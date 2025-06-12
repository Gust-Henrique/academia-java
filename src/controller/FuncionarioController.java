package controller;

import model.Funcionario;
import util.Serializador;
import util.Log;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {
    private List<Funcionario> funcionarios;
    private final String ARQUIVO = "funcionarios.dat";

    public FuncionarioController() {
        funcionarios = Serializador.carregarFuncionarios(ARQUIVO);
    }

    public void cadastrarFuncionario(String nome, String cpf, String email, String telefone, String cargo) {
        Funcionario f = new Funcionario(nome, cpf, email, telefone, cargo);
        funcionarios.add(f);
        Serializador.salvarFuncionarios(funcionarios, ARQUIVO);
        Log.registrar("Funcionário cadastrado: " + nome);
        System.out.println(" Funcionário cadastrado com sucesso!");
    }

    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println(" Nenhum funcionário cadastrado.");
        } else {
            System.out.println(" Lista de Funcionários:");
            for (int i = 0; i < funcionarios.size(); i++) {
                System.out.println((i + 1) + ". " + funcionarios.get(i));
            }
        }
    }

    public void removerFuncionario(int indice) {
        if (indice >= 0 && indice < funcionarios.size()) {
            String nomeRemovido = funcionarios.get(indice).getNome();
            funcionarios.remove(indice);
            Serializador.salvarFuncionarios(funcionarios, ARQUIVO);
            Log.registrar("Funcionário removido: " + nomeRemovido);
            System.out.println(" Funcionário removido com sucesso.");
        } else {
            System.out.println(" Índice inválido.");
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
