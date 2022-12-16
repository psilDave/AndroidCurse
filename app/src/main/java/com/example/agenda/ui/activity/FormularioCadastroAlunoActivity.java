package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantsActivities.CHAVE_ALUNO_SELECIONADO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

public class FormularioCadastroAlunoActivity extends AppCompatActivity {

    private static final String ACTIVITY_FORMULARIO_DE_CADASTRO_DE_ALUNO_TITULO = "NOVO ALUNO";
    private static final String ACTIVITY_FORMULARIO_DE_CADASTRO_PARA_EDITAR_ALUNO_TITULO = "EDITAR ALUNO";
    private static final AlunoDAO dao = new AlunoDAO();
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro_aluno);
        setCamposDoLayoutDoFormularioDeCadastros();
        setAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_cadastro_aluno_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemSelecionado = item.getItemId();
        if (itemSelecionado == R.id.activity_formulario_cadastro_aluno_botao_salvar) {
            finalizaOFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO_SELECIONADO)) {
            setTitle(ACTIVITY_FORMULARIO_DE_CADASTRO_PARA_EDITAR_ALUNO_TITULO);
            aluno = (Aluno) dados.getSerializableExtra("alunoSelecionado");
            getExtraAlunoSelecionado();
        } else {
            setTitle(ACTIVITY_FORMULARIO_DE_CADASTRO_DE_ALUNO_TITULO);
            aluno = new Aluno();
        }
    }


    private void finalizaOFormulario() {
        preencheAluno();
        if (aluno.hasIDvalido()) {
            dao.editaAluno(aluno);
        } else {
            salvar(aluno);
        }
        finish();
    }

    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setTelefone(telefone);

    }


    private void salvar(Aluno alunoNovo) {
        dao.salvarAluno(alunoNovo);
        finish();
    }

    private void setCamposDoLayoutDoFormularioDeCadastros() {
        this.campoNome = findViewById(R.id.activity_formulario_aluno_campo_nome);
        this.campoTelefone = findViewById(R.id.activity_formulario_aluno_campo_telefone);
        this.campoEmail = findViewById(R.id.activity_formulario_aluno_campo_email);
    }

    private void getExtraAlunoSelecionado() {
        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        campoTelefone.setText(aluno.getTelefone());
    }
}