package model;

public class Pagamento {
    public String realizarPagamento(double valor) {
        return "Pagamento realizado: R$" + valor;
    }

    public String realizarPagamento(double valor, double desconto) {
        double total = valor - desconto;
        return "Pagamento com desconto: R$" + total;
    }
}