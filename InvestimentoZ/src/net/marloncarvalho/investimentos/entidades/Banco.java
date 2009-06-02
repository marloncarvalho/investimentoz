package net.marloncarvalho.investimentos.entidades;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Classe que representa um Banco.
 * 
 * @author Marlon Silva Carvalho
 */
@Entity
@Table(name="INVEST_BANCOS")
@NamedQueries(
		{
			@NamedQuery(name="bancosPorNome", query="from Banco banco where banco.nome = :nome")
		}
	)
public class Banco implements EntidadePersistente {
	public static String BANCODOBRASIL = "001";

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="NOME")
	private String nome;

	@Column(name="NUMERO")
	private String numero;

	@Transient
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}