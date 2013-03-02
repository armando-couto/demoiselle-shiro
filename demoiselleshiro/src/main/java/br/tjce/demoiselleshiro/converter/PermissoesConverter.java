package br.tjce.demoiselleshiro.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.frameworkdemoiselle.util.Beans;
import br.tjce.demoiselleshiro.business.PermissoesBC;
import br.tjce.demoiselleshiro.domain.Permissoes;

@FacesConverter(forClass = Permissoes.class, value= "permissoesConverter")
public class PermissoesConverter implements Converter,Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Permissoes getAsObject(FacesContext arg0, UIComponent component, String string) {
		try {
			if (string != null && !string.trim().isEmpty()) {
				Integer id = Integer.parseInt(string);				
				return (Permissoes) permissoesBC().load(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		try {
			if (objeto != null && objeto instanceof Permissoes) {
				Permissoes permissoes = (Permissoes) objeto;
				if (permissoes.getId() != null) {
					return permissoes.getId().toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private PermissoesBC permissoesBC() {
		return Beans.getReference(PermissoesBC.class);
	}

}
