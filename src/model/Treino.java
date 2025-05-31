package model;

import java.io.Serializable;

public abstract class Treino implements Serializable {
    protected String descricao;

    public Treino(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public abstract void registrarExercicio();
}