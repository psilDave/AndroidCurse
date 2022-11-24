package com.example.agenda.dao;

import com.example.agenda.model.Aluno;

import java.util.ArrayList;

public class AlunoDAO {

    private final static ArrayList<Aluno> listaAlunos =  new ArrayList<>();

    public void salvarAlunos(Aluno aluno){
        listaAlunos.add(aluno);

    }
    public ArrayList<Aluno> getTodosAlunos(){
        return new ArrayList<>(listaAlunos);
    }
}
