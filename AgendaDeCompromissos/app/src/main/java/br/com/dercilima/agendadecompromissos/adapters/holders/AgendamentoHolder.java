package br.com.dercilima.agendadecompromissos.adapters.holders;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import br.com.dercilima.agendadecompromissos.R;
import br.com.dercilima.agendadecompromissos.listeners.OnRecyclerItemClickListener;
import br.com.dercilima.agendadecompromissos.models.Agendamento;

/**
 * Created by dercilima on 21/10/17.
 */

public class AgendamentoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textNome;
    private TextView textAssunto;

    private OnRecyclerItemClickListener mListener;

    public AgendamentoHolder(View itemView) {
        super(itemView);
        textNome = (TextView) itemView.findViewById(R.id.text_nome);
        textAssunto = (TextView) itemView.findViewById(R.id.text_assunto);
        itemView.setOnClickListener(this);
    }

    public void bind(Agendamento agendamento) {
        textNome.setText(TextUtils.concat(String.valueOf(agendamento.getId()), " - ", agendamento.getNome()));
        textAssunto.setText(agendamento.getAssunto());
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        mListener = onRecyclerItemClickListener;
    }

    @Override
    public void onClick(View view) {
        Log.d("AgendamentoHolder", "onClick: [position] = " + getAdapterPosition());
        if (mListener != null) {
            mListener.onItemClick(getAdapterPosition());
        }
    }
}