package br.tjce.demoiselleshiro.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the entidades_has_perfis database table.
 * 
 */
@Embeddable
public class EntidadesHasPerfiPK implements Serializable {
	
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="entidades_id")
	private int entidadesId;

	@Column(name="perfis_id")
	private int perfisId;

    public EntidadesHasPerfiPK() {
    }
    
	public EntidadesHasPerfiPK(int entidadesId, int perfisId) {
		super();
		this.entidadesId = entidadesId;
		this.perfisId = perfisId;
	}

	public int getEntidadesId() {
		return this.entidadesId;
	}
	public void setEntidadesId(int entidadesId) {
		this.entidadesId = entidadesId;
	}
	public int getPerfisId() {
		return this.perfisId;
	}
	public void setPerfisId(int perfisId) {
		this.perfisId = perfisId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EntidadesHasPerfiPK)) {
			return false;
		}
		EntidadesHasPerfiPK castOther = (EntidadesHasPerfiPK)other;
		return 
			(this.entidadesId == castOther.entidadesId)
			&& (this.perfisId == castOther.perfisId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.entidadesId;
		hash = hash * prime + this.perfisId;
		
		return hash;
    }
}