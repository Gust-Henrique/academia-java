package model;

import java.io.Serializable;

public class Funcionario extends Pessoa implements Serializable {
    private String cargo;

    public Funcionario(String nome, String cpf, String email, String telefone, String cargo) {
        super(nome, cpf, email, telefone);
        this.cargo = cargo;
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    @Override
    public String toString() {
        return "Funcionário: " + getNome() + " | Cargo: " + cargo;
    }
}
