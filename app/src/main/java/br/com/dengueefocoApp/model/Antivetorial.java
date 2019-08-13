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
	private Boolean imovelFoco;
	private String quadra;
	private String lote;
	private String numero;
	private String logradouro;
	private String observacao;
	private String numQuarto;
	private String lado;
	private String cilo;
	private String distrito;
	private String pendencia;
	private String qtdFoco;
	private String qtdEliminado;

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

	public String getNumQuarto() {
		return numQuarto;
	}

	public void setNumQuarto(String numQuarto) {
		this.numQuarto = numQuarto;
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

	public String getQtdEliminado() {
		return qtdEliminado;
	}

	public void setQtdEliminado(String qtdEliminado) {
		this.qtdEliminado = qtdEliminado;
	}
}

