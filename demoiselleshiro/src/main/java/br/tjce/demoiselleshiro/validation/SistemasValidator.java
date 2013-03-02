package br.tjce.demoiselleshiro.validation;

import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import br.gov.frameworkdemoiselle.util.Beans;
import br.tjce.demoiselleshiro.business.SistemasBC;

@Named("sistemasValidator")
@RequestScoped
public class SistemasValidator {

	ResourceBundle bundle = ResourceBundle.getBundle("validations");

	public void validateCode(FacesContext context, UIComponent component,
			Object object) throws ValidatorException {

		String value = String.valueOf(object);

		if (value.trim().length() > 4) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, bundle.getString("sistemas.code.maxlength"),
					bundle.getString("sistemas.code.maxlength")));
		}

		if (value.trim().length() < 4) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, bundle.getString("sistemas.code.minlength"),
					bundle.getString("sistemas.code.minlength")));
		}

		HtmlInputHidden isUpdateMode = (HtmlInputHidden) context.getViewRoot()
				.findComponent("formCadastroSistemas:updatemode");

		if (!isUpdateMode.getValue().toString().equals("true")) {
			if (sistemasBC().checkIfExist(value)) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						bundle.getString("sistemas.code.inuse")));
			}
		}

	}

	public void validateDescricao(FacesContext context, UIComponent component,
			Object object) throws ValidatorException {

		String value = String.valueOf(object);

		if (value.length() > 0 && value.trim().length() == 0) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "",
					bundle.getString("sistemas.description.required")));
		}

	}

	private SistemasBC sistemasBC() {
		return Beans.getReference(SistemasBC.class);
	}

}
