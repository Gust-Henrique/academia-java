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

    public String cadastrarPlano(String nome, double preco, Funcionario responsavel) {
        Plano plano = new Plano(nome, preco);
        plano.setResponsavel(responsavel);
        planos.add(plano);
        salvarDados();
        registrarLog("Plano cadastrado: " + plano.getNome() + " - Responsável: " + responsavel.getNome());
        return "Plano cadastrado com sucesso!";
    }

    public String listarPlanos() {
        if (planos.isEmpty()) {
            return "Nenhum plano cadastrado.";
    
        }

        for (int i = 0; i < planos.size(); i++) {
            return ((i + 1) + ". " + planos.get(i));
        }

        return "Lista de planos!";
    }

    public String removerPlano(int indice) {
        if (indice >= 0 && indice < planos.size()) {
            Plano removido = planos.remove(indice);
            salvarDados();
            registrarLog("Plano removido: " + removido.getNome());
            return "Plano removido com sucesso!";
        } else {
            return "Índice inválido.";
        }
    }

    public Plano buscarPlanoPorIndice(int indice) {
        if (indice >= 0 && indice < planos.size()) {
            return planos.get(indice);
        }
        return null;
    }

    private String salvarDados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(planos);
        } catch (IOException e) {
            return "Erro ao salvar dados de planos.";
        }
        return "Dados salvos com sucesso";
    }

    private String carregarDados() {
        File file = new File(arquivo);
        if (!file.exists()) return "Arquivo inexistente";

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            planos = (List<Plano>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return "Erro ao carregar dados de planos.";
        }

        return "Dados carregados com sucesso";
    }

    private String registrarLog(String mensagem) {
        try (FileWriter fw = new FileWriter(log, true)) {
            fw.write(mensagem + "\n");
        } catch (IOException e) {
            return ("Erro ao escrever no log.");
        }
        return "Log registrado com sucesso!";
    }

    public List<Plano> getPlanos() {
        return planos;
    }


}
