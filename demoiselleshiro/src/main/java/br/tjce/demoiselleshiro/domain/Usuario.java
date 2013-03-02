package br.tjce.demoiselleshiro.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable,Loggable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;

	private String userlogin;

	private String username;

	private String userpassword;

	//bi-directional many-to-many association to Perfi
    @ManyToMany
	@JoinTable(
		name="perfis_has_usuarios"
		, joinColumns={
			@JoinColumn(name="usuarios_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="perfis_id")
			}
		)
	private List<Perfis> perfis;

    public Usuario() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserlogin() {
		return this.userlogin;
	}

	public void setUserlogin(String userlogin) {
		this.userlogin = userlogin;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return this.userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public List<Perfis> getPerfis() {
		return this.perfis;
	}

	public void setPerfis(List<Perfis> perfis) {
		this.perfis = perfis;
	}
	
	@Override
	public String getInfo() {		
		String info = this.getClass().toString().substring(this.getClass().toString().lastIndexOf(".")+1);
		info += " ";
		info += (this.getId() == 0) ? "um novo registro" : " o registro numero " + this.getId();
		return info;
	}
	
}