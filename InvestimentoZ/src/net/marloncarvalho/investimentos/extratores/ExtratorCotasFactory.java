package net.marloncarvalho.investimentos.extratores;

import net.marloncarvalho.investimentos.extratores.bancodobrasil.ExtratorCotasBancoBrasil;

public class ExtratorCotasFactory implements IExtratorCotasFactory {

	public ExtratorCotas obterExtrator(int banco) {
		switch ( banco ) {
			case IExtratorCotasFactory.BANCODOBRASIL:
				return new ExtratorCotasBancoBrasil();
		}
		return null;
	}

	public ExtratorCotas obterExtrator() {
			return this.obterExtrator(IExtratorCotasFactory.BANCODOBRASIL);
	}

	public ExtratorCotas obterExtrator(String banco) throws Exception {
		if ( banco.toLowerCase().equals("banco do brasil") || 
				banco.toLowerCase().equals("banco brasil") || 
				banco.toLowerCase().equals("bancobrasil") || 
				banco.toLowerCase().equals("bancodobrasil") ) {
			return this.obterExtrator(IExtratorCotasFactory.BANCODOBRASIL);
		}
		throw new Exception("Nao existe um extrator para fundos de investimento do banco informado: " + banco);
	}

}