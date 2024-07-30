package br.com.cupacke.utils;

import org.apache.commons.lang3.StringUtils;

public class ApenasNumero {
	
	
	public static String deixaApenasNumeros(final String valor) {
		
		if (valor == null) {
			return null;
		}
		
		if (StringUtils.isBlank(valor)) {
			return valor;
		}
		
		return valor.replaceAll("\\D", StringUtils.EMPTY);
		
	}


}
