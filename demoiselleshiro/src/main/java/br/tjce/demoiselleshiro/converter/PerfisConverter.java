package br.tjce.demoiselleshiro.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.frameworkdemoiselle.util.Beans;
import br.tjce.demoiselleshiro.business.PerfisBC;
import br.tjce.demoiselleshiro.domain.Perfis;

@FacesConverter (forClass = Perfis.class)
public class PerfisConverter  implements Converter, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {

		if (string != null && !string.trim().isEmpty()) {
			Integer id = Integer.parseInt(string);
			return perfilBC().load(id);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {

		if (objeto != null && objeto instanceof Perfis) {
			Perfis perfil = (Perfis) objeto;
			if (perfil.getId() != 0) {
				return String.valueOf(perfil.getId());
			}
		}
		return null;
	}
	
	private PerfisBC perfilBC() {
		return Beans.getReference(PerfisBC.class);
	}
}