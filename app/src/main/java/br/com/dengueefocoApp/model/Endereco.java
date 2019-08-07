package br.com.dengueefocoApp.model;

public class Endereco {
	private String bairro;
	private String cep;
	private String complemento;
	private String gia;
	private String ibge;
	private String localidade;
	private String logradouro;
	private String uf;
	private String unidade;

	public Endereco(String bairro, String cep, String complemento, String gia, String ibge, String localidade, String logradouro, String uf, String unidade) {
		this.bairro = bairro;
		this.cep = cep;
		this.complemento = complemento;
		this.gia = gia;
		this.ibge = ibge;
		this.localidade = localidade;
		this.logradouro = logradouro;
		this.uf = uf;
		this.unidade = unidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
}
