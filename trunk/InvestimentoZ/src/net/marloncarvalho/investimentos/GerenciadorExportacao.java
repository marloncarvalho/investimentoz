package net.marloncarvalho.investimentos;

import java.io.IOException;

import net.marloncarvalho.investimentos.configuracao.Configurador;
import net.marloncarvalho.investimentos.entidades.Banco;
import net.marloncarvalho.investimentos.entidades.Fundo;
import net.marloncarvalho.investimentos.exportador.Exportador;
import net.marloncarvalho.investimentos.exportador.ExportadorFactory;
import net.marloncarvalho.investimentos.extratores.ExtratorCotas;
import net.marloncarvalho.investimentos.extratores.bancodobrasil.ExtratorCotasBancoBrasil;

import org.xml.sax.SAXException;

public class GerenciadorExportacao {
	private static GerenciadorExportacao instancia = new GerenciadorExportacao();
	private Configurador configurador = new Configurador();

	private GerenciadorExportacao() {		
	}

	public static GerenciadorExportacao getInstancia() {
		return instancia;
	}

	public void exportarCotasBanco(int tipoExportacao, String nome) {
		try {
			ExtratorCotas extrator = new ExtratorCotasBancoBrasil();
			Banco banco = configurador.obterBanco(nome);
			for ( Fundo fundo : banco.getFundos().values() ) {
				fundo.setCotas(extrator.extrairCotas(fundo.getUrl()));
			}
			Exportador exportador = ExportadorFactory.getExportador(tipoExportacao);
			exportador.exportar(banco);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void exportarCotasFundo(int tipoExportacao, String nome) {
/*		Banco b = new Banco();
		b.setNumero(banco);
*/		Fundo fundo = configurador.obterFundoInvestimento(nome);
		//fundo.setBanco(b);
		ExtratorCotas extrator = new ExtratorCotasBancoBrasil();
		fundo.setCotas(extrator.extrairCotas(fundo.getUrl()));
		Exportador exportador = ExportadorFactory.getExportador(tipoExportacao);
		exportador.exportar(fundo);
	}

}