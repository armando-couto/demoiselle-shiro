package br.tjce.demoiselleshiro.view;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.util.Parameter;
import br.gov.frameworkdemoiselle.util.Redirector;
import br.tjce.demoisellecayenne.utils.MyUtils;
import br.tjce.demoiselleshiro.business.ErrosBC;
import br.tjce.demoiselleshiro.business.SistemasBC;
import br.tjce.demoiselleshiro.domain.Erro;
import br.tjce.demoiselleshiro.domain.Sistema;

@ViewController
@NextView("/erros_edit.xhtml")
public class ErrosListMB extends AbstractListPageBean<Erro, Integer> {

	private static final long serialVersionUID = 1L;

	private Long id_erros;

	private Sistema sistema;;

	@Inject
	private ErrosBC erroBC;
	
	@Inject 
	private SistemasBC sistemasBC;

	@Inject
	private Parameter<Sistema> parameterSistema;

	@PostConstruct
	public void init() {
		if (parameterSistema.getValue() != null)
			sistema = parameterSistema.getValue();
	}

	@Override
	protected List<Erro> handleResultList() {
		return erroBC.findAll();
	}

	public void sistemasValueChangeListener(AjaxBehaviorEvent event) {
		HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
		sistema = (Sistema) select.getValue();
	}

	@RequiresPermissions("erros_deletar")
	public String delete() {
		SecurityUtils.getSubject().checkPermission("erros_deletar");
		sistema.getErros().remove(erroBC.load(id_erros));
		erroBC.delete(id_erros);
		clear();
		MyUtils.addMessage(FacesMessage.SEVERITY_INFO, ResourceBundle
				.getBundle("validations").getString("erros.deleted"));
		return null;
	}

	public void setId_erros(Long id_sistema) {
		this.id_erros = id_sistema;
	}

	public Long getId_erros() {
		return id_erros;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Sistema getSistema() {
		return sistema;
	}
	
	@ExceptionHandler
	public void tratador(UnauthorizedException cause) { 
		Redirector.redirect("/accessdenied.jsf");
	}
	
	@Override
	public void clear() {
		super.clear();
	}

}
