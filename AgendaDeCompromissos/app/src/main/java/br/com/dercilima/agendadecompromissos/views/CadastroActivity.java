package br.com.dercilima.agendadecompromissos.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import br.com.dercilima.agendadecompromissos.R;
import br.com.dercilima.agendadecompromissos.models.Agendamento;

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editAssunto;
    private EditText editData;

    private Calendar calendario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro);

        findById();

        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Instancia um novo calendário
                calendario = Calendar.getInstance();

                // Abre um dialog com um Calendário para selecionar uma data
                new DatePickerDialog(CadastroActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        calendario.set(year, month, dayOfMonth);

//                        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(calendario.getTime());
//                        editData.setText(dataFormatada);

                        String dataFormatada = DateFormat.getMediumDateFormat(CadastroActivity.this).format(calendario.getTime());
                        editData.setText(dataFormatada);

                    }
                }, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }

    private void findById() {
        editNome = (EditText) findViewById(R.id.edit_nome);
        editAssunto= (EditText) findViewById(R.id.edit_assunto);
        editData = (EditText) findViewById(R.id.edit_data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastro_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.salvar_menu_item) {
            gravar();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void gravar() {

        // Carregar os dados da tela para o objeto Agendamento
        Agendamento agendamento = new Agendamento();
        agendamento.setNome(editNome.getText().toString());
        agendamento.setAssunto(editAssunto.getText().toString());
        agendamento.setData(calendario != null ? calendario.getTime() : null);

        // Retornar o objeto Agendamento para a MainActivity
        Intent dataIntent = new Intent();
        dataIntent.putExtra("agendamento", agendamento);
        setResult(RESULT_OK, dataIntent);

        // Fechar esta Activity
        finish();

    }
}
