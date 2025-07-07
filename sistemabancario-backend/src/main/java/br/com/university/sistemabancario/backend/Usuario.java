package br.com.university.sistemabancario.backend;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    // Um usuário pode ter muitas contas.
    // mappedBy="usuario" diz que a entidade 'Conta' é a dona da relação.
    // fetch=FetchType.EAGER garante que as contas sejam carregadas junto com o usuário.
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Conta> contas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }
}