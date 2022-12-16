package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantsActivities.CHAVE_ALUNO_SELECIONADO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String ACTIVITY_LISTA_DE_ALUNOS_TITULO = "LISTA DE ALUNOS";
    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "ABRINDO MEU PRIMEIRO APP", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_lista_alunos);
        setTitle(ACTIVITY_LISTA_DE_ALUNOS_TITULO);
        configuraListaDeAlunos();
        configuracaoBotaoAdicionarAluno();
        dao.salvarAluno(new Aluno("Davi", "11111111111", "davi@teste.com"));
        dao.salvarAluno(new Aluno("Isa", "22222222222", "isa@teste.com"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaListaDeAlunos();
    }

    private void atualizaListaDeAlunos() {
        adapter.clear();
        adapter.addAll(dao.getTodosAlunos());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_de_alunos_menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemselected = item.getItemId();
        if (itemselected == R.id.activity_lista_de_alunos_menu_item_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            adapter.remove(adapter.getItem(menuInfo.position));
        }
        return super.onContextItemSelected(item);


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
        setAdapter(lista_de_alunos);
        setClickListenerParaEditarOAlunoSelecionado(lista_de_alunos);
        registerForContextMenu(lista_de_alunos);
    }


    private void setClickListenerParaEditarOAlunoSelecionado(ListView lista_de_alunos) {
        lista_de_alunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                Aluno alunoSelecionado = (Aluno) adapterView.getItemAtPosition(posicao);
                abreFormularioParaEditarAluno(alunoSelecionado);
            }
        });
    }

    private void abreFormularioParaEditarAluno(Aluno alunoSelecionado) {
        Intent goToFormulario = new Intent(ListaAlunosActivity.this, FormularioCadastroAlunoActivity.class);
        goToFormulario.putExtra(CHAVE_ALUNO_SELECIONADO, alunoSelecionado);
        Log.i("idAlunoSelecionado", String.valueOf(alunoSelecionado.getID()));
        startActivity(goToFormulario);
    }

    private void setAdapter(ListView lista_de_alunos) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lista_de_alunos.setAdapter(adapter);
    }
}
