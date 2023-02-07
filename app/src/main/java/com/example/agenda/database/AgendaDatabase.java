package com.example.agenda.database;

import static com.example.agenda.database.ConstantesDataBase.AGENDA_DB;
import static com.example.agenda.database.AgendaMigrations.TODAS_MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.agenda.database.converter.ConversorCalendar;
import com.example.agenda.database.converter.ConversorTipoTelefone;
import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.model.Telefone;

@Database(entities = {Aluno.class, Telefone.class}, version =5, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTipoTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {

    public static AgendaDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AgendaDatabase.class, AGENDA_DB).addMigrations(TODAS_MIGRATIONS).build();
    }


    public abstract AlunoDAO getAlunoDAO();

    public abstract TelefoneDAO getTelefoneDAO();
}
