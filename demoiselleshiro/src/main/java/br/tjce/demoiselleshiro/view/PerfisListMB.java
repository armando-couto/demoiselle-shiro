package br.tjce.demoiselleshiro.view;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.tjce.demoisellecayenne.utils.MyUtils;
import br.tjce.demoiselleshiro.business.PerfisBC;
import br.tjce.demoiselleshiro.domain.Perfis;

@ViewController
@NextView("/perfis_edit.xhtml")
public class PerfisListMB extends AbstractListPageBean<Perfis, Integer> {

	private static final long serialVersionUID = 1L;

	@Inject
	private PerfisBC perfisBC;
	
	private Integer idToDelete;
	
	@Override
	protected List<Perfis> handleResultList() {
		return perfisBC.findAll();
	}
	
	public String delete(){
		perfisBC.delete(idToDelete);
		MyUtils.addMessage(FacesMessage.SEVERITY_INFO, ResourceBundle.getBundle("validations").getString("perfis.deleted"));
		clear();
		return null;
	}

	public void setIdToDelete(Integer idToDelete) {
		this.idToDelete = idToDelete;
	}

	public Integer getIdToDelete() {
		return idToDelete;
	}
	
	@Override
	public void clear() {
		super.clear();
	}
	
	

}
