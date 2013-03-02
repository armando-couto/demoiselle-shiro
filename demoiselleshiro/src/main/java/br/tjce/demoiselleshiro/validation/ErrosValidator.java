package br.tjce.demoiselleshiro.validation;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import br.gov.frameworkdemoiselle.util.Beans;
import br.tjce.demoiselleshiro.business.ErrosBC;
import br.tjce.demoiselleshiro.domain.Sistema;

@Named("errosValidator")
@RequestScoped
public class ErrosValidator {

	ResourceBundle bundle = ResourceBundle.getBundle("validations");

	public void validateCodigo(FacesContext context, UIComponent component,
			Object object) throws ValidatorException {

		if (object != null) {
			String value = String.valueOf(object);
			System.out.println(value);
			
			try{
				if (Integer.parseInt(value) == 0 ) {
					throw new ValidatorException(new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "",
							bundle.getString("erros.code.invalid")));
				}
			}catch(NumberFormatException e){
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						bundle.getString("erros.code.invalid")));
			}

			if (value.trim().length() > 4) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						bundle.getString("erros.code.maxlength")));
			}

			HtmlSelectOneMenu selectSistema = (HtmlSelectOneMenu) context.getViewRoot().findComponent("formCadastroErros:selectSistema");

			HtmlInputHidden id = (HtmlInputHidden) context.getViewRoot()
					.findComponent("formCadastroErros:erroid");

			if (selectSistema.getValue() != null) {
				Sistema sistema = (Sistema) selectSistema.getValue();
				// TESTE SE EXISTE ALGUM VALOR SETADO PARA ID, SE EXISTIR ELE
				// ESTA
				// EM MODO UPDATE
				if (id.getValue() == null) {
					if (errosBC().checkIfCodeExist(Integer.parseInt(value),
							sistema)) {
						throw new ValidatorException(new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "",
								bundle.getString("erros.code.inuse")));
					}
				} else {
					Integer idInteger = 0;
//					System.out.println(id.getValue().toString());
					try {
						idInteger = Integer.parseInt(String.valueOf(id
								.getValue()));
					} catch (Exception e) {
						e.printStackTrace();
						throw new ValidatorException(new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "",
								bundle.getString("erros.code.invalidIdValue")));
					}
					if (errosBC().checkIfCodeExist(Integer.parseInt(value),
							sistema, idInteger)) {
						throw new ValidatorException(new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "",
								bundle.getString("erros.code.inuse")));
					}
				}
			}
		} else {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "",
					bundle.getString("erros.code.invalidIdValue")));
		}
//		throw new ValidatorException(new FacesMessage(
//				FacesMessage.SEVERITY_ERROR, "",
//				bundle.getString("erros.code.required")));
	}

	public void validateDescricao(FacesContext context, UIComponent component,
			Object object) throws ValidatorException {

		String value = String.valueOf(object);

		if (value.length() > 0) {
			if (value.trim().length() == 0) {
				throw new ValidatorException(new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "",
						bundle.getString("erros.descricao.required")));
			}
		}

	}

	public void validateRazao(FacesContext context, UIComponent component,
			Object object) throws ValidatorException {

		String value = String.valueOf(object);

		// POR PADRAO O CAMPO P:EDITOR ADICIONA UM BR <BR> 4 CARACTERES
		// TESTE SE O CAMPO É MAIOR QUE 4 CARACTERS (NÃO ESTA EM BRANCO)
		if (value.trim().length() <= 4) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "",
					bundle.getString("erros.razao.required")));
		}

	}

	public void validateAcao(FacesContext context, UIComponent component,
			Object object) throws ValidatorException {

		String value = String.valueOf(object);

		// POR PADRAO O CAMPO P:EDITOR ADICIONA UM BR <BR> 4 CARACTERES
		// TESTE SE O CAMPO É MAIOR QUE 4 CARACTERS (NÃO ESTA EM BRANCO)
		if (value.trim().length() <= 4) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "",
					bundle.getString("erros.acao.required")));
		}

	}

	// INJEÇÃO DO BEAN ErrosBC
	private ErrosBC errosBC() {
		return Beans.getReference(ErrosBC.class);
	}

}
