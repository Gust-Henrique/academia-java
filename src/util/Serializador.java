package util;

import model.Aluno;
import model.Plano;
import model.Funcionario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializador {

  
    public static void salvarAlunos(List<Aluno> lista, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println(" Erro ao salvar alunos: " + e.getMessage());
        }
    }

    public static List<Aluno> carregarAlunos(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (List<Aluno>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" Erro ao carregar alunos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salvarPlanos(List<Plano> lista, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println(" Erro ao salvar planos: " + e.getMessage());
        }
    }

    public static List<Plano> carregarPlanos(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (List<Plano>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" Erro ao carregar planos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

  
    public static void salvarFuncionarios(List<Funcionario> lista, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println(" Erro ao salvar funcionários: " + e.getMessage());
        }
    }

    public static List<Funcionario> carregarFuncionarios(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (List<Funcionario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" Erro ao carregar funcionários: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
