package br.tjce.demoiselleshiro.view;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.tjce.demoisellecayenne.utils.MyUtils;
import br.tjce.demoiselleshiro.business.SistemasBC;
import br.tjce.demoiselleshiro.domain.Sistema;


@ViewController
@PreviousView("/sistemas_list.xhtml")
public class SistemasEditMB extends AbstractEditPageBean<Sistema, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private SistemasBC sistemasBC;
	
	@Override
	public String delete() {
		return null;
	}

	@Override
	public String insert() {
		getBean().setCodigo(getBean().getCodigo().toUpperCase());
		sistemasBC.insert(getBean());
		MyUtils.addMessage(FacesMessage.SEVERITY_INFO, ResourceBundle.getBundle("validations").getString("sistemas.inserted"));
		return getPreviousView();
	}

	@Override
	public String update() {
		getBean().setCodigo(getBean().getCodigo().toUpperCase());
		sistemasBC.update(getBean());
		MyUtils.addMessage(FacesMessage.SEVERITY_INFO,  ResourceBundle.getBundle("validations").getString("sistemas.updated"));
		return getPreviousView();
	}

	@Override
	protected void handleLoad() {
		setBean(sistemasBC.load(getId()));		
	}
	
	@Override
	public Sistema getBean() {
		return  (Sistema) super.getBean();
	}
	
	
	
}
