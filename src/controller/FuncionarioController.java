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

        Funcionario funcionario = new Funcionario(nome, cpf, email, telefone, cargo);
        funcionarios.add(funcionario);
        salvarDados();
        registrarLog("Funcionário cadastrado: " + nome + " | CPF: " + cpf);
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
        } else {
            System.out.println("Índice inválido.");
        }
    }
public Funcionario buscarFuncionarioPorIndice(int indice) {
        if (indice >= 0 && indice < funcionarios.size()) {
            return funcionarios.get(indice);
        }
        return null;
    }

    public boolean existeCpf(String cpf) {
        return funcionarios.stream().anyMatch(f -> f.getCpf().equals(cpf));
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
}