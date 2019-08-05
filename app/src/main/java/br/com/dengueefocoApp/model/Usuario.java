package br.com.dengueefocoApp.model;

public class Usuario {

    private String email;
    private String nome;
    private String password;
    private TipoUsuario TipoUsuario;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public br.com.dengueefocoApp.model.TipoUsuario getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(br.com.dengueefocoApp.model.TipoUsuario tipoUsuario) {
        TipoUsuario = tipoUsuario;
    }
}
