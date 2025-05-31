package controller;

import model.Plano;
import util.Serializador;
import util.Log;

import java.util.ArrayList;
import java.util.List;

public class PlanoController {
    private List<Plano> planos;
    private final String ARQUIVO = "planos.dat";

    public PlanoController() {
        planos = Serializador.carregarPlanos(ARQUIVO);
    }

    public void cadastrarPlano(String nome, double preco) {
        Plano plano = new Plano(nome, preco);
        planos.add(plano);
        Serializador.salvarPlanos(planos, ARQUIVO);
        Log.registrar("Plano cadastrado: " + nome);
        System.out.println(" Plano cadastrado com sucesso!");
    }

    public void listarPlanos() {
        if (planos.isEmpty()) {
            System.out.println(" Nenhum plano cadastrado.");
        } else {
            System.out.println(" Lista de Planos:");
            for (int i = 0; i < planos.size(); i++) {
                System.out.println((i + 1) + ". " + planos.get(i));
            }
        }
    }

    public void removerPlano(int indice) {
        if (indice >= 0 && indice < planos.size()) {
            String nomeRemovido = planos.get(indice).getNome();
            planos.remove(indice);
            Serializador.salvarPlanos(planos, ARQUIVO);
            Log.registrar("Plano removido: " + nomeRemovido);
            System.out.println(" Plano removido com sucesso.");
        } else {
            System.out.println(" Índice inválido.");
        }
    }

    public Plano buscarPlanoPorIndice(int indice) {
        if (indice >= 0 && indice < planos.size()) {
            return planos.get(indice);
        }
        return null;
    }

    public List<Plano> getPlanos() {
        return planos;
    }
}
