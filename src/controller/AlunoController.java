package controller;

import model.Aluno;
import model.Plano;
import util.Serializador;
import util.Log;

import java.util.ArrayList;
import java.util.List;

public class AlunoController {
    private List<Aluno> alunos;
    private final String ARQUIVO = "alunos.dat";

    public AlunoController() {
        alunos = Serializador.carregarAlunos(ARQUIVO);
    }

    public void cadastrarAluno(String nome, String cpf, String email, String telefone, String matricula, Plano plano) {
        Aluno aluno = new Aluno(nome, cpf, email, telefone, matricula, plano);
        alunos.add(aluno);
        Serializador.salvarAlunos(alunos, ARQUIVO);
        Log.registrar("Aluno cadastrado: " + nome);
        System.out.println(" Aluno cadastrado com sucesso!");
    }

    public void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println(" Nenhum aluno cadastrado.");
        } else {
            System.out.println(" Lista de Alunos:");
            for (int i = 0; i < alunos.size(); i++) {
                System.out.println((i + 1) + ". " + alunos.get(i));
            }
        }
    }

    public void removerAluno(int indice) {
        if (indice >= 0 && indice < alunos.size()) {
            String nomeRemovido = alunos.get(indice).getNome();
            alunos.remove(indice);
            Serializador.salvarAlunos(alunos, ARQUIVO);
            Log.registrar("Aluno removido: " + nomeRemovido);
            System.out.println(" Aluno removido com sucesso.");
        } else {
            System.out.println(" Índice inválido.");
        }
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
}
