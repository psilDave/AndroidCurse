package com.example.agenda.dao;

import android.util.Log;

import com.example.agenda.model.Aluno;

import java.util.ArrayList;

public class AlunoDAO {

    private final static ArrayList<Aluno> listaAlunos = new ArrayList<>();
    private int contadorDeID = 0;

    public void salvarAluno(Aluno aluno) {
        atualizaOContadorDeID();
        listaAlunos.add(aluno);
        aluno.setID(contadorDeID);
        Log.i("salvarAluno", "aluno salvo");


    }

    private void atualizaOContadorDeID() {
        contadorDeID++;
    }

    public ArrayList<Aluno> getTodosAlunos() {
        return new ArrayList<>(listaAlunos);
    }

    public void editaAluno(Aluno aluno) {
        Aluno alunoEncontrado;
        alunoEncontrado = buscaAlunoPeloID(aluno);
        if (alunoEncontrado != null) {
            int posicaoDoAlunoEncontrado = listaAlunos.indexOf(alunoEncontrado);
            listaAlunos.set(posicaoDoAlunoEncontrado, aluno);
        }
    }

    private Aluno buscaAlunoPeloID(Aluno aluno) {
        for (Aluno a : listaAlunos) {
            if (a.getID() == aluno.getID()) {
                return a;
            }
        }
        return null;
    }

    public void remove(Aluno alunoSelecionado) {
        Aluno alunoDevolvido = buscaAlunoPeloID(alunoSelecionado);
        if (alunoDevolvido != null){
            listaAlunos.remove(alunoDevolvido);
        }
    }
}
