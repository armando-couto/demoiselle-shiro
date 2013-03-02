package br.tjce.demoiselleshiro.view;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.tjce.demoisellecayenne.utils.MyUtils;
import br.tjce.demoiselleshiro.business.UsuariosBC;
import br.tjce.demoiselleshiro.domain.Usuario;

@ViewController
@NextView("/usuarios_edit.xhtml")
public class UsuariosListMB extends  AbstractListPageBean<Usuario, Integer> {

	private static final long serialVersionUID = 1L;
	
	private Integer id_usuario;
	
	@Inject
	private UsuariosBC bc;
	
	private List<Usuario> listaUsuarios;
	
	@PostConstruct
	public void carregar(){
		listaUsuarios = bc.findAll();
	}
	
	@Override
	protected List<Usuario> handleResultList() {		
		return bc.findAll();
	}
	
	public String delete(){
		 bc.delete(id_usuario);
		 carregar();		
		 MyUtils.addMessage(FacesMessage.SEVERITY_INFO,  ResourceBundle.getBundle("validations").getString("usuarios.deleted"));
		 return null;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	
	@Override
	public void clear() {
		super.clear();
	}

}
