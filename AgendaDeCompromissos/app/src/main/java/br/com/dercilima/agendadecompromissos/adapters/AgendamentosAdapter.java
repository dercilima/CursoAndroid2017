package br.com.dercilima.agendadecompromissos.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.dercilima.agendadecompromissos.R;
import br.com.dercilima.agendadecompromissos.adapters.holders.AgendamentoHolder;
import br.com.dercilima.agendadecompromissos.listeners.OnRecyclerItemClickListener;
import br.com.dercilima.agendadecompromissos.models.Agendamento;

/**
 * Created by dercilima on 23/09/17.
 */

public class AgendamentosAdapter extends RecyclerView.Adapter<AgendamentoHolder> {

    private Context mContext;
    private List<Agendamento> agendamentos;
    private OnRecyclerItemClickListener mListener;

    public AgendamentosAdapter(Context mContext, List<Agendamento> agendamentos) {
        this.mContext = mContext;
        this.agendamentos = agendamentos;
    }

    @Override
    public AgendamentoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.agendamentos_item, parent, false);
        return new AgendamentoHolder(view);
    }

    @Override
    public void onBindViewHolder(AgendamentoHolder holder, int position) {
        Agendamento agendamento = agendamentos.get(position);
        holder.bind(agendamento);
        holder.setOnRecyclerItemClickListener(mListener);
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        mListener = onRecyclerItemClickListener;
    }

    @Override
    public int getItemCount() {
        return agendamentos.size();
    }

}
