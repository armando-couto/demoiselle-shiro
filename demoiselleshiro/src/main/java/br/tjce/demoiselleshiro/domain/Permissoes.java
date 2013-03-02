package br.tjce.demoiselleshiro.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the permissoes database table.
 * 
 */
@Entity
@Table(name="permissoes")
public class Permissoes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	private String permissao;

	//bi-directional many-to-many association to EntidadesHasPerfi
    @ManyToMany
	@JoinTable(
		name="permissoes_has_entidades_has_perfis"
		, joinColumns={
			@JoinColumn(name="permissoes_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="entidades_has_perfis_entidades_id", referencedColumnName="entidades_id"),
			@JoinColumn(name="entidades_has_perfis_perfis_id", referencedColumnName="perfis_id")
			}
		)
	private List<EntidadesHasPerfi> entidadesHasPerfis;

    public Permissoes() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermissao() {
		return this.permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public List<EntidadesHasPerfi> getEntidadesHasPerfis() {
		return this.entidadesHasPerfis;
	}

	public void setEntidadesHasPerfis(List<EntidadesHasPerfi> entidadesHasPerfis) {
		this.entidadesHasPerfis = entidadesHasPerfis;
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
		Permissoes other = (Permissoes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}