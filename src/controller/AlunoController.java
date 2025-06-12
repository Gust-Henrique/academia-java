package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import model.Plano;

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

        if (!cpfValido(cpf)) {
            System.out.println("CPF inválido. Use o formato 000.000.000-00");
            return;
        }

        if (!emailValido(email)) {
            System.out.println("E-mail inválido.");
            return;
        }

        if (existeMatricula(matricula)) {
            System.out.println("Já existe um aluno com essa matrícula!");
            return;
        }

        if (!matriculaValida(matricula)) {
            System.out.println("Matrícula inválida. Use apenas letras e números, sem espaços.");
            return;
        }

        Aluno aluno = new Aluno(nome, cpf, email, telefone, matricula, plano);
        alunos.add(aluno);
        salvarDados();
        registrarLog("Aluno cadastrado: " + nome + " | CPF: " + cpf + " | Matrícula: " + matricula);
        System.out.println("Aluno cadastrado com sucesso!");
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
            System.out.println("Aluno removido com sucesso!");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private boolean existeCpf(String cpf) {
        return alunos.stream().anyMatch(a -> a.getCpf().equals(cpf));
    }

    private boolean existeMatricula(String matricula) {
        return alunos.stream().anyMatch(a -> a.getMatricula().equalsIgnoreCase(matricula));
    }

    private boolean cpfValido(String cpf) {
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    private boolean emailValido(String email) {
        return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    private boolean matriculaValida(String matricula) {
        return matricula.matches("^[a-zA-Z0-9]+$");
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