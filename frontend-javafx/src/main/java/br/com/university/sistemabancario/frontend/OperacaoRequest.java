package br.com.university.sistemabancario.frontend;

/**
 * Representa os dados que o Front End envia para o Back End
 * para realizar uma operação financeira.
 */
public class OperacaoRequest {
    private Long contaId;
    private double valor;

    // Getters e Setters para que o Controller possa preencher os dados
    // e a biblioteca Jackson possa lê-los para criar o JSON.

    public Long getContaId() {
        return contaId;
    }

    // Este é o método que está faltando no seu arquivo
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