package net.marloncarvalho.investimentos.console;

import java.util.HashMap;
import java.util.Map;

public class Parametros {
	private Map<String, String> parametros = new HashMap<String,String>();

	public Parametros(String[] args) {
		this.construirParametros(args);
	}

	private void construirParametros(String[] args) {
		for ( String string : args ) {
			String[] chaveValor = string.split("=");
			if ( chaveValor.length == 2 ) {
				parametros.put(chaveValor[0], chaveValor[1]);
			}
		}
	}

	public boolean contem(String parametro) {
		return this.parametros.containsKey(parametro);
	}
	
	public String obterValor(String chave) {
		if ( this.contem(chave) )
			return this.parametros.get(chave);
		return null;
	}

}