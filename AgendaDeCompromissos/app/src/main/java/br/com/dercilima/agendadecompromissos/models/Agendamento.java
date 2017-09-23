package br.com.dercilima.agendadecompromissos.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dercilima on 23/09/17.
 */

public class Agendamento implements Serializable {

    private String nome;
    private String assunto;
    private Date data;

    public Agendamento() {

    }

    public String getNome() {
        return nome;
    }

    public Agendamento setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getAssunto() {
        return assunto;
    }

    public Agendamento setAssunto(String assunto) {
        this.assunto = assunto;
        return this;
    }

    public Date getData() {
        return data;
    }

    public Agendamento setData(Date data) {
        this.data = data;
        return this;
    }
}
