package net.marloncarvalho.investimentos.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Classe que representa uma Cota de um Fundo de Investimento.
 * 
 * @author Marlon Silva Carvalho
 */
@Entity
@Table(name="INVEST_COTAS")
@NamedQueries(
		{
			@NamedQuery(name="cotasPorFundoData", query="from Cota cota where cota.data = :data and cota.fundo.id = :idFundo")
		}
	)
public class Cota implements EntidadePersistente {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="DATA")
	private Date data;

	@Column(name="VALOR")
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name="ID_FUNDO")
	private Fundo fundo;

	public Fundo getFundo() {
		return fundo;
	}

	public void setFundo(Fundo fundo) {
		this.fundo = fundo;
	}

	/**
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}
	
	/**
	 * @param valor the valor to set
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}

	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}