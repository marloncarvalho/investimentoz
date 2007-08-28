package net.marloncarvalho.investimentos.exportador.implementador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.marloncarvalho.investimentos.entidades.Banco;
import net.marloncarvalho.investimentos.entidades.Cota;
import net.marloncarvalho.investimentos.entidades.Fundo;
import net.marloncarvalho.investimentos.exportador.Exportador;

public class ExportadorXML implements Exportador {

	public void exportar(Banco banco) {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		buffer.append("<banco nome=\"" + banco.getNome() + "\" numero=\"" + banco.getNumero() + "\">" ) ;
		buffer.append("<fundos>");
		for ( Fundo fundo : banco.getFundos().values() ) {
			buffer.append("<fundo nome=\"" + fundo.getNome() + "\" url=\"" + fundo.getUrl() + "\">");
			buffer.append("<cotas>");
			for ( Cota cota : fundo.getCotas() ) {
				buffer.append("<cota data=\""+sdf.format(cota.getData())+"\" valor=\""+ cota.getValor() +"\"/>");
			}
			buffer.append("</cotas>");
			buffer.append("</fundo>");
		}
		buffer.append("</fundos>");
		buffer.append("</banco>");
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter( banco.getNome() + ".xml"));
			writer.write(buffer.toString());
			writer.close();
		} catch (IOException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Ocorreu um erro ao criar o arquivo de exportacão para o " + banco.getNome() + "." );
		}
	}

	public void exportar(Fundo fundo) {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		buffer.append("<fundo nome=\"" + fundo.getNome() + "\" url=\"" + fundo.getUrl() + "\">");
		buffer.append("<cotas>");
		for ( Cota cota : fundo.getCotas() ) {
			buffer.append("<cota data=\""+sdf.format(cota.getData())+"\" valor=\""+ cota.getValor() +"\"/>");
		}		
		buffer.append("</cotas>");
		buffer.append("</fundo>");

		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter( fundo.getNome()+ ".xml"));
			writer.write(buffer.toString());
			writer.close();
		} catch (IOException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Ocorreu um erro ao criar o arquivo de exportacão para o fundo " + fundo.getNome() + "." );
		}
	}

}