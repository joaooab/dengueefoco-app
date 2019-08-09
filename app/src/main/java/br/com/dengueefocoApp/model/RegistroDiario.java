package br.com.dengueefocoApp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "registro_diario")
public class RegistroDiario {

    @PrimaryKey ()
    private Long id;
    private Long idUsuario;
    private String distrito;
    private String bairro;
    private String larvicida;
    private String supervisor;
    private String ciclo;
    private String data;

    public RegistroDiario(Long idUsuario, String distrito, String bairro, String larvicida, String supervisor, String ciclo, String data) {
        this.idUsuario = idUsuario;
        this.distrito = distrito;
        this.bairro = bairro;
        this.larvicida = larvicida;
        this.supervisor = supervisor;
        this.ciclo = ciclo;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLarvicida() {
        return larvicida;
    }

    public void setLarvicida(String larvicida) {
        this.larvicida = larvicida;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
