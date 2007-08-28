package net.marloncarvalho.investimentos.entidades;

import java.util.Date;

public class Cota {
	private Date data;
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