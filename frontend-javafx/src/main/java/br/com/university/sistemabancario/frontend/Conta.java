package br.com.university.sistemabancario.frontend;

public class Conta {
    private Long id;
    // O nome do campo no DTO deve ser idêntico ao nome do método get no back-end
    // O back-end tem um método getTipo(), então o campo aqui deve ser "tipo".
    private String tipo; 
    private double saldo;

    // Construtor padrão (às vezes exigido pelo Jackson)
    public Conta() {
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
}