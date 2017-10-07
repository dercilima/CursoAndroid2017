package br.com.dercilima.agendadecompromissos.controllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.com.dercilima.agendadecompromissos.models.Agendamento;

/**
 * Created by dercilima on 07/10/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Constantes
    private static final String DATABASE_NAME = "Agenda.sqlite";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Chamado apenas uma vez

        final String sqlCreateTables =
                "CREATE TABLE " + Agendamento.Columns.TABLE_NAME + "(" +
                        Agendamento.Columns._ID + " INTEGER PRIMARY KEY, " +
                        Agendamento.Columns.COLUMN_NAME_NOME + " TEXT NOT NULL, " +
                        Agendamento.Columns.COLUMN_NAME_ASSUNTO + " TEXT NOT NULL, " +
                        Agendamento.Columns.COLUMN_NAME_DATA + " TEXT NOT NULL);";

        Log.d(getClass().getName(), "onCreate() called with: sqlCreateTables = [" + sqlCreateTables + "]");

        sqLiteDatabase.execSQL(sqlCreateTables);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
