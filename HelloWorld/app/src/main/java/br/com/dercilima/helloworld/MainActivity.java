package br.com.dercilima.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editDataNascimento;
    private EditText editEmail;
    private EditText editTelefone;
    private EditText editEndereco;
    private EditText editCpf;
    private EditText editSenha;
    private Button finalizarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initViews();

        finalizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, DetalhesActivity.class);

                startActivity(intent);

            }
        });

    }

    private void initViews() {
        editNome = (EditText) findViewById(R.id.edit_nome);
        editDataNascimento = (EditText) findViewById(R.id.edit_data_nascimento);
        editEmail = (EditText) findViewById(R.id.edit_email);
        editTelefone = (EditText) findViewById(R.id.edit_telefone);
        editEndereco = (EditText) findViewById(R.id.edit_endereco);
        editCpf = (EditText) findViewById(R.id.edit_cpf);
        editSenha = (EditText) findViewById(R.id.edit_senha);
        finalizarButton = (Button) findViewById(R.id.finalizar_button);
    }

}
