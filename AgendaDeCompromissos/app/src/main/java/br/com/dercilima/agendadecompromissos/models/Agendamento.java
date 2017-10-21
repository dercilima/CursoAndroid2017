package br.com.dercilima.agendadecompromissos.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

import java.util.Date;

/**
 * Created by dercilima on 23/09/17.
 */

public class Agendamento implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.nome);
        dest.writeString(this.assunto);
        dest.writeLong(this.data != null ? this.data.getTime() : -1);
    }

    protected Agendamento(Parcel in) {
        this.id = in.readLong();
        this.nome = in.readString();
        this.assunto = in.readString();
        long tmpData = in.readLong();
        this.data = tmpData == -1 ? null : new Date(tmpData);
    }

    public static final Parcelable.Creator<Agendamento> CREATOR = new Parcelable.Creator<Agendamento>() {
        @Override
        public Agendamento createFromParcel(Parcel source) {
            return new Agendamento(source);
        }

        @Override
        public Agendamento[] newArray(int size) {
            return new Agendamento[size];
        }
    };
}
