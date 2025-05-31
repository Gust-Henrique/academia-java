package model;

public class TreinoHipertrofia extends Treino {
    public TreinoHipertrofia(String descricao) {
        super(descricao);
    }

    @Override
    public void registrarExercicio() {
        System.out.println("Registrando exercício de hipertrofia: " + descricao);
    }
}