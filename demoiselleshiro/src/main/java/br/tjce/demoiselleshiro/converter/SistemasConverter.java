package br.tjce.demoiselleshiro.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.frameworkdemoiselle.util.Beans;
import br.tjce.demoiselleshiro.business.SistemasBC;
import br.tjce.demoiselleshiro.domain.Sistema;

@FacesConverter(forClass = Sistema.class)
public class SistemasConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Sistema getAsObject(FacesContext arg0, UIComponent component, String string) {
		try {
			if (string != null && !string.trim().isEmpty()) {
				Integer id = Integer.parseInt(string);
				return (Sistema) sistemasBC().load(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		try {
			if (objeto != null && objeto instanceof Sistema) {
				Sistema sistemas = (Sistema) objeto;
				if (sistemas.getId() != null) {
					return sistemas.getId().toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private SistemasBC sistemasBC() {
		return Beans.getReference(SistemasBC.class);
	}
}
