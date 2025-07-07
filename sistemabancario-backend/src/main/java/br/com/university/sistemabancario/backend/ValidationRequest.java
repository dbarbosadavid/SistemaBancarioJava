package br.com.university.sistemabancario.backend;

public class ValidationRequest {
    private String nome;
    private String senha;
    private String data;
    private String hora;
    private String codigo;


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
}