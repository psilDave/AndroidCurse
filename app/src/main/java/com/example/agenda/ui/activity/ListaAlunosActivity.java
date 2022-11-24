package com.example.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String ACTIVITY_LISTA_DE_ALUNOS_TITULO = "LISTA DE ALUNOS";
    private final AlunoDAO dao = new AlunoDAO();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "ABRINDO MEU PRIMEIRO APP", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_lista_alunos);
        setTitle(ACTIVITY_LISTA_DE_ALUNOS_TITULO);
        configuracaoBotaoAdicionarAluno();

    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraListaDeAlunos();
    }

    private void configuracaoBotaoAdicionarAluno() {
        FloatingActionButton addNewAluno = findViewById(R.id.activity_lista_de_alunos_botao_para_adicionar_um_aluno);
        addNewAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListaAlunosActivity.this, FormularioCadastroAlunoActivity.class));
            }
        });
    }

    private void configuraListaDeAlunos() {
        ListView lista_de_alunos = findViewById(R.id.activity_main_lista_de_alunos);
        lista_de_alunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.getTodosAlunos()));
    }
}
