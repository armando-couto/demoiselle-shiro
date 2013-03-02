package br.tjce.demoiselleshiro.view;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.tjce.demoiselleshiro.business.EntidadeHasPerfilBC;
import br.tjce.demoiselleshiro.business.EntidadesBC;
import br.tjce.demoiselleshiro.business.PerfisBC;
import br.tjce.demoiselleshiro.business.PermissoesBC;
import br.tjce.demoiselleshiro.domain.Entidade;
import br.tjce.demoiselleshiro.domain.EntidadesHasPerfi;
import br.tjce.demoiselleshiro.domain.EntidadesHasPerfiPK;
import br.tjce.demoiselleshiro.domain.Perfis;
import br.tjce.demoiselleshiro.domain.Permissoes;

@ViewController
@PreviousView("/perfis_list.xhtml")
public class PerfisEditMB extends AbstractEditPageBean<Perfis, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntidadesBC entidadesBC;

	@Inject
	private PermissoesBC permissoesBC;

	@Inject
	private EntidadeHasPerfilBC entidadeHasPerfilBC;

	@Inject
	private PerfisBC perfisBC;

	private Integer id_perfil;

	private List<Entidade> listaEntidades;

	private List<Permissoes> listaPermissoes;

	@Override
	public String delete() {
		return null;
	}

	@Override
	public String insert() {
		perfisBC.insert(getBean());
		id_perfil = getBean().getId();
		System.out.println(id_perfil);
		return null;
	}

	@Override
	public String update() {
		getBean().getEntidades().clear();
		for (Entidade entidade : listaEntidades) {
			if (!entidade.getPermissoes().isEmpty()
					&& !getBean().getEntidades().contains(entidade.getId())) {
				getBean().getEntidades().add(entidade);
			}
		}
		perfisBC.update(getBean());
		
		for (Permissoes permissao : permissoesBC.findAll()) {
			for (Entidade entidade : listaEntidades) {
				if(!entidade.getPermissoes().contains(permissao)){
					if(permissao.getEntidadesHasPerfis().contains(new EntidadesHasPerfi(new EntidadesHasPerfiPK(entidade.getId(), getBean().getId())))){
						permissao.getEntidadesHasPerfis().remove(new EntidadesHasPerfi(new EntidadesHasPerfiPK(entidade.getId(), getBean().getId())));
						System.out.println("REMOVEU ENTIDADE : "  + entidade.getNome() + " " + permissao.getPermissao());
					}
				}else{
					if(!permissao.getEntidadesHasPerfis().contains(new EntidadesHasPerfi(new EntidadesHasPerfiPK(entidade.getId(), getBean().getId())))){
						permissao.getEntidadesHasPerfis().add(new EntidadesHasPerfi(new EntidadesHasPerfiPK(entidade.getId(), getBean().getId())));
						System.out.println("ADICIONOU ENTIDADE : "  + entidade.getNome() + " " + permissao.getPermissao());
					}
				}
				permissoesBC.update(permissao);
				EntidadesHasPerfi e = entidadeHasPerfilBC.load(new EntidadesHasPerfiPK(entidade.getId(), getBean().getId()));
				if(e != null){
					e.setPermissoes(entidade.getPermissoes());
					entidadeHasPerfilBC.update(e);
				}
			}
		}
		clear();
		return getPreviousView();
	}

	@Override
	protected void handleLoad() {
		setBean(perfisBC.load(getId()));
		for (Entidade entidade : getListaEntidades()) {
			EntidadesHasPerfi entidadeHasPerfil = entidadeHasPerfilBC
					.load(new EntidadesHasPerfiPK(entidade.getId(), getBean()
							.getId()));
			if(entidadeHasPerfil != null && !entidadeHasPerfil.getPermissoes().isEmpty()){
				entidade.setPermissoes(new ArrayList<Permissoes>());
				for (Permissoes permissao : entidadeHasPerfil.getPermissoes()) {
					entidade.getPermissoes().add(permissao);
				}
			}
		}
	}

	public void setListaEntidades(List<Entidade> listaEntidades) {
		this.listaEntidades = listaEntidades;
	}

	public List<Entidade> getListaEntidades() {
		if (listaEntidades == null)
			setListaEntidades(entidadesBC.findAll());
		return listaEntidades;
	}

	public void setListaPermissoes(List<Permissoes> listaPermissoes) {
		this.listaPermissoes = listaPermissoes;
	}

	public List<Permissoes> getListaPermissoes() {
		if (listaPermissoes == null)
			setListaPermissoes(permissoesBC.findAll());
		return listaPermissoes;
	}

	public void setId_perfil(Integer id_perfil) {
		this.id_perfil = id_perfil;
	}

	public Integer getId_perfil() {
		return id_perfil;
	}

	@Override
	public boolean isUpdateMode() {
		return (id_perfil != null) || super.isUpdateMode();
	}

	@Override
	public Perfis getBean() {
		return (Perfis) super.getBean();
	}

	@Override
	protected void clear() {
		listaPermissoes = null;
		listaEntidades = null;
		super.clear();
	}
	// private void clear(){
	// listaPermissoes = null;
	// listaEntidades = null;
	// }

}
