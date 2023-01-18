package com.example.agenda.database.dao;

import static com.example.agenda.database.ConstantesDataBase.SELECT_ALL_ALUNOS;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.agenda.model.Aluno;

import java.util.List;

@Dao
public interface AlunoDAO {
    @Insert
    void salvarAluno(Aluno aluno);

    @Query(SELECT_ALL_ALUNOS)
    List<Aluno> getTodosAlunos();

    @Delete
    void remove(Aluno aluno);

    @Update
    void editaAluno(Aluno aluno);
}
