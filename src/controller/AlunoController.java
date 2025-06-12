package controller;

import model.Aluno;
import model.Plano;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoController {
    private List<Aluno> alunos = new ArrayList<>();
    private final String arquivo = "alunos.dat";
    private final String log = "log.txt";

    public AlunoController() {
        carregarDados();
    }
public void cadastrarAluno(String nome, String cpf, String email, String telefone, String matricula, Plano plano) {
        if (existeCpf(cpf)) {
            System.out.println("Já existe um aluno com esse CPF!");
            return;
        }

        Aluno aluno = new Aluno(nome, cpf, email, telefone, matricula, plano);
        alunos.add(aluno);
        salvarDados();
        registrarLog("Aluno cadastrado: " + nome + " | CPF: " + cpf);
    }

    public void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        for (int i = 0; i < alunos.size(); i++) {
            System.out.println((i + 1) + ". " + alunos.get(i));
        }
    }

    public void removerAluno(int indice) {
        if (indice >= 0 && indice < alunos.size()) {
            Aluno removido = alunos.remove(indice);
            salvarDados();
            registrarLog("Aluno removido: " + removido.getNome());
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public boolean existeCpf(String cpf) {
        return alunos.stream().anyMatch(a -> a.getCpf().equals(cpf));
    }

    private void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(alunos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados de alunos.");
        }
    }
private void carregarDados() {
        File file = new File(arquivo);
        if (!file.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            alunos = (List<Aluno>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados de alunos.");
        }
    }

    private void registrarLog(String mensagem) {
        try (FileWriter fw = new FileWriter(log, true)) {
            fw.write(mensagem + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no log.");
        }
    }
}

