package br.tjce.demoiselleshiro.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.gov.frameworkdemoiselle.template.JPACrud;
import br.tjce.demoiselleshiro.domain.Erro;
import br.tjce.demoiselleshiro.domain.Logs;
import br.tjce.demoiselleshiro.domain.MaisConsultado;
import br.tjce.demoiselleshiro.domain.Sistema;


public class ReportDAO extends JPACrud<Logs, Integer>{
	
	private static final long serialVersionUID = 1L;
	
	public List<MaisConsultado> getRelatorioMaisConsultados(Sistema sistema,Erro erro,Date dataInicio,Date dataFim){
		
		List<MaisConsultado> lista = new ArrayList<MaisConsultado>();
		
		String sql = "SELECT count(l.id) as totalConsultas, l.codigosistema as sistema," +
		"l.descricaosistema as sistemadescricao, l.codigoerro as erro, " +
		" (SELECT s.id FROM sistemas s WHERE  s.codigo = l.codigosistema) as t, " +
		" l.descricaoerro as errodescricao " +
		" FROM logs l WHERE codigosistema = ?1" ;  //GROUP BY erro ORDER BY totalConsultas desc"
		if(erro != null)
			sql += " AND l.codigoerro = ?2";
		if(dataInicio != null)
			sql += " AND l.data >= ?3";
		if(dataFim != null)
			sql += " AND l.data <= ?4";
		sql +=  " GROUP BY erro ORDER BY totalConsultas desc";
	
//		"SELECT count(l.id) as totalConsultas, l.codigosistema as sistema," +
//		"l.descricaosistema as sistemadescricao, l.codigoerro as erro, " +
//		" (SELECT s.id FROM sistemas s WHERE  s.codigo = l.codigosistema) as t, " +
//		" l.descricaoerro as errodescricao " +
//		" FROM logs l WHERE codigosistema = ? GROUP BY erro ORDER BY totalConsultas desc"
		
		Query q = getEntityManager().createNativeQuery(sql);
		q.setParameter(1, sistema.getCodigo());
		if(erro != null)
			q.setParameter(2, erro.getCodigo() );
		if(dataInicio != null)
			q.setParameter(3 , dataInicio );
		if(dataFim != null)
			q.setParameter(4 , dataFim );

		@SuppressWarnings("unchecked")
		List<Object[]> rows = q.getResultList(); 		
		
		for(int i = 0 ; i < rows.size() ; i++){
			lista.add(popularMaisConsultado(rows.get(i)));
		}
		
		return lista;
		
	}

	private MaisConsultado popularMaisConsultado(Object[] row){
		MaisConsultado con = new MaisConsultado();	
		con.setTotalConsultas( (Long) row[0] );// row.get("totalConsultas"));
		con.setSistema((String) row[1]);
		con.setSistemaDescricao( (String)  row[2] );
		con.setErro(String.format("%04d", (Integer) row[3]));
		con.setErroDescricao((String) row[5]);			
		return con;
	}
	
	
}
