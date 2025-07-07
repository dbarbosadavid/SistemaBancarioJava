package br.com.university.sistemabancario.frontend;

import java.util.List;

public class Usuario {
    private Long id;
    private String login;
    // O nome do campo "contas" deve ser idêntico ao que está no JSON
    private List<Conta> contas;

    // Construtor padrão
    public Usuario() {
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public List<Conta> getContas() { return contas; }
    public void setContas(List<Conta> contas) { this.contas = contas; }
}