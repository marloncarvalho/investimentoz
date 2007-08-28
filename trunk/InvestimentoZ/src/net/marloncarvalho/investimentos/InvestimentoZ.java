package net.marloncarvalho.investimentos;

import net.marloncarvalho.investimentos.console.Constantes;
import net.marloncarvalho.investimentos.console.TratadorConsole;

@SuppressWarnings("all")
public class InvestimentoZ {

	public static void main ( String[] args ) {
		TratadorConsole console = TratadorConsole.getInstancia(args);
		try {
			int tipoExportacao = console.obterTipoExportacao(args);
			switch ( console.checarParametros(args) ) {

				//	Solicitado apenas um fundo de investimento específico.
				case Constantes.FUNDO: 
					GerenciadorExportacao.getInstancia().exportarCotasFundo(tipoExportacao, console.obterValorParametro("-f"));
					break;

				//	Solicitado todos fundos de um banco.
				case Constantes.BANCO: 
					GerenciadorExportacao.getInstancia().exportarCotasBanco(tipoExportacao, console.obterValorParametro("-b"));
					break;

				// Não Definido ainda.
				case 3:
					break;
	
				default:
					console.usabilidade(null);
			}
		} catch (Exception e) {
			console.usabilidade(e.getMessage());
		}
	}

}