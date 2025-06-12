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