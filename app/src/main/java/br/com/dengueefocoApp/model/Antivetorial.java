package br.com.dengueefocoApp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "antivetorial")
public class Antivetorial {
	@PrimaryKey()
	private Long id;
	@ColumnInfo(name = "id_usuario")
	private Long idUsuario;
	@ColumnInfo(name = "data_visita")
	private Date dataVisita;
	@ColumnInfo(name = "larvicida")
	private String larvicida;
	@ColumnInfo(name = "qtd_larvicida")
	private String qtdLarvicida;
	@ColumnInfo(name = "tipo_imovel")
	private String tipoImovel;
	@ColumnInfo(name = "longitude")
	private Double longitude;
	@ColumnInfo(name = "latitude")
	private Double latitude;
	private String status;
	private Boolean notificado;
	private Boolean imovelFoco;
	private String quadra;
	@ColumnInfo(name = "num_lote")
	private String numLote;
	private String logradouro;
	private String observacao;
    @ColumnInfo(name = "num_quarto")
	private String quarteirao;
	private String lado;
	private String cilo;
	private String distrito;
	private String pendencia;
    @ColumnInfo(name = "qtd_foco")
	private String qtdFoco;
	private String sequencia;
    @ColumnInfo(name = "qtd_dep_tratado")
	private String qtdDepTratado;
    @ColumnInfo(name = "tipo_foco")
    private String tipoFoco;
    @ColumnInfo(name = "num_dep_eliminado")
    private String numDepEliminado;
    private String bairro;

	public Antivetorial() {
		this.status = Status.AGUARDANDO.valor;
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

	public Date getDataVisita() {
		return dataVisita;
	}

	public void setDataVisita(Date dataVisita) {
		this.dataVisita = dataVisita;
	}

	public String getLarvicida() {
		return larvicida;
	}

	public void setLarvicida(String larvicida) {
		this.larvicida = larvicida;
	}

	public String getQtdLarvicida() {
		return qtdLarvicida;
	}

	public void setQtdLarvicida(String qtdLarvicida) {
		this.qtdLarvicida = qtdLarvicida;
	}

	public String getTipoImovel() {
		return tipoImovel;
	}

	public void setTipoImovel(String tipoImovel) {
		this.tipoImovel = tipoImovel;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getNotificado() {
		return notificado;
	}

	public void setNotificado(Boolean notificado) {
		this.notificado = notificado;
	}

	public String getQuadra() {
		return quadra;
	}

	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}

	public String getNumLote() {
		return numLote;
	}

	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getQuarteirao() {
		return quarteirao;
	}

	public void setQuarteirao(String quarteirao) {
		this.quarteirao = quarteirao;
	}

	public String getLado() {
		return lado;
	}

	public void setLado(String lado) {
		this.lado = lado;
	}

	public String getCilo() {
		return cilo;
	}

	public void setCilo(String cilo) {
		this.cilo = cilo;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getPendencia() {
		return pendencia;
	}

	public void setPendencia(String pendencia) {
		this.pendencia = pendencia;
	}

	public Boolean getImovelFoco() {
		return imovelFoco;
	}

	public void setImovelFoco(Boolean imovelFoco) {
		this.imovelFoco = imovelFoco;
	}

	public String getQtdFoco() {
		return qtdFoco;
	}

	public void setQtdFoco(String qtdFoco) {
		this.qtdFoco = qtdFoco;
	}

    public String getSequencia() {
        return sequencia;
    }

    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }

    public String getQtdDepTratado() {
        return qtdDepTratado;
    }

    public void setQtdDepTratado(String qtdDepTratado) {
        this.qtdDepTratado = qtdDepTratado;
    }

    public String getTipoFoco() {
        return tipoFoco;
    }

    public void setTipoFoco(String tipoFoco) {
        this.tipoFoco = tipoFoco;
    }

    public String getNumDepEliminado() {
        return numDepEliminado;
    }

    public void setNumDepEliminado(String numDepEliminado) {
        this.numDepEliminado = numDepEliminado;
    }

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

}
