package br.tjce.demoiselleshiro.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the entidades_has_perfis database table.
 * 
 */
@Entity
@Table(name="entidades_has_perfis")
public class EntidadesHasPerfi implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EntidadesHasPerfiPK id;

	//bi-directional many-to-many association to Permissoes
	@ManyToMany(mappedBy="entidadesHasPerfis")
	private List<Permissoes> permissoes;

    public EntidadesHasPerfi() {
    }

	public EntidadesHasPerfi(EntidadesHasPerfiPK id) {
		super();
		this.id = id;
	}

	public EntidadesHasPerfiPK getId() {
		return this.id;
	}

	public void setId(EntidadesHasPerfiPK id) {
		this.id = id;
	}
	
	public List<Permissoes> getPermissoes() {
		return this.permissoes;
	}

	public void setPermissoes(List<Permissoes> permissoes) {
		this.permissoes = permissoes;
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
		EntidadesHasPerfi other = (EntidadesHasPerfi) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}