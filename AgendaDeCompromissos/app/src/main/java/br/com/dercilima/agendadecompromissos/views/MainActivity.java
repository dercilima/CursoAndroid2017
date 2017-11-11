package br.com.dercilima.agendadecompromissos.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
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
import br.com.dercilima.agendadecompromissos.listeners.OnRecyclerItemLongClickListener;
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

                // Carregar todos os agendamentos
                carregarAgendamentos();

            }

        }
    }

    private void setupRecyclerView() {

        mRecyclerView.setHasFixedSize(true);

        // Layout Manager
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(llm);

        final AgendamentosAdapter adapter = new AgendamentosAdapter(this, agendamentos);
        adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Agendamento agendamentoSelected = agendamentos.get(position);
                showCadastroAgendamento(agendamentoSelected);
            }
        });
        adapter.setOnItemLongClickListener(new OnRecyclerItemLongClickListener() {
            @Override
            public void onItemLongClick(final int position) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.warning)
                        .setMessage(R.string.confirmar_exclusao_agendamento)
                        .setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Excluir no banco de dados
                                new AgendamentoDAO(MainActivity.this).delete(agendamentos.get(position));

                                // Remover o item da lista de agendamentos que está em memória
                                agendamentos.remove(position);

                                // Atualizar lista
                                adapter.notifyItemRemoved(position);

                                // Avisar o usuário
                                Toast.makeText(MainActivity.this, R.string.agendamento_excluido, Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(R.string.cancelar, null)
                        .create()
                        .show();
            }
        });
        mRecyclerView.setAdapter(adapter);

    }

}
