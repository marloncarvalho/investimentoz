package net.marloncarvalho.investimentos.console;

import net.marloncarvalho.investimentos.exportador.Exportador;

public class TratadorConsole {
	public String versao = "0.0.1 beta";
	private Parametros parametros ;
	private static TratadorConsole instancia ;
	
	private TratadorConsole(String[] args) {
		this.parametros = new Parametros(args);
	}

	public static TratadorConsole getInstancia(String[] args) {
		if ( instancia == null )
			instancia = new TratadorConsole(args);
		return instancia;
	}

	public int obterTipoExportacao(String[] args) {
		String param = parametros.obterValor("-e");
		if ( param == null ) return Exportador.TXT;
		if ( param.equals("xml") )
			return Exportador.XML;	
		return Exportador.TXT;
	}

	public int checarParametros(String[] args) throws Exception {
		if ( parametros.contem("-f") && parametros.contem("-pi") && parametros.contem("-pf") )
			return Constantes.FUNDO_PERIODOINICIAL_PERIODOFINAL;
		if ( parametros.contem("-f") && parametros.contem("-pi") )
			return Constantes.FUNDO_PERIODOINICIAL;
		if ( parametros.contem("-f") && parametros.contem("-pf") )
			return Constantes.FUNDO_PERIODOFINAL;
		if ( parametros.contem("-b") && parametros.contem("-pi") && parametros.contem("-pf") )
			return Constantes.BANCO_PERIODOINICIAL_PERIODOFINAL;
		if ( parametros.contem("-b") && parametros.contem("-pi") )
			return Constantes.BANCO_PERIODOINICIAL;
		if ( parametros.contem("-b") && parametros.contem("-pf") )
			return Constantes.BANCO_PERIODOFINAL;
		if ( parametros.contem("-f") )
			return Constantes.FUNDO;
		if ( parametros.contem("-b") )
			return Constantes.BANCO;
		return -1;
	}

	public String obterValorParametro(String parametro) {
		return this.parametros.obterValor(parametro);
	}

	public void usabilidade(String mensagem) {
		StringBuffer buffer = new StringBuffer();
		if ( mensagem != null )
			buffer.append("Erro: " + mensagem + "\n");
		buffer.append("Fundos de Investimento. \n");
		buffer.append("Versão " + versao + "\n");
		buffer.append("investimentos [-f=<nomefundo>|-b=<nomebanco>] -e=<tipoexportador>\n");
		buffer.append("Exemplo: investimentoz -f=\"BB Curto Prazo Mil\" -e=xml \n");
		buffer.append("Bancos disponíveis: bancodobrasil \n");
		buffer.append("Exportadores: xml txt \n");
		System.out.println(buffer.toString());
	}

}