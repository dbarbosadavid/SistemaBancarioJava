package br.com.university.sistemabancario.frontend;
public class OperacaoResponse {
    private String mensagem;
    private String tipoConta;
    private double novoSaldo;
    // Crie um construtor vazio e os getters e setters para o Jackson funcionar
    public OperacaoResponse() {}
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public String getTipoConta() { return tipoConta; }
    public void setTipoConta(String tipoConta) { this.tipoConta = tipoConta; }
    public double getNovoSaldo() { return novoSaldo; }
    public void setNovoSaldo(double novoSaldo) { this.novoSaldo = novoSaldo; }
}