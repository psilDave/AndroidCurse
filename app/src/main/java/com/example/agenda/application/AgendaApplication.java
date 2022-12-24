package com.example.agenda.application;

import android.app.Application;

import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AlunoDAO dao = new AlunoDAO();
        dao.salvarAluno(new Aluno("Davi", "11111111111", "davi@teste.com"));
        dao.salvarAluno(new Aluno("Isa", "22222222222", "isa@teste.com"));
    }
}
