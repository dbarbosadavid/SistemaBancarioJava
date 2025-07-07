package br.com.university.sistemabancario.frontend;

public class TransferenciaRequest {
    private Long idContaOrigem;
    private Long idContaDestino;
    private double valor;


    public Long getIdContaOrigem() { return idContaOrigem; }
    public void setIdContaOrigem(Long idContaOrigem) { this.idContaOrigem = idContaOrigem; }
    public Long getIdContaDestino() { return idContaDestino; }
    public void setIdContaDestino(Long idContaDestino) { this.idContaDestino = idContaDestino; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
}