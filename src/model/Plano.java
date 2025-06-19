package model;

import java.io.Serializable;

public class Plano implements Serializable {
    private String nome;
    private double preco;
    private Funcionario responsavel;

    public Plano(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + preco +
               " | Responsável: " + (responsavel != null ? responsavel.getNome() : "Nenhum");
    }
}
