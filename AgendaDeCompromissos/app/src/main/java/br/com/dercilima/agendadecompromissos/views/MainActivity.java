package br.com.dercilima.agendadecompromissos.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.dercilima.agendadecompromissos.R;
import br.com.dercilima.agendadecompromissos.adapters.AgendamentosAdapter;
import br.com.dercilima.agendadecompromissos.controllers.dao.AgendamentoDAO;
import br.com.dercilima.agendadecompromissos.listeners.OnRecyclerItemClickListener;
import br.com.dercilima.agendadecompromissos.models.Agendamento;

public class MainActivity extends AppCompatActivity {

    private static final int RC_CADASTRO = 123;

    private List<Agendamento> agendamentos = new ArrayList<>();

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findById();

        setSupportActionBar(toolbar);

        setTitle(R.string.title_activity_main);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCadastroAgendamento();

            }
        });

        carregarAgendamentos();
    }

    private void showCadastroAgendamento() {
        showCadastroAgendamento(null);
    }

    private void showCadastroAgendamento(Agendamento agendamento) {
        Intent intent = new Intent(this, CadastroActivity.class);
        // Anexar o agendamento na intent
        if (agendamento != null) {
            intent.putExtra("agendamento", agendamento);
        }
        startActivityForResult(intent, RC_CADASTRO);
    }

    private void findById() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void carregarAgendamentos() {
        // Limpar a lista de agendamentos
        agendamentos.clear();

        // Carregar os agendamentos do banco de dados
        agendamentos = new AgendamentoDAO(this).select();

        // Atualizar a lista
        setupRecyclerView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_CADASTRO) {

            if (resultCode == RESULT_OK) {

                /*// Recupera o agendamento
                Agendamento agendamento = (Agendamento) data.getSerializableExtra("agendamento");

                // Adiciona na lista de agendamentos
                agendamentos.add(agendamento);

                // Configurar a RecyclerView
                setupRecyclerView();*/

                // Carregar todos os agendamentos
                carregarAgendamentos();

            } else {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
            }


        }
    }

    private void setupRecyclerView() {

        mRecyclerView.setHasFixedSize(true);

        // Layout Manager
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(llm);

        AgendamentosAdapter adapter = new AgendamentosAdapter(this, agendamentos);
        adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Agendamento agendamentoSelected = agendamentos.get(position);
                showCadastroAgendamento(agendamentoSelected);
            }
        });
        mRecyclerView.setAdapter(adapter);

    }

}
