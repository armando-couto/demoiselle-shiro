package br.tjce.demoiselleshiro.view;

import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.util.Parameter;
import br.tjce.demoisellecayenne.utils.MyUtils;
import br.tjce.demoiselleshiro.business.ErrosBC;
import br.tjce.demoiselleshiro.domain.Erro;
import br.tjce.demoiselleshiro.domain.Sistema;

@ViewController
@PreviousView("/erros_list.xhtml")
public class ErrosEditMB extends AbstractEditPageBean<Erro, Long> {

	private static final long serialVersionUID = 1L;

	private Sistema sistema;

	@Inject
	private Parameter<Sistema> parameterSistema;

	@PostConstruct
	public void init() {
		if (parameterSistema.getValue() != null)
			sistema = parameterSistema.getValue();
	}

	@Inject
	private ErrosBC errosBC;

	@Override
	public String delete() {
		return null;
	}

	@Override
	public String insert() {
		getBean().setSistema(sistema);
		errosBC.insert(getBean());

		MyUtils.addMessage(FacesMessage.SEVERITY_INFO, ResourceBundle
				.getBundle("validations").getString("erros.inserted"));

		return getPreviousView();
	}

	@Override
	public String update() {
		errosBC.update(getBean());
		MyUtils.addMessage(FacesMessage.SEVERITY_INFO, ResourceBundle
				.getBundle("validations").getString("erros.updated"));
		return getPreviousView();
	}

	@Override
	protected void handleLoad() {
		setBean(errosBC.load(getId()));
	}

	public void valueChange(ValueChangeEvent event) {
		if (event.getNewValue() instanceof Sistema) {
			sistema = (Sistema) event.getNewValue();
			// if (sistema.getErros().size() > 0){
			// System.out.println(sistema.getErros().get(
			// sistema.getErros().size() - 1).getCodigo() );
			// getBean().setCodigo(sistema.getErros().get(sistema.getErros().size()
			// - 1).getCodigo() + 1);
			// }
		}
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Sistema getSistema() {
		return sistema;
	}

	@Override
	public Erro getBean() {
		return (Erro) super.getBean();
	}

}
