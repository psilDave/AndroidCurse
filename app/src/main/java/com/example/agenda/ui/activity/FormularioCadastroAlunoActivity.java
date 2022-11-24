package com.example.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.R;
import com.example.agenda.dao.AlunoDAO;
import com.example.agenda.model.Aluno;

public class FormularioCadastroAlunoActivity extends AppCompatActivity {

    public static final String ACTIVITY_FORMULARIO_DE_CADASTRO_DE_ALUNO_TITULO = "NOVO ALUNO";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private static AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro_aluno);
        setCamposDoLayoutDoFormularioDeCadastros();
        setTitle(ACTIVITY_FORMULARIO_DE_CADASTRO_DE_ALUNO_TITULO);
        configuracaoDoBotaoDeSalvarFormulario();
    }

    private void configuracaoDoBotaoDeSalvarFormulario() {
        Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_de_salvar_o_formulario);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aluno alunoNovo = new Aluno(campoNome.getText().toString(), campoTelefone.getText().toString(), campoEmail.getText().toString());
                salvar(alunoNovo);
            }
        });
    }

    private void salvar(Aluno alunoNovo) {
        dao.salvarAlunos(alunoNovo);
        finish();
    }

    private void setCamposDoLayoutDoFormularioDeCadastros() {
        this.campoNome = findViewById(R.id.activity_formulario_aluno_campo_nome);
        this.campoTelefone = findViewById(R.id.activity_formulario_aluno_campo_telefone);
        this.campoEmail = findViewById(R.id.activity_formulario_aluno_campo_email);
    }
}