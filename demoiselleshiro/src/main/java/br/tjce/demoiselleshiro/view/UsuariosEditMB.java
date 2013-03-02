package br.tjce.demoiselleshiro.view;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.tjce.demoisellecayenne.utils.MyUtils;
import br.tjce.demoiselleshiro.business.PerfisBC;
import br.tjce.demoiselleshiro.business.UsuariosBC;
import br.tjce.demoiselleshiro.domain.Perfis;
import br.tjce.demoiselleshiro.domain.Usuario;

@ViewController
@PreviousView("/usuarios_list.xhtml")
public class UsuariosEditMB extends AbstractEditPageBean<Usuario, Integer> {

	private static final long serialVersionUID = 1L;

	private String confirmpassword;
	private String password;

	@Inject
	private UsuariosBC usuariosBC;

	@Inject
	private PerfisBC perfisBC;

	@Override
	public String delete() {
		return null;
	}

	@Override
	public String insert() {

		if (password != null && password.length() != 0) {
			getBean().setUserpassword(MyUtils.md5(password));
		}
		usuariosBC.insert(getBean());

		MyUtils.addMessage(FacesMessage.SEVERITY_INFO, ResourceBundle
				.getBundle("validations").getString("usuarios.inserted"));

		return getPreviousView();
	}

	@Override
	public String update() {
		if (password != null && password.length() != 0) {
			getBean().setUserpassword(MyUtils.md5(password));
		}

		usuariosBC.update(getBean());
		
		MyUtils.addMessage(FacesMessage.SEVERITY_INFO, ResourceBundle
				.getBundle("validations").getString("usuarios.updated"));

		return getPreviousView();
	}

	@Override
	protected void handleLoad() {
		setBean(usuariosBC.load(getId()));
	}

	public List<Perfis> getPerfisList() {
		return perfisBC.findAll();
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public Usuario getBean() {
		return (Usuario) super.getBean();
	}

}
