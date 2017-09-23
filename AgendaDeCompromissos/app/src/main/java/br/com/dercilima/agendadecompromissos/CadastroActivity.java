package br.com.dercilima.agendadecompromissos;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editAssunto;
    private EditText editData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro);

        findById();

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
}
