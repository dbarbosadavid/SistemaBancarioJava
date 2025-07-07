package br.com.university.sistemabancario.frontend;

import java.util.List;

public class Usuario {
    private Long id;
    private String login;
    private List<Conta> contas;

    public Usuario() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public List<Conta> getContas() { return contas; }
    public void setContas(List<Conta> contas) { this.contas = contas; }
}