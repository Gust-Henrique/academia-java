package model;

public class TreinoCardio extends Treino {
    public TreinoCardio(String descricao) {
        super(descricao);
    }

    @Override
    public void registrarExercicio() {
        System.out.println("Registrando exercício de cardio: " + descricao);
    }
}
