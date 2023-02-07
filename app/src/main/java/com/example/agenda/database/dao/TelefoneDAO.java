package com.example.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.agenda.model.Telefone;

import java.util.List;

@Dao
public interface TelefoneDAO {
    @Insert
    void salva(Telefone... telefones);

    @Query("SELECT * FROM Telefone WHERE alunoID = :ID LIMIT 1")
    Telefone buscaPrimeiroTelefoneDoAluno(int ID);

    @Query("SELECT * FROM Telefone WHERE alunoID = :alunoID")
    List<Telefone> buscaTodosTelefonesDoAluno(int alunoID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualiza(Telefone... telefones);
}
