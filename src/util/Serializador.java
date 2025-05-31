package util;

import java.io.*;
import java.util.List;

public class Serializador {

    public static void salvarAlunos(List<model.Aluno> lista, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }

    public static List<model.Aluno> carregarAlunos(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (List<model.Aluno>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }
}