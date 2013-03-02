package br.tjce.demoiselleshiro.view;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import org.primefaces.event.DateSelectEvent;

import br.gov.frameworkdemoiselle.report.Report;
import br.gov.frameworkdemoiselle.report.Type;
import br.gov.frameworkdemoiselle.report.annotation.Path;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractPageBean;
import br.gov.frameworkdemoiselle.util.FileRenderer;
import br.tjce.demoiselleshiro.business.LogsBC;
import br.tjce.demoiselleshiro.business.ReportBC;
import br.tjce.demoiselleshiro.domain.Erro;
import br.tjce.demoiselleshiro.domain.Logs;
import br.tjce.demoiselleshiro.domain.Sistema;


@ViewController
public class RelatoriosMB extends AbstractPageBean {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ReportBC reportBC;
	
	@Inject
	@Path("jsreports/maisConsultados.jasper")
	private Report maisConsultados;
	
	@Inject
	private FileRenderer renderer;
	
	@Inject
	private LogsBC bc;
	
	private Date dataInicio;
	
	private Date dataFim;
	 
	private Sistema sistema;
	
	private Erro erro;
	
	
	public String relatorioMaisConsultados(){
		Map<String, Object> parameter = new HashMap<String, Object>();	
		
		URL path =  this.getClass().getResource("/jsreports/images/marca_full_color_g.png");
		parameter.put("imagemTitle", path.toString());
		
		byte[] buffer = maisConsultados.export(reportBC.getRelatorioMaisConsultados(sistema,erro,dataInicio,dataFim), parameter, Type.PDF);

		renderer.render(buffer, FileRenderer.ContentType.PDF, "relatorio.pdf");
		return null;
	}
	
	public void sistemaSelectListener(ValueChangeEvent event){
		if(event.getNewValue() != null)
			sistema =  (Sistema) event.getNewValue();	
		else
			sistema = null;
	}
	
	public void erroSelectListener(ValueChangeEvent event){
		if(event.getNewValue() != null)
			erro =  (Erro) event.getNewValue();	
		else
			erro = null;
	}

	public void dataInicioListener(DateSelectEvent event){
		setDataInicio(event.getDate());
	}
	
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}


	public Date getDataInicio() {
		return dataInicio;
	}

	public void dataFimListener(DateSelectEvent event){
		setDataFim(event.getDate());
	}
	
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}


	public Date getDataFim() {
		return dataFim;
	}


	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}


	public Sistema getSistema() {
		return sistema;
	}


	public void setErro(Erro erro) {
		this.erro = erro;
	}


	public Erro getErro() {
		return erro;
	}
	
	

}
