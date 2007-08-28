package net.marloncarvalho.investimentos.extratores;

import java.util.Collection;

import net.marloncarvalho.investimentos.entidades.Cota;

public interface ExtratorCotas {

	/**
	 * Extrair as Cotas da URL informada.
	 * 
	 * @param endereco URL contendo as cotas a serem extraidas.
	 * @return Collection contendo as cotas extraidas.
	 */
	public Collection<Cota> extrairCotas(String url);

}