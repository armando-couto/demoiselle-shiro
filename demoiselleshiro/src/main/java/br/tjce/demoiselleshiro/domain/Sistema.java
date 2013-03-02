package br.tjce.demoiselleshiro.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Collections;
import java.util.List;


/**
 * The persistent class for the sistemas database table.
 * 
 */
@Entity
@Table(name="sistemas")
public class Sistema implements Serializable, Loggable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	private String codigo;

	private String descricao;

    @Lob()
	private String observacoes;

	//bi-directional many-to-one association to Erro
	@OneToMany(mappedBy="sistema")
	private List<Erro> erros;

    public Sistema() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
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

	@SuppressWarnings("unchecked")
	public List<Erro> getErros() {
		Collections.sort(this.erros);
		return this.erros;
	}

	public void setErros(List<Erro> erros) {
		this.erros = erros;
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
		Sistema other = (Sistema) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String getInfo() {		
		String info = this.getClass().toString().substring(this.getClass().toString().lastIndexOf(".")+1);
		info += " ";
		info += (this.getId() == null) ? "um novo registro" : " o registro numero " + this.getId();
		return info;
	}
	
}