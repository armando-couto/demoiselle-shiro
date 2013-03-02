package br.tjce.demoiselleshiro.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the logsacoes database table.
 * 
 */
@Entity
@Table(name="logsacoes")
public class LogsAcoes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

    @Temporal( TemporalType.DATE)
	private Date data;

    @Lob()
	private String detalhado;

	private String entidade;

	@Column(name="id_usuario")
	private int idUsuario;

	private String operacao;

	private String perfil;

	private String realizado;

	private String usuario;
	
	
	
	
    public LogsAcoes(Date data, String detalhado, String entidade,
			int idUsuario, String operacao, String perfil, String realizado,
			String usuario) {
		super();
		this.data = data;
		this.detalhado = detalhado;
		this.entidade = entidade;
		this.idUsuario = idUsuario;
		this.operacao = operacao;
		this.perfil = perfil;
		this.realizado = realizado;
		this.usuario = usuario;
	}

	public LogsAcoes() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDetalhado() {
		return this.detalhado;
	}

	public void setDetalhado(String detalhado) {
		this.detalhado = detalhado;
	}

	public String getEntidade() {
		return this.entidade;
	}

	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getOperacao() {
		return this.operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getRealizado() {
		return this.realizado;
	}

	public void setRealizado(String realizado) {
		this.realizado = realizado;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}