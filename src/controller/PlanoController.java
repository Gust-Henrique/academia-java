/*package controller;

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
*/
package controller;

import model.Funcionario;
import model.Plano;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlanoController {
    private List<Plano> planos = new ArrayList<>();
    private final String arquivo = "planos.dat";
    private final String log = "log.txt";

    public PlanoController() {
        carregarDados();
    }

    public void cadastrarPlano(String nome, double preco, Funcionario responsavel) {
        Plano plano = new Plano(nome, preco);
        plano.setResponsavel(responsavel);
        planos.add(plano);
        salvarDados();
        registrarLog("Plano cadastrado: " + plano.getNome() + " - Responsável: " + responsavel.getNome());
    }

    public void listarPlanos() {
        if (planos.isEmpty()) {
            System.out.println("Nenhum plano cadastrado.");
            return;
        }

        for (int i = 0; i < planos.size(); i++) {
            System.out.println((i + 1) + ". " + planos.get(i));
        }
    }

    public void removerPlano(int indice) {
        if (indice >= 0 && indice < planos.size()) {
            Plano removido = planos.remove(indice);
            salvarDados();
            registrarLog("Plano removido: " + removido.getNome());
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public Plano buscarPlanoPorIndice(int indice) {
        if (indice >= 0 && indice < planos.size()) {
            return planos.get(indice);
        }
        return null;
    }

    private void salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(planos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados de planos.");
        }
    }

    private void carregarDados() {
        File file = new File(arquivo);
        if (!file.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            planos = (List<Plano>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados de planos.");
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
