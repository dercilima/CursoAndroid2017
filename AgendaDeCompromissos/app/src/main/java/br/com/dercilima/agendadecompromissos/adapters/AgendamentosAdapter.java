package br.com.dercilima.agendadecompromissos.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.dercilima.agendadecompromissos.R;
import br.com.dercilima.agendadecompromissos.models.Agendamento;

/**
 * Created by dercilima on 23/09/17.
 */

public class AgendamentosAdapter extends RecyclerView.Adapter<AgendamentosAdapter.MyViewHolder> {

    private Context mContext;
    private List<Agendamento> agendamentos;

    public AgendamentosAdapter(Context mContext, List<Agendamento> agendamentos) {
        this.mContext = mContext;
        this.agendamentos = agendamentos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.agendamentos_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Agendamento agendamento = agendamentos.get(position);
        holder.bind(agendamento);
    }

    @Override
    public int getItemCount() {
        return agendamentos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textNome;
        private TextView textAssunto;

        public MyViewHolder(View itemView) {
            super(itemView);
            textNome = (TextView) itemView.findViewById(R.id.text_nome);
            textAssunto = (TextView) itemView.findViewById(R.id.text_assunto);
        }

        public void bind(Agendamento agendamento) {
            textNome.setText(TextUtils.concat(String.valueOf(agendamento.getId()), " - ", agendamento.getNome()));
            textAssunto.setText(agendamento.getAssunto());
        }
    }

}
