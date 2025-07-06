package br.com.university.sistemabancario.backend;

// Representa o JSON que o Front End vai nos enviar
public class OperacaoRequest {
    private Long contaId;
    private String tipoConta;
    private double valor;

    // Getters e Setters
    public Long getContaId() { return contaId; }
    public void setContaId(Long contaId) { this.contaId = contaId; }
    public String getTipoConta() { return tipoConta; }
    public void setTipoConta(String tipoConta) { this.tipoConta = tipoConta; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
}