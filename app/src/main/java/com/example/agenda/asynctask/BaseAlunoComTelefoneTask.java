package com.example.agenda.asynctask;

import android.os.AsyncTask;

import com.example.agenda.model.Telefone;

abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void, Void, Void> {

    private final FinalizadaListener listener;

    BaseAlunoComTelefoneTask(FinalizadaListener listener) {
        this.listener = listener;
    }

    void vinculaAlunoComTelefone(int alunoID, Telefone... telefones) {
        for (Telefone telefone:telefones
        ) {telefone.setAlunoID(alunoID);
        }
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.quandoFinalizada();
    }

    public interface FinalizadaListener{
        void quandoFinalizada();
    }
}
