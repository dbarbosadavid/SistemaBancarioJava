package br.com.university.sistemabancario.frontend;

public class Conta {
    private Long id;
    private String tipo; 
    private double saldo;

    public Conta() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
}