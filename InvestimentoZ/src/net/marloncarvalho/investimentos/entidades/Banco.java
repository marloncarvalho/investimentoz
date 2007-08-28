package net.marloncarvalho.investimentos.entidades;

import java.util.HashMap;
import java.util.Map;

public class Banco {
	public static String BANCODOBRASIL = "001";
	private String nome;
	private String numero;
	private Map<String,Fundo> fundos = new HashMap<String,Fundo>();
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the fundos
	 */
	public Map<String,Fundo> getFundos() {
		return fundos;
	}

	/**
	 * @param fundos the fundos to set
	 */
	public void setFundos(Map<String,Fundo> fundos) {
		this.fundos = fundos;
	}

}