package br.com.dengueefocoApp.model;

public class Usuario {

    private Long id;
    private String email;
    private String nome;
    private String senha;
    private String matricula;
    private TipoUsuario tipoUsuario;

    public Usuario() {
    }

    public Usuario(Long id, String email, String nome, String senha, String matricula, TipoUsuario tipoUsuario) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.matricula = matricula;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}