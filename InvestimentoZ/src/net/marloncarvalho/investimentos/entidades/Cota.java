package net.marloncarvalho.investimentos.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe que representa uma Cota de um Fundo de Investimento.
 * 
 * @author Marlon Silva Carvalho
 */
@Entity
@Table(name="INVEST_COTAS")
public class Cota extends EntidadePersistente {

	@Column(name="DATA")
	private Date data;

	@Column(name="VALOR")
	private Double valor;
	
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
	
}