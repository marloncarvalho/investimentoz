package net.marloncarvalho.investimentos.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe que define critérios para consulta em uma base de dados.
 * 
 * @author 79325645572
 * @since 01/06/2009
 */
final public class Criterio {
	private String nomeConsulta;
	private int quantidade;
	private int registroInicial;
	private Map<String, Object> parametros = new HashMap<String, Object>();
	
	public void adicionarParametro(String chave, Object o) {
		this.parametros.put(chave,o);
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getRegistroInicial() {
		return registroInicial;
	}

	public void setRegistroInicial(int registroInicial) {
		this.registroInicial = registroInicial;
	}

	public Map<String, Object> getParametros() {
		return parametros;
	}

	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}

	public String getNomeConsulta() {
		return nomeConsulta;
	}

	public void setNomeConsulta(String nomeConsulta) {
		this.nomeConsulta = nomeConsulta;
	}
}