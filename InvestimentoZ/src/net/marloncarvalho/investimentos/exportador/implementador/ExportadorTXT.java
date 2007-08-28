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

public class ExportadorTXT implements Exportador {

	public void exportar(Banco banco) {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		buffer.append("Banco: " + banco.getNome() + "\n" ) ;
		buffer.append("Fundos de Investimento \n");
		for ( Fundo fundo : banco.getFundos().values() ) {
			buffer.append( fundo.getNome() + "\n" );
			for ( Cota cota : fundo.getCotas() ) {
				buffer.append(sdf.format(cota.getData()) + " " + cota.getValor() + "\n");
			}
		}
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter( banco.getNome() + ".txt"));
			writer.write(buffer.toString());
			writer.close();
		} catch (IOException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Ocorreu um erro ao criar o arquivo de exportacão para o " + banco.getNome() + "." );
		}
	}

	public void exportar(Fundo fundo) {
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		buffer.append( fundo.getNome() + "\n" );
		for ( Cota cota : fundo.getCotas() ) {
			buffer.append(sdf.format(cota.getData()) + " " + cota.getValor() + "\n");
		}		
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter( fundo.getNome()+ ".txt"));
			writer.write(buffer.toString());
			writer.close();
		} catch (IOException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Ocorreu um erro ao criar o arquivo de exportacão para o fundo " + fundo.getNome() + "." );
		}
	}

}