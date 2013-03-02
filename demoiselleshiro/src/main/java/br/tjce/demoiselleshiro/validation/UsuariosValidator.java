package br.tjce.demoiselleshiro.validation;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import org.primefaces.component.password.Password;

import br.gov.frameworkdemoiselle.util.Beans;
import br.tjce.demoiselleshiro.business.UsuariosBC;

@Named("usuarioValidator")
@RequestScoped
public class UsuariosValidator {

	ResourceBundle bundle = ResourceBundle.getBundle("validations");

	public void validateUsername(FacesContext context, UIComponent component,
			Object object) throws ValidatorException {

		String value = String.valueOf(object);

		if (value.trim().length() > 100) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "",
					bundle.getString("usuarios.username.maxlength")));
		}
	}

	public void validateUserlogin(FacesContext context, UIComponent component,
			Object object) throws ValidatorException {

		String value = String.valueOf(object);

		if (value.trim().length() > 30) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "",
					bundle.getString("usuarios.userlogin.maxlength")));
		}
		if (!Pattern.matches("^[0-9a-zA-Z\\-\\_\\.]{1,30}$", value)) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "",
					bundle.getString("usuarios.userlogin.pattern")));
		}

		HtmlInputHidden isUpdateMode = (HtmlInputHidden) context.getViewRoot()
				.findComponent("formCadastroUsuario:updatemode");
		
		if (!isUpdateMode.getValue().toString().equals("true")) {
			if (usuariosBC().checkIfLoginExist(value)) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						bundle.getString("usuarios.userlogin.inuse")));
			}
		}

	}

	public void validateUserpassword(FacesContext context,
			UIComponent component, Object object) throws ValidatorException {

		String value = String.valueOf(object);
		if (value.trim().length() > 0) {
			if (value.trim().length() > 30) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						bundle.getString("usuarios.userpassword.maxlength")));
			}

			if (!Pattern.matches("^[0-9a-zA-Z\\-\\_\\.]{1,30}$", value)) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						bundle.getString("usuarios.userpassword.pattern")));
			}
		}

	}

	public void validatePasswordConfirm(FacesContext context,
			UIComponent component, Object object) throws ValidatorException {

		String value = String.valueOf(object);

		Password myComponent = (Password) FacesContext.getCurrentInstance()
				.getViewRoot()
				.findComponent("formCadastroUsuario:userpassword");
		String value2 = (String) myComponent.getValue();
		if (value.trim().length() > 0 || value2.trim().length() > 0) {
			if (!value.equals(value2)) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						bundle.getString("usuarios.passwordconfirm.invalid")));
			}
		}

	}

	private UsuariosBC usuariosBC() {
		return Beans.getReference(UsuariosBC.class);
	}

}
