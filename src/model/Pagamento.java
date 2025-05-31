package model;

public class Pagamento {
    public void realizarPagamento(double valor) {
        System.out.println("Pagamento realizado: R$" + valor);
    }

    public void realizarPagamento(double valor, double desconto) {
        double total = valor - desconto;
        System.out.println("Pagamento com desconto: R$" + total);
    }
}