package com.example.agenda.ui.activity;

import static com.example.agenda.model.TipoTelefone.CELULAR;
import static com.example.agenda.model.TipoTelefone.FIXO;
import static com.example.agenda.ui.activity.ConstantsActivities.ACTIVITY_FORMULARIO_DE_CADASTRO_DE_ALUNO_TITULO;
import static com.example.agenda.ui.activity.ConstantsActivities.ACTIVITY_FORMULARIO_DE_CADASTRO_PARA_EDITAR_ALUNO_TITULO;
import static com.example.agenda.ui.activity.ConstantsActivities.CHAVE_ALUNO_SELECIONADO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.asynctask.BuscaTodosTelefonesDoAlunoTask;
import com.example.agenda.asynctask.EditaAlunoTask;
import com.example.agenda.asynctask.SalvaAlunoTask;
import com.example.agenda.database.AgendaDatabase;
import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.model.Telefone;
import com.example.agenda.model.TipoTelefone;

import java.util.List;

public class FormularioCadastroAlunoActivity extends AppCompatActivity {


    private AlunoDAO alunoDAO;
    private EditText campoNome;
    private EditText campoTelefoneFixo;

    private EditText campoTelefoneCelular;
    private EditText campoEmail;
    private Aluno aluno;

    private TelefoneDAO telefoneDAO;
    private List<Telefone> telefonesDoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro_aluno);
        setCamposDoLayoutDoFormularioDeCadastros();
        alunoDAO = AgendaDatabase.getInstance(this).getAlunoDAO();
        telefoneDAO = AgendaDatabase.getInstance(this).getTelefoneDAO();
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
            preencheCampos();
        } else {
            setTitle(ACTIVITY_FORMULARIO_DE_CADASTRO_DE_ALUNO_TITULO);
            aluno = new Aluno();
        }
    }


    private void finalizaOFormulario() {
        preencheAluno();
        Telefone fixo = criaTelefone(campoTelefoneFixo, FIXO);
        Telefone celular = criaTelefone(campoTelefoneCelular, CELULAR);
        if (aluno.hasIDvalido()) {
            editaAluno(fixo, celular);
        } else {
            salvaAluno(fixo, celular);
        }
    }

    @NonNull
    private Telefone criaTelefone(EditText campoTelefone, TipoTelefone tipo) {
        String numeroTelefone = campoTelefone.getText().toString();
        return new Telefone(numeroTelefone, tipo);
    }

    private void salvaAluno(Telefone fixo, Telefone celular) {
        new SalvaAlunoTask(alunoDAO, telefoneDAO, aluno, fixo, celular, this::finish).execute();
    }

    private void editaAluno(Telefone fixo, Telefone celular) {
        new EditaAlunoTask(aluno, fixo, celular, telefoneDAO, alunoDAO, telefonesDoAluno, this::finish).execute();
    }


    private void preencheAluno() {
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        aluno.setNome(nome);
        aluno.setEmail(email);
    }


    private void setCamposDoLayoutDoFormularioDeCadastros() {
        this.campoNome = findViewById(R.id.activity_formulario_aluno_campo_nome);
        this.campoTelefoneFixo = findViewById(R.id.activity_formulario_aluno_campo_telefone);
        this.campoEmail = findViewById(R.id.activity_formulario_aluno_campo_email);
        this.campoTelefoneCelular = findViewById(R.id.activity_formulario_aluno_campo_celular);
    }

    private void preencheCampos() {
        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        preencheCamposDeTelefone();
    }

    private void preencheCamposDeTelefone() {

        new BuscaTodosTelefonesDoAlunoTask(telefoneDAO, aluno, telefones -> {
            telefonesDoAluno = telefones;
            for (Telefone telefone:telefonesDoAluno) {
                if(telefone.getTipo() == FIXO){
                    campoTelefoneFixo.setText(telefone.getNumero());
                }else {
                    campoTelefoneCelular.setText(telefone.getNumero());
                }
            }
        }).execute();
    }
}