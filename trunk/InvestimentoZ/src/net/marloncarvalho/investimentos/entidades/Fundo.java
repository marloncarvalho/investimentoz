package net.marloncarvalho.investimentos.entidades;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe que representa um fundo de Investimento.
 * 
 * @author Marlon Silva Carvalho
 */
@Entity
@Table(name="INVEST_FUNDOS")
@NamedQueries(
		{
			@NamedQuery(name="fundosPorNome", query="from Fundo fundo where fundo.nome = :nome")
		}
	)
public class Fundo extends EntidadePersistente {
	
	@Column(name="NOME")
	private String nome;
	
	@OneToMany
	@JoinColumn(name="ID_FUNDO")
	private Collection<Cota> cotas = new ArrayList<Cota>();
	
	@Column(name="URL")
	private String url;

	@ManyToOne
	@JoinColumn(name="ID_BANCO")
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