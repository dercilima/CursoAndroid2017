package br.com.dercilima.agendadecompromissos.views;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.com.dercilima.agendadecompromissos.R;
import br.com.dercilima.agendadecompromissos.controllers.dao.AgendamentoDAO;
import br.com.dercilima.agendadecompromissos.models.Agendamento;

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editAssunto;
    private EditText editData;

    private Agendamento agendamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        findById();

        getDataForIntent();

        showData();

        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Instancia um novo calendário
                final Calendar calendario = Calendar.getInstance();

                // Abre um dialog com um Calendário para selecionar uma data
                new DatePickerDialog(CadastroActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        calendario.set(year, month, dayOfMonth);

                        String dataFormatada = getDateFormat().format(calendario.getTime());
                        editData.setText(dataFormatada);

                    }
                }, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }

    private SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    }

    private void getDataForIntent() {
        if (getIntent().hasExtra("agendamento")) {
            agendamento = getIntent().getParcelableExtra("agendamento");
        }
    }

    private void showData() {
        if (agendamento != null) {
            editNome.setText(agendamento.getNome());
            editAssunto.setText(agendamento.getAssunto());
            editData.setText(getDateFormat().format(agendamento.getData()));
        }
    }

    private void findById() {
        editNome = (EditText) findViewById(R.id.edit_nome);
        editAssunto = (EditText) findViewById(R.id.edit_assunto);
        editData = (EditText) findViewById(R.id.edit_data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastro_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // Exibir o botão de exluir apenas quando o item já tiver sido gravado no banco de dados
        menu.findItem(R.id.excluir_menu_item).setVisible(agendamento != null && agendamento.getId() > 0);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.salvar_menu_item:
                gravar();
                return true;

            case R.id.excluir_menu_item:
                excluir();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void excluir() {

        // Pedir confirmação
        new AlertDialog.Builder(this)
                .setTitle(R.string.warning)
                .setMessage(R.string.confirmar_exclusao_agendamento)
                .setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        // Excluir o registro no banco de dados
                        new AgendamentoDAO(CadastroActivity.this).delete(agendamento);

                        // Avisar o usuário
                        Toast.makeText(CadastroActivity.this, R.string.agendamento_excluido, Toast.LENGTH_LONG).show();

                        // Fechar a activity
                        setResult(RESULT_OK);
                        finish();
                    }
                })
                .setNegativeButton(R.string.cancelar, null)
                .create()
                .show();

    }

    private void gravar() {

        if (agendamento == null) {
            agendamento = new Agendamento();
        }

        // Carregar os dados da tela para o objeto Agendamento
        agendamento.setNome(editNome.getText().toString());
        agendamento.setAssunto(editAssunto.getText().toString());
        try {
            agendamento.setData(getDateFormat().parse(editData.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (agendamento.getId() == 0) {
            new AgendamentoDAO(this).insert(agendamento);
        } else {
            new AgendamentoDAO(this).update(agendamento);
        }

        // Retornar o objeto Agendamento para a MainActivity
        Intent dataIntent = new Intent();
        dataIntent.putExtra("agendamento", agendamento);
        setResult(RESULT_OK, dataIntent);

        // Fechar esta Activity
        finish();

    }
}
