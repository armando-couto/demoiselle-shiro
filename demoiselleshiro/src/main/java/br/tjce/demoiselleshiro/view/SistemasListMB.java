package br.tjce.demoiselleshiro.view;

import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.tjce.demoisellecayenne.utils.MyUtils;
import br.tjce.demoiselleshiro.business.SistemasBC;
import br.tjce.demoiselleshiro.domain.Sistema;

@ViewController
@NextView("/sistemas_edit.xhtml")
public class SistemasListMB extends AbstractListPageBean<Sistema, Integer> {

	private static final long serialVersionUID = 1L;
	
	private Integer id_sistema;

	
	@Inject
	private SistemasBC sistemasBC;
	
//	@PostConstruct
//	public void init(){
//		ResourceBundle bundle = ResourceBundle.getBundle("validations");
//		if(message.getValue() != null){
//			String msg = message.getValue();
//			if(msg.equals("inserido"))
//				MyUtils.addMessage(FacesMessage.SEVERITY_INFO, bundle.getString("sistemas.inserted"));
//			
//		}
//	}
	
	@Override
	protected List<Sistema> handleResultList() {
		return sistemasBC.findAll();
	}
	
	public String delete(){		
		sistemasBC.delete(id_sistema);
		clear();
		MyUtils.addMessage(FacesMessage.SEVERITY_INFO, ResourceBundle.getBundle("validations").getString("sistemas.deleted"));
		return null;
	}

	public void setId_sistema(Integer id_sistema) {
		this.id_sistema = id_sistema;
	}

	public Integer getId_sistema() {
		return id_sistema;
	}
	
	@Override
	public List<Sistema> getResultList() {
		return super.getResultList();
	}
	

}
