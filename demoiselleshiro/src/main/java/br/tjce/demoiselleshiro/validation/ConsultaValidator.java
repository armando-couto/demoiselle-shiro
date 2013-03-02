package br.tjce.demoiselleshiro.validation;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import br.gov.frameworkdemoiselle.util.Beans;
import br.tjce.demoiselleshiro.business.ConsultaBC;
import br.tjce.demoiselleshiro.business.ErrosBC;
import br.tjce.demoiselleshiro.business.SistemasBC;
import br.tjce.demoiselleshiro.domain.Sistema;

@Named("consultaValidator")
@RequestScoped
public class ConsultaValidator {

	ResourceBundle bundle = ResourceBundle.getBundle("validations");

	public void validateConsulta(FacesContext context, UIComponent component,
			Object object) throws ValidatorException {

		String value = String.valueOf(object);
		
//		System.out.println(" VALUE : " + value + " length " + value.length());
		
		
		if (value.trim().length() == 0) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "",
					bundle.getString("consulta.required")));
		}
		
		if (value.trim().length() < 11) {
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "",
					bundle.getString("consulta.minlength")));
		}
		
		String[] string = value.split("[-]");
		
		if(!sistemasBC().checkIfExist(string[0].trim())){
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "",
					bundle.getString("consulta.sistema.notfound")));			
		}		
		
		Sistema sistema = consultaBC().loadSistemaByCode(string[0].trim());
		Integer code = 0;
		try{
			code = Integer.parseInt(string[1].trim());
		}catch(Exception e){
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "",
					bundle.getString("consulta.erro.invalid")));	
		}
		
		
		if(!errosBC().checkIfCodeExist( code  , sistema)    ){
			throw new ValidatorException(new FacesMessage(
					FacesMessage.SEVERITY_ERROR, "",
					bundle.getString("consulta.erro.notfound")));			
		}	
		

	}
	

	private ConsultaBC consultaBC() {
		return Beans.getReference(ConsultaBC.class);
	}
	
	private SistemasBC sistemasBC() {
		return Beans.getReference(SistemasBC.class);
	}
	
	private ErrosBC errosBC() {
		return Beans.getReference(ErrosBC.class);
	}

}
