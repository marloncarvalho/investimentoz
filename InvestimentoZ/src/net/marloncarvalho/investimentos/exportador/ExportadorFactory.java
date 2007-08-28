package net.marloncarvalho.investimentos.exportador;

import net.marloncarvalho.investimentos.exportador.implementador.ExportadorTXT;
import net.marloncarvalho.investimentos.exportador.implementador.ExportadorXML;

public class ExportadorFactory {

	public static Exportador getExportador(int exportador) {
		switch ( exportador ) {
			case Exportador.TXT:
				return new ExportadorTXT();
			case Exportador.XML:
				return new ExportadorXML();
		}
		return null;
	}
}