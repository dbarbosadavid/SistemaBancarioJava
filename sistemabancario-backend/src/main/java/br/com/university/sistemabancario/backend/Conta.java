package br.com.university.sistemabancario.backend;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING)
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double saldo;

    @JsonBackReference
    @ManyToOne
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimento> movimentos = new ArrayList<>();

    // Métodos de negócio abstratos
    public abstract void depositar(double valor);
    public abstract void sacar(double valor);
    public abstract String getTipo();

    // --- VERIFIQUE SE TODOS ESTES MÉTODOS GETTERS E SETTERS ESTÃO AQUI ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    // Este é o método que provavelmente está faltando
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Movimento> getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(List<Movimento> movimentos) {
        this.movimentos = movimentos;
    }
}