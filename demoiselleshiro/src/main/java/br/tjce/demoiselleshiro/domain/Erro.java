package br.tjce.demoiselleshiro.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the erros database table.
 * 
 */
@SuppressWarnings("rawtypes")
@Entity
@Table(name="erros")
public class Erro implements Serializable, Comparable,Loggable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

    @Lob()
	private String acao;

	private Integer codigo;

	private String descricao;

    @Lob()
	private String observacoes;

    @Lob()
	private String razao;

	//bi-directional many-to-one association to Sistema
    @ManyToOne
	private Sistema sistema;

    public Erro() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAcao() {
		return this.acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getRazao() {
		return this.razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public Sistema getSistema() {
		return this.sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
	
	@Override
	public int compareTo(Object object) {
		Erro e = (Erro) object;
		return getCodigo().compareTo(e.getCodigo()); 
	}
	
	@Override
	public String getInfo() {		
		String info = this.getClass().toString().substring(this.getClass().toString().lastIndexOf(".")+1);
		info += " ";
		info += (this.getId() == 0) ? "um novo registro" : " o registro numero " + this.getId();
		return info;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Erro other = (Erro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}