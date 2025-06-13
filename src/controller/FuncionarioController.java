package controller;

import model.Funcionario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {
    private List<Funcionario> funcionarios = new ArrayList<>();
    private final String arquivo = "funcionarios.dat";
    private final String log = "log.txt";

    public FuncionarioController() {
        carregarDados();
    }

    public void cadastrarFuncionario(String nome, String cpf, String email, String telefone, String cargo) {
        if (existeCpf(cpf)) {
            System.out.println("Já existe um funcionário com esse CPF!");
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

        Funcionario funcionario = new Funcionario(nome, cpf, email, telefone, cargo);
        funcionarios.add(funcionario);
        salvarDados();
        registrarLog("Funcionário cadastrado: " + nome + " | CPF: " + cpf);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        for (int i = 0; i < funcionarios.size(); i++) {
            System.out.println((i + 1) + ". " + funcionarios.get(i));
        }
    }

    public void removerFuncionario(int indice) {
        if (indice >= 0 && indice < funcionarios.size()) {
            Funcionario removido = funcionarios.remove(indice);
            salvarDados();
            registrarLog("Funcionário removido: " + removido.getNome());
            System.out.println("Funcionário removido com sucesso!");
        } else {
            System.out.println("Índice inválido.");
        }
    }
private boolean existeCpf(String cpf) {
        return funcionarios.stream().anyMatch(f -> f.getCpf().equals(cpf));
    }

    private boolean cpfValido(String cpf) {
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    private boolean emailValido(String email) {
        return email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    private void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(funcionarios);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados de funcionários.");
        }
    }

    private void carregarDados() {
        File file = new File(arquivo);
        if (!file.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            funcionarios = (List<Funcionario>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados de funcionários.");
        }
    }

    private void registrarLog(String mensagem) {
        try (FileWriter fw = new FileWriter(log, true)) {
            fw.write(mensagem + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao escrever no log.");
        }
    }

    public List<Funcionario> getFuncionarios() {
    return funcionarios;
}

public Funcionario buscarFuncionarioPorIndice(int indice) {
    if (indice >= 0 && indice < funcionarios.size()) {
        return funcionarios.get(indice);
    }
    return null;
}


}