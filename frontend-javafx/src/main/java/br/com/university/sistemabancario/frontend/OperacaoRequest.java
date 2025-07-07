package br.com.university.sistemabancario.frontend;


public class OperacaoRequest {
    private Long contaId;
    private double valor;


    public Long getContaId() {
        return contaId;
    }

    public void setContaId(Long contaId) {
        this.contaId = contaId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}