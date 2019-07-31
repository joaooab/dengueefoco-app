package br.com.dengueefocoApp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "antivetorial")
public class Antivetorial {
    @PrimaryKey()
    private Long id;
    @ColumnInfo(name = "id_usuario")
    private Long idUsuario;
    @ColumnInfo(name = "data_visita")
    private String dataVisita;
    @ColumnInfo(name = "larvicida")
    private String larvicida;
    @ColumnInfo(name = "qtd_larvicida")
    private Double qtdLarvicida;
    @ColumnInfo(name = "status_imovel")
    private String statusImovel;
    @ColumnInfo(name = "tipo_imovel")
    private String tipoImovel;
    @ColumnInfo(name = "regiao")
    private String regiao;
    @ColumnInfo(name = "longitude")
    private Double longitude;
    @ColumnInfo(name = "latitude")
    private Double latitude;

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

    public String getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(String dataVisita) {
        this.dataVisita = dataVisita;
    }

    public String getLarvicida() {
        return larvicida;
    }

    public void setLarvicida(String larvicida) {
        this.larvicida = larvicida;
    }

    public Double getQtdLarvicida() {
        return qtdLarvicida;
    }

    public void setQtdLarvicida(Double qtdLarvicida) {
        this.qtdLarvicida = qtdLarvicida;
    }

    public String getStatusImovel() {
        return statusImovel;
    }

    public void setStatusImovel(String statusImovel) {
        this.statusImovel = statusImovel;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}

