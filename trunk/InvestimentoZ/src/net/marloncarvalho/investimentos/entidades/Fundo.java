package net.marloncarvalho.investimentos.entidades;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

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
public class Fundo implements EntidadePersistente {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="NOME")
	private String nome;

	@Transient
	private Collection<Cota> cotas = new ArrayList<Cota>();

	@Column(name="URL")
	private String url;

	@ManyToOne
	@JoinColumn(name="ID_BANCO")
	private Banco banco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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