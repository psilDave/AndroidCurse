package com.example.agenda.asynctask;

import static com.example.agenda.model.TipoTelefone.FIXO;

import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.model.Telefone;

import java.util.List;

public class EditaAlunoTask extends BaseAlunoComTelefoneTask {
    private final Aluno aluno;
    private final Telefone fixo;
    private final Telefone celular;
    private final TelefoneDAO telefoneDAO;
    private final AlunoDAO alunoDAO;
    private final List<Telefone> telefonesDoAluno;

    public EditaAlunoTask(Aluno aluno, Telefone fixo, Telefone celular, TelefoneDAO telefoneDAO, AlunoDAO alunoDAO, List<Telefone> telefonesDoAluno, FinalizadaListener listener) {

        super(listener);
        this.aluno = aluno;
        this.fixo = fixo;
        this.celular = celular;
        this.telefoneDAO = telefoneDAO;
        this.alunoDAO = alunoDAO;
        this.telefonesDoAluno = telefonesDoAluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.editaAluno(aluno);
        vinculaAlunoComTelefone(aluno.getID(), fixo, celular);
        atualizaIdsDosTelefones(fixo, celular);
        telefoneDAO.atualiza(fixo, celular);
        return null;
    }


    private void atualizaIdsDosTelefones(Telefone fixo, Telefone celular) {
        for (Telefone telefone:telefonesDoAluno) {
            if (telefone.getTipo() == FIXO){
                fixo.setId(telefone.getId());
            } else{
                celular.setId(telefone.getId());
            }
        }
    }

}
