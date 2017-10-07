package br.com.dercilima.agendadecompromissos.controllers.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.dercilima.agendadecompromissos.controllers.DBHelper;
import br.com.dercilima.agendadecompromissos.models.Agendamento;

/**
 * Created by dercilima on 07/10/17.
 */

public class AgendamentoDAO {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private final Context context;
    private DBHelper helper;

    public AgendamentoDAO(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    // insert
    public void insert(Agendamento agendamento) {
        try {

            agendamento.setId(
                    helper.getWritableDatabase().insertOrThrow(
                            Agendamento.Columns.TABLE_NAME, null, getContentValues(agendamento)
                    )
            );

        } finally {
            helper.close();
        }
    }

    private ContentValues getContentValues(Agendamento agendamento) {
        ContentValues values = new ContentValues();

        values.put(Agendamento.Columns.COLUMN_NAME_NOME, agendamento.getNome());
        values.put(Agendamento.Columns.COLUMN_NAME_ASSUNTO, agendamento.getAssunto());
        values.put(Agendamento.Columns.COLUMN_NAME_DATA, formatDateToString(agendamento.getData()));

        return values;
    }

    private String formatDateToString(Date data) {
        return getDateFormat().format(data);
    }

    private SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
    }

    // update

    // delete

    // select
    public List<Agendamento> select() {
        try {

            // SQL
            String sql = "SELECT * FROM " + Agendamento.Columns.TABLE_NAME;

            // Passar o SQL para o banco de dados
            SQLiteDatabase database = helper.getReadableDatabase();
            Cursor cursor = database.rawQuery(sql, null);

            // Cria uma nova lista
            List<Agendamento> agendamentos = new ArrayList<>();

            // Tratar o retorno do banco de dados
            if (cursor.moveToFirst()) {
                do {

                    Agendamento agendamento = new Agendamento();
                    agendamento.setId(cursor.getLong(cursor.getColumnIndex(Agendamento.Columns._ID)));
                    agendamento.setNome(cursor.getString(cursor.getColumnIndex(Agendamento.Columns.COLUMN_NAME_NOME)));
                    agendamento.setAssunto(cursor.getString(cursor.getColumnIndex(Agendamento.Columns.COLUMN_NAME_ASSUNTO)));

                    // Data yyyy-MM-dd
                    String data = cursor.getString(cursor.getColumnIndex(Agendamento.Columns.COLUMN_NAME_DATA));
                    agendamento.setData(formatStringToDate(data));

                    // Adicionar o agendamento na lista
                    agendamentos.add(agendamento);

                } while (cursor.moveToNext());
            }

            return agendamentos;

        } finally {
            helper.close();
        }
    }

    private Date formatStringToDate(String data) {
        try {
            return getDateFormat().parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
