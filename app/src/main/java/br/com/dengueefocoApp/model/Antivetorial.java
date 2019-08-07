package br.com.dengueefocoApp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
    @ColumnInfo(name = "setor")
    private String setor;
    @ColumnInfo(name = "longitude")
    private Double longitude;
    @ColumnInfo(name = "latitude")
    private Double latitude;
    private String status;
    private Boolean notificado;
    private String quadra;
    private String lote;
    private String numero;
    private String logradouro;
    private String observacao;

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

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
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

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

}

