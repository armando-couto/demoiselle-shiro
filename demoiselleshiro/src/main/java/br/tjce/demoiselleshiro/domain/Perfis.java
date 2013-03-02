package br.tjce.demoiselleshiro.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;
/**
 * The persistent class for the perfis database table.
 * 
 */
@Entity
@Table(name="perfis")
public class Perfis implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	private String perfil;

	//bi-directional many-to-many association to Entidade
    @ManyToMany
	@JoinTable(
		name="entidades_has_perfis"
		, joinColumns={
			@JoinColumn(name="perfis_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="entidades_id")
			}
		)
	private List<Entidade> entidades;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="perfis")
	private List<Usuario> usuarios;

    public Perfis() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public List<Entidade> getEntidades() {
		return this.entidades;
	}

	public void setEntidades(List<Entidade> entidades) {
		this.entidades = entidades;
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
		Perfis other = (Perfis) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}