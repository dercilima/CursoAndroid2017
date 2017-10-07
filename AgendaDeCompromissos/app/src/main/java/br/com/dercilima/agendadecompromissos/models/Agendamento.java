package br.com.dercilima.agendadecompromissos.models;

import android.provider.BaseColumns;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dercilima on 23/09/17.
 */

public class Agendamento implements Serializable {

    public static class Columns implements BaseColumns {
        public static final String TABLE_NAME = "AGENDA";
        public static final String COLUMN_NAME_NOME = "NOME";
        public static final String COLUMN_NAME_ASSUNTO = "ASSUNTO";
        public static final String COLUMN_NAME_DATA = "DATA";
    }

    private long id;
    private String nome;
    private String assunto;
    private Date data;

    public Agendamento() {
    }

    public long getId() {
        return id;
    }

    public Agendamento setId(long id) {
        this.id = id;
        return this;
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
