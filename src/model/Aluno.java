package model;

import java.io.Serializable;

public class Aluno extends Pessoa implements Serializable {
    private String matricula;
    private Plano plano;

    public Aluno(String nome, String cpf, String email, String telefone, String matricula, Plano plano) {
        super(nome, cpf, email, telefone);
        this.matricula = matricula;
        this.plano = plano;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public Plano getPlano() { return plano; }
    public void setPlano(Plano plano) { this.plano = plano; }

    @Override
    public String toString() {
        return "Aluno: " + getNome() + " | Matrícula: " + matricula + " | Plano: " + plano.getNome();
    }
}
