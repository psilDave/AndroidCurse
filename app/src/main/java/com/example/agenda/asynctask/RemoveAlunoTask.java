package com.example.agenda.asynctask;

import android.os.AsyncTask;

import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

public class RemoveAlunoTask extends AsyncTask {


    private final AlunoDAO alunoDAO;
    private final ListaAlunosAdapter adapter;
    private final Aluno aluno;

    public RemoveAlunoTask(AlunoDAO alunoDAO, ListaAlunosAdapter adapter, Aluno aluno) {
        this.alunoDAO = alunoDAO;
        this.adapter = adapter;
        this.aluno = aluno;
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        alunoDAO.remove(aluno);
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        adapter.remove(aluno);
    }
}
