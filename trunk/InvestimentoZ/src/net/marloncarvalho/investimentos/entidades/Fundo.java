package net.marloncarvalho.investimentos.entidades;

import java.util.ArrayList;
import java.util.Collection;

public class Fundo {
	private String nome;
	private Collection<Cota> cotas = new ArrayList<Cota>();
	private String url;
	private Banco banco;
	
	/**
	 * @return the cotas
	 */
	public Collection<Cota> getCotas() {
		return cotas;
	}
	
	/**
	 * @param cotas the cotas to set
	 */
	public void setCotas(Collection<Cota> cotas) {
		this.cotas = cotas;
	}

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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the banco
	 */
	public Banco getBanco() {
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(Banco banco) {
		this.banco = banco;
	}

}