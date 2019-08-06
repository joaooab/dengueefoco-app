package br.com.dengueefocoApp.model;

public class Usuario {

    private Integer id;
    private String email;
    private String nome;
    private String senha;
    private TipoUsuario tipoUsuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public br.com.dengueefocoApp.model.TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(br.com.dengueefocoApp.model.TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
