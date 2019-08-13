package br.com.dengueefocoApp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity (tableName = "bairro")
public class Bairro {

    @PrimaryKey()
    private Long id;
    private String nome;
    private String distrito;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bairro bairro = (Bairro) o;
        return Objects.equals(nome, bairro.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
