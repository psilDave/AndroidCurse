package com.example.agenda.database;

import static com.example.agenda.database.ConstantesDataBase.AGENDA_DB;
import static com.example.agenda.database.AgendaMigrations.TODAS_MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.agenda.database.converter.ConversorCalendar;
import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 4, exportSchema = false)
@TypeConverters({ConversorCalendar.class})
public abstract class AgendaDatabase extends RoomDatabase {

    public static AgendaDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AgendaDatabase.class, AGENDA_DB).allowMainThreadQueries().addMigrations(TODAS_MIGRATIONS).build();
    }


    public abstract AlunoDAO getRoomAlunoDAO();
}
