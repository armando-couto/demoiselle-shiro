package br.tjce.demoiselleshiro.view;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.tjce.demoiselleshiro.business.EntidadesBC;
import br.tjce.demoiselleshiro.business.PerfisBC;
import br.tjce.demoiselleshiro.business.PermissoesBC;
import br.tjce.demoiselleshiro.business.UsuariosBC;
import br.tjce.demoiselleshiro.domain.Entidade;
import br.tjce.demoiselleshiro.domain.Perfis;
import br.tjce.demoiselleshiro.domain.Permissoes;
import br.tjce.demoiselleshiro.domain.Usuario;

@ViewController
@PreviousView("/usuarios_list.hxtml")
public class UsuariosPermissoesMB extends
		AbstractEditPageBean<Usuario, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuariosBC usuariosBC;

	@Inject
	private EntidadesBC entidadesBC;

	@Inject
	private PermissoesBC permissoesBC;

	@Inject
	private PerfisBC perfisBC;

	private List<Object> selectedPerfis = new ArrayList<Object>();

	private Perfis selectedPefil;

	private List<Entidade> listaEntidades = new ArrayList<Entidade>();

	private List<String> listaPermissoes = new ArrayList<String>();

	@Override
	public String delete() {
		return null;
	}

	@Override
	public String insert() {
		return getPreviousView();
	}

	@Override
	public String update() {
		getBean().getPerfis().clear();
		
		Perfis perfil = null;
		for (Object obj : selectedPerfis) {
			perfil = (Perfis) obj;
			if(!getBean().getPerfis().contains(perfil)){
				getBean().getPerfis().add(perfil);
			}
		}
		System.out.println(getBean().getPerfis().toString());
		usuariosBC.update(getBean());
		return getPreviousView();
	}

	@Override
	protected void handleLoad() {
		setBean(usuariosBC.load(getId()));
		for (Perfis perfil : getBean().getPerfis()) {
			selectedPerfis.add((Object) perfil);
		}
	}


	public List<Entidade> getEntidades() {
		return entidadesBC.findAll();
	}

	public List<Permissoes> getPermissoes() {
		return permissoesBC.findAll();
	}

	public List<Perfis> getPerfis() {
		return perfisBC.findAll();
	}

	public void setSelectedPefil(Perfis selectedPefil) {
		this.selectedPefil = selectedPefil;
	}

	public Perfis getSelectedPefil() {
		return selectedPefil;
	}

	public void setListaEntidades(List<Entidade> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}

	public List<Entidade> getListaEntidades() {
		return listaEntidades;
	}

	public void setSelectedPerfis(Object[] selectedPerfis) {
		this.selectedPerfis.clear();
		
		for (Object object : selectedPerfis) {
			this.selectedPerfis.add(object);
		}
	}

	public Object[] getSelectedPerfis() {
		return selectedPerfis.toArray();
	}
	
	public void setListaPermissoes(List<String> listaPermissoes) {
		this.listaPermissoes = listaPermissoes;
	}

	public List<String> getListaPermissoes() {
		return listaPermissoes;
	}

	@Override
	protected void clear() {
		this.selectedPerfis.clear();
	}
	
	@Override
	public Usuario getBean() {
		return (Usuario) super.getBean();
	}

}
