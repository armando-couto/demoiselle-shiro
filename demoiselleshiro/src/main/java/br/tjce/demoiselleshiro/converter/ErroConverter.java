package br.tjce.demoiselleshiro.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.frameworkdemoiselle.util.Beans;
import br.tjce.demoiselleshiro.business.ErrosBC;
import br.tjce.demoiselleshiro.domain.Erro;

@FacesConverter(forClass = Erro.class)
public class ErroConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {

		if (string != null && !string.trim().isEmpty()) {
			try {
				Long id = Long.parseLong(string);
				return errosBC().load(id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {

		if (objeto != null && objeto instanceof Erro) {
			Erro erro = (Erro) objeto;
			if (erro.getId() != 0) {
				return String.valueOf(erro.getId());
			}
		}
		return null;
	}

	private ErrosBC errosBC() {
		return Beans.getReference(ErrosBC.class);
	}
}