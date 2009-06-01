package net.marloncarvalho.investimentos.configuracao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import net.marloncarvalho.investimentos.entidades.Banco;
import net.marloncarvalho.investimentos.entidades.Fundo;
import net.marloncarvalho.investimentos.extratores.ExtratorCotas;
import net.marloncarvalho.investimentos.extratores.bancodobrasil.ExtratorCotasBancoBrasil;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Classe responsável em realizar a configuração inicial do extrator de cotas.
 * 
 * @author Marlon Silva Carvalho
 */
@SuppressWarnings("all")
public class Configurador {
	private DOMParser parser = new DOMParser();

	public Configurador() {
		try {
			parser.parse("fundos.xml");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Fundo obterFundoInvestimento(String nome) {
		NodeList nodeListFundos = parser.getDocument().getElementsByTagName("fundo");
		Fundo fundo = null;
		for ( int indice = 0 ; indice < nodeListFundos.getLength() ; indice++ ) {
			Node nodeFundo = nodeListFundos.item(indice);
			if ( nodeFundo.getAttributes() != null && nodeFundo.getAttributes().getNamedItem("nome") != null && nodeFundo.getAttributes().getNamedItem("nome").getTextContent().equals(nome) ) {
				fundo = new Fundo();
				fundo.setNome(nodeFundo.getAttributes().getNamedItem("nome").getTextContent());
				fundo.setUrl(nodeFundo.getAttributes().getNamedItem("endereco").getTextContent());
				return fundo;
			}
		}
		return fundo;
	}

	public Banco obterBanco(String numero) throws SAXException, IOException {
		NodeList nodeListBancos = parser.getDocument().getElementsByTagName("banco");
		for ( int indiceBancos = 0 ; indiceBancos < nodeListBancos.getLength() ; indiceBancos++ ) {
			Node nodeBanco = nodeListBancos.item(indiceBancos);
			if ( nodeBanco.getAttributes() != null && nodeBanco.getAttributes().getNamedItem("numero") != null && nodeBanco.getAttributes().getNamedItem("numero").getTextContent().equals(numero) ) {
				Banco banco = new Banco();
				banco.setNome(nodeBanco.getAttributes().getNamedItem("nome").getTextContent());
				banco.setNumero(nodeBanco.getAttributes().getNamedItem("numero").getTextContent());
				NodeList nodeListFundos = nodeBanco.getChildNodes().item(1).getChildNodes();
				for ( int indiceFundos = 0 ; indiceFundos < nodeListFundos.getLength() ; indiceFundos++ ) {
					Node nodeFundo = nodeListFundos.item(indiceFundos);
					if ( ! nodeFundo.getNodeName().equals("fundo") )
						continue;
					Fundo fundo = new Fundo();
					fundo.setNome(nodeFundo.getAttributes().getNamedItem("nome").getTextContent());
					fundo.setUrl(nodeFundo.getAttributes().getNamedItem("endereco").getTextContent());
					banco.getFundos().put(fundo.getNome(), fundo);
				}
				return banco;
			}
		}
		return null;
	}

	/**
	 * Obter todos os bancos e seus fundos de investimento.
	 * 
	 * @return Map contendo os bancos indexados pelo seu número.
	 * @throws SAXException
	 * @throws IOException
	 */
	public Map<String,Banco> obterBancos() throws SAXException, IOException {
		Map<String,Banco> bancos = new HashMap<String,Banco>();
		NodeList nodeListBancos = parser.getDocument().getElementsByTagName("banco");
		for ( int indiceBancos = 0 ; indiceBancos < nodeListBancos.getLength() ; indiceBancos++ ) {
			Node nodeBanco = nodeListBancos.item(indiceBancos);
			Banco banco = new Banco();
			banco.setNome(nodeBanco.getAttributes().getNamedItem("nome").getTextContent());
			banco.setNumero(nodeBanco.getAttributes().getNamedItem("numero").getTextContent());

			NodeList nodeListFundos = nodeBanco.getChildNodes().item(1).getChildNodes();
			for ( int indiceFundos = 0 ; indiceFundos < nodeListFundos.getLength() ; indiceFundos++ ) {
				Node nodeFundo = nodeListFundos.item(indiceFundos);
				if ( ! nodeFundo.getNodeName().equals("fundo") )
					continue;
				Fundo fundo = new Fundo();
				fundo.setNome(nodeFundo.getAttributes().getNamedItem("nome").getTextContent());
				fundo.setUrl(nodeFundo.getAttributes().getNamedItem("endereco").getTextContent());
				banco.getFundos().put(fundo.getNome(), fundo);
			}
		}
		return bancos;
	}

	public Banco obterFundosBancoBrasil() {
		Banco banco = new Banco();
		banco.setNome("Banco do Brasil");
		banco.setNumero("001");
		Collection<Fundo> fundos = new ArrayList<Fundo>();
		DOMParser parser = new DOMParser();
		try {
			Logger.getLogger(this.getClass().getName()).info("--- Obtendo Fundos de Investimento do Banco do Brasil.\n");
			parser.parse("fundos.xml");
			NodeList list = parser.getDocument().getElementsByTagName("fundo");
			for ( int indice = 0 ; indice < list.getLength() ; indice++ ) {
				Node node = list.item(indice);
				Fundo fundo = new Fundo();
				fundo.setNome(node.getAttributes().getNamedItem("nome").getTextContent());
				fundo.setUrl(node.getAttributes().getNamedItem("endereco").getTextContent());
				Logger.getLogger(this.getClass().getName()).info("------- Obtendo Fundo: " + fundo.getNome() + "\n");
				ExtratorCotas extrator = new ExtratorCotasBancoBrasil();
				Logger.getLogger(this.getClass().getName()).info("------- Extraindo Cotas para este fundo. Por favor, aguarde. Esta operacão pode demorar.\n");
				fundo.setCotas(extrator.extrairCotas(fundo.getUrl()));
				Logger.getLogger(this.getClass().getName()).info("------- Foram extraídas " + fundo.getCotas().size()+ " cotas.\n");
				fundos.add(fundo);
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//banco.setFundos(fundos);
		return banco;
	}

}