package br.com.university.sistemabancario.backend;

public class LoginRequest {
    private String login;
    private String senha;
    private String data;
    private String hora;
    private String codigo;

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
}