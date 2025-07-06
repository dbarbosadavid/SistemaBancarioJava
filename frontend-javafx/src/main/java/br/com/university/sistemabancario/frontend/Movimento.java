package br.com.university.sistemabancario.frontend;

import java.time.LocalDateTime;

public class Movimento {
    private Long id;
	private String tipo;
    private double valor;
    private LocalDateTime data;

    public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}
    public double getValor() {return valor;}
    public void setValor(double valor) {this.valor = valor;}
    public LocalDateTime getData() {return data;}
    public void setData(LocalDateTime data) {this.data = data;}
    
}