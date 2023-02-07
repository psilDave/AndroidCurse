package com.example.agenda.asynctask;

import com.example.agenda.database.dao.AlunoDAO;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.model.Telefone;

public class SalvaAlunoTask extends BaseAlunoComTelefoneTask {
    private final AlunoDAO alunoDAO;
    private final TelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private final Telefone fixo;
    private final Telefone celular;

    public SalvaAlunoTask(AlunoDAO alunoDAO, TelefoneDAO telefoneDAO, Aluno aluno, Telefone fixo, Telefone celular, FinalizadaListener listener) {
        super(listener);
        this.alunoDAO = alunoDAO;
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.fixo = fixo;
        this.celular = celular;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int alunoID = alunoDAO.salvarAluno(aluno).intValue();
        vinculaAlunoComTelefone(alunoID, fixo, celular);
        telefoneDAO.salva(fixo, celular);
        return null;
    }

}
