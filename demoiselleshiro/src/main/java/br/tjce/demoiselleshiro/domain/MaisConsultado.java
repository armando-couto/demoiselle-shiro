package br.tjce.demoiselleshiro.domain;

public class MaisConsultado {
	
	
	private long totalConsultas;
	
	private String sistema;
	
	private String sistemaDescricao;
	
	private String erro;
	
	private String erroDescricao;

	public long getTotalConsultas() {
		return totalConsultas;
	}

	public void setTotalConsultas(long totalConsultas) {
		this.totalConsultas = totalConsultas;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getSistemaDescricao() {
		return sistemaDescricao;
	}

	public void setSistemaDescricao(String sistemaDescricao) {
		this.sistemaDescricao = sistemaDescricao;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getErroDescricao() {
		return erroDescricao;
	}

	public void setErroDescricao(String erroDescricao) {
		this.erroDescricao = erroDescricao;
	}
	
}
