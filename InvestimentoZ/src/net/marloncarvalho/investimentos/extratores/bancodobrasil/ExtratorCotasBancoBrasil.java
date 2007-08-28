package net.marloncarvalho.investimentos.extratores.bancodobrasil;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import net.marloncarvalho.investimentos.entidades.Cota;
import net.marloncarvalho.investimentos.extratores.ExtratorCotas;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@SuppressWarnings("all")
public class ExtratorCotasBancoBrasil implements ExtratorCotas {

	public Collection<Cota> extrairCotas(String endereco) {
		try {
			return this.extrair(endereco);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Collection<Cota> extrair(String url) throws SAXException, IOException {
		Collection<Cota> cotas = new ArrayList<Cota>();
		DOMParser parser = new DOMParser();
		parser.parse(url);
		// Passar por cada Node que compoem a pagina.
		NodeList trList = parser.getDocument().getElementsByTagName("TR");
		int tamanho = trList.getLength();
		for (int indice = 0 ; indice < tamanho ; indice ++) {
			Node node = trList.item(indice);
			if ( node.getAttributes() != null ) {
				if ( node.getAttributes().getNamedItem("bgcolor") != null ) {
					if ( node.getAttributes().getNamedItem("bgcolor").getTextContent().equals("#FFF5CB") ) {
						try {
							Cota cota = this.extrairCota(node);
							if ( cota != null )
								cotas.add(cota);
						} catch (DOMException e) {
							e.printStackTrace();
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return cotas;
	}

	private Double extrairValor(Node node) {
		char[] valor = node.getTextContent().trim().toCharArray();
		String valorFinal = "";
		for ( int indice = 0 ; indice < valor.length ; indice ++ )
			if ( Character.isDigit(valor[indice]) )
				valorFinal += valor[indice];
			else {
				if ( valor[indice] == ',' )
					valorFinal += ".";
			}
		return Double.parseDouble(valorFinal);
	}

	private Date extrairData(Node node) throws DOMException, ParseException {
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		data = sdf.parse(node.getTextContent());
		return data;
	}

	private Cota extrairCota(Node node) throws DOMException, ParseException {
		Cota cota = new Cota();
		Node tdData = node.getChildNodes().item(1).getFirstChild();
		Node tdValor = node.getChildNodes().item(3).getFirstChild();
		cota.setData(this.extrairData(tdData));
		cota.setValor(this.extrairValor(tdValor));
		return cota;
	}
}