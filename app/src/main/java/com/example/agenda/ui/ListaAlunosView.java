package com.example.agenda.ui;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.agenda.database.AgendaDatabase;
import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final Context context;
    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(context);
        this.dao = AgendaDatabase.getInstance(context).getRoomAlunoDAO();
    }

    public void atualizaListaDeAlunos() {
        adapter.atualiza(dao.getTodosAlunos());
    }

    public void remove(Aluno alunoSelecionado) {
        dao.remove(alunoSelecionado);
        adapter.remove(alunoSelecionado);
    }

    public void setAdapter(ListView lista_de_alunos) {
        lista_de_alunos.setAdapter(adapter);
    }

    public void dialogParaConfirmarRemocaoDeAluno(@NonNull MenuItem item) {
        new AlertDialog.Builder(context).setTitle("REMOVENDO ALUNO").setMessage("Você gostaria de realmente remover esse contado?").setPositiveButton("Sim", (dialogInterface, i) -> removendoAluno(item)).setNegativeButton("Não", null).show();
    }

    private void removendoAluno(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Aluno alunoSelecionado = adapter.getItem(menuInfo.position);
        remove(alunoSelecionado);
    }
}
