package br.com.university.sistemabancario.frontend;
public class OperacaoRequest {
    private String tipoConta;
    private double valor;
    // Crie os getters e setters
    public String getTipoConta() { return tipoConta; }
    public void setTipoConta(String tipoConta) { this.tipoConta = tipoConta; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
}