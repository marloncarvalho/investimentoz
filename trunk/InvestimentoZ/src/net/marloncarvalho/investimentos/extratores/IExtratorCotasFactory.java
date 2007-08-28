package net.marloncarvalho.investimentos.extratores;

public interface IExtratorCotasFactory {
	public static int BANCODOBRASIL = 1;

	public ExtratorCotas obterExtrator(int banco) throws Exception;
	public ExtratorCotas obterExtrator(String banco) throws Exception;

}