package br.tjce.demoiselleshiro.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="codigoErroConveter")
public class CodigoErroConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent component, String string) {
		try {
			if (string != null && !string.trim().isEmpty()) {
				Integer codigo = 0;
				try{
					codigo = Integer.parseInt(string);
				}catch(NumberFormatException e){
					
				}
				return codigo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		try {
			if (objeto != null && objeto instanceof Integer) {
				String value = "";
				if( (Integer)objeto > 0){
					value = 	String.format("%04d", objeto);
				}
				return value;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
