package br.tjce.demoiselleshiro.view;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractPageBean;
import br.tjce.demoiselleshiro.business.ConsultaBC;
import br.tjce.demoiselleshiro.business.LogsBC;
import br.tjce.demoiselleshiro.domain.Erro;
import br.tjce.demoiselleshiro.domain.Sistema;


@ViewController
public class ConsultaMB extends AbstractPageBean {

	private static final long serialVersionUID = 1L;
	
	private String consulta;
	
	private Sistema sistema;
	
	private Erro erro;
	
	@Inject
	private ConsultaBC consultaBC;
	
	@Inject
	private LogsBC logsBC;
	
	public void consultar(){
		String[] s = consulta.split("[-]");
		//CARREGA O SISTEMA E O ERRO CONSULTADO
		setSistema(consultaBC.loadSistemaByCode(s[0].trim()));
		erro = consultaBC.loadErroByCode(Integer.parseInt(s[1].trim()), getSistema());
		
		//GERA UM LOG DA CONSULTA FEITA
		logsBC.gravarLogPesquisa("CONSULTAR", sistema, erro);
	}
	
	public void clean(){
		setSistema(null);
		erro = null;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setErro(Erro erro) {
		this.erro = erro;
	}

	public Erro getErro() {
		return erro;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Sistema getSistema() {
		return sistema;
	}

}
