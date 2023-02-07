package com.example.agenda.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Telefone;

public class BuscaPrimeiroTelefoneDoAlunoTask extends AsyncTask<Void, Void, Telefone> {
    private final TelefoneDAO telefoneDAO;
    private final int alunoID;
    private final   PrimeiroTelefoneEncontradoListener listener;

    public BuscaPrimeiroTelefoneDoAlunoTask(TelefoneDAO telefoneDAO, int alunoID, PrimeiroTelefoneEncontradoListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.alunoID = alunoID;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return telefoneDAO.buscaPrimeiroTelefoneDoAluno(alunoID);
    }

    @Override
    protected void onPostExecute(Telefone telefone) {
        super.onPostExecute(telefone);
        listener.quandoEncontrado(telefone);
    }

    public interface PrimeiroTelefoneEncontradoListener {
        void quandoEncontrado(Telefone telefone);
    }
}
