package br.com.university.sistemabancario.backend;

// Representa o JSON que vamos devolver para o Front End
public class OperacaoResponse {
    private String mensagem;
    private String tipoConta;
    private double novoSaldo;

    public OperacaoResponse(String mensagem, String tipoConta, double novoSaldo) {
        this.mensagem = mensagem;
        this.tipoConta = tipoConta;
        this.novoSaldo = novoSaldo;
    }

    // Getters e Setters
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public String getTipoConta() { return tipoConta; }
    public void setTipoConta(String tipoConta) { this.tipoConta = tipoConta; }
    public double getNovoSaldo() { return novoSaldo; }
    public void setNovoSaldo(double novoSaldo) { this.novoSaldo = novoSaldo; }
}