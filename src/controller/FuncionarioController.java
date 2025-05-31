package controller;

import model.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {
    private List<Funcionario> funcionarios = new ArrayList<>();

    public void cadastrarFuncionario(String nome, String cpf, String email, String telefone, String cargo) {
        Funcionario f = new Funcionario(nome, cpf, email, telefone, cargo);
        funcionarios.add(f);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            System.out.println("Lista de Funcionários:");
            for (int i = 0; i < funcionarios.size(); i++) {
                System.out.println((i + 1) + ". " + funcionarios.get(i));
            }
        }
    }

    public void removerFuncionario(int indice) {
        if (indice >= 0 && indice < funcionarios.size()) {
            funcionarios.remove(indice);
            System.out.println("Funcionário removido com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}
