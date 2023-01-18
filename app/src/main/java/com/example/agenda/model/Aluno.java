package com.example.agenda.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ID = 0;
    private String nome;
    private String telefone;
    private String email;
    private Calendar momentoDeCadasto = Calendar.getInstance();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getNome();
    }

    public boolean hasIDvalido() {
        return getID() > 0;
    }

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public Calendar getMomentoDeCadasto() {
        return momentoDeCadasto;
    }

    public void setMomentoDeCadasto(Calendar momentoDeCadasto) {
        this.momentoDeCadasto = momentoDeCadasto;
    }
}
