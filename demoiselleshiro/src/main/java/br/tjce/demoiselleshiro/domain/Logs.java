package br.tjce.demoiselleshiro.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the logs database table.
 * 
 */
@Entity
@Table(name="logs")
public class Logs implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

	private int codigoerro;

	private String codigosistema;

    @Temporal( TemporalType.DATE)
	private Date data;

	private String descricaoerro;

	private String descricaosistema;

	private String operacao;

	private String usertype;

	private String usuario;

    public Logs() {
    }
    
	public Logs(int codigoerro, String codigosistema, Date data,
			String descricaoerro, String descricaosistema, String operacao,
			String usertype, String usuario) {
		super();
		this.codigoerro = codigoerro;
		this.codigosistema = codigosistema;
		this.data = data;
		this.descricaoerro = descricaoerro;
		this.descricaosistema = descricaosistema;
		this.operacao = operacao;
		this.usertype = usertype;
		this.usuario = usuario;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCodigoerro() {
		return this.codigoerro;
	}

	public void setCodigoerro(int codigoerro) {
		this.codigoerro = codigoerro;
	}

	public String getCodigosistema() {
		return this.codigosistema;
	}

	public void setCodigosistema(String codigosistema) {
		this.codigosistema = codigosistema;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricaoerro() {
		return this.descricaoerro;
	}

	public void setDescricaoerro(String descricaoerro) {
		this.descricaoerro = descricaoerro;
	}

	public String getDescricaosistema() {
		return this.descricaosistema;
	}

	public void setDescricaosistema(String descricaosistema) {
		this.descricaosistema = descricaosistema;
	}

	public String getOperacao() {
		return this.operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}