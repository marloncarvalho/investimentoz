package net.marloncarvalho.investimentos.exportador.implementador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.marloncarvalho.investimentos.entidades.Banco;
import net.marloncarvalho.investimentos.entidades.Cota;
import net.marloncarvalho.investimentos.entidades.Fundo;
import net.marloncarvalho.investimentos.excecoes.ServicosException;
import net.marloncarvalho.investimentos.exportador.Exportador;
import net.marloncarvalho.investimentos.servicos.BancosServico;
import net.marloncarvalho.investimentos.servicos.CotasServico;
import net.marloncarvalho.investimentos.servicos.FundosServico;

/**
 * Exportador de Cotas para Banco de Dados.
 * 
 * @author Marlon Silva Carvalho
 * @since 01/06/2009
 */
public class ExportadorBD implements Exportador {
	private FundosServico fundosServico = new FundosServico();
	private BancosServico bancosServico = new BancosServico();
	private CotasServico cotasServico = new CotasServico();
	
	@Override
	public void exportar(Banco banco) {

	}

	@Override
	public void exportar(Fundo fundo) {
		try {
			// Verificar se o fundo j� est� salvo. 
			Fundo fundoBD = fundosServico.obterFundoComNome(fundo.getNome());
			Fundo fundoAtivo = null;

			// Trata-se de um fundo de investimento que j� est� cadastrado no BD.
			if ( fundoBD != null ) {

				// Atualizar as informa��es do fundo.
				fundoBD.setUrl(fundo.getUrl());

				// Verificar se o banco do fundo de investimento foi trocado no XML.
				Banco novoBanco = fundo.getBanco();
				if ( ! novoBanco.getNome().equals(fundoBD.getBanco().getNome())) {
					// Foi trocado, ent�o verificar se o novo banco do XML j� existe no BD.
					// Caso sim, atualizar as informa��es.
					Banco b = bancosServico.obterBancoPorNome(novoBanco.getNome());
					if ( b != null ) {
						// Atualizar as informa��es.
						b.setNome(novoBanco.getNome());
						b.setNumero(novoBanco.getNumero());
					} else {
						// Sen�o, salvar como novo.
						b = novoBanco;
					}
					bancosServico.salvar(b);
					fundoBD.setBanco(b);
				}
				fundosServico.salvar(fundoBD);
				fundoAtivo = fundoBD;
			}  else {
				// Neste caso, � um novo fundo de investimento.
				Banco novoBanco = fundo.getBanco();
				Banco b = bancosServico.obterBancoPorNome(novoBanco.getNome());
				if ( b != null ) {
					b.setNome(novoBanco.getNome());
					b.setNumero(novoBanco.getNumero());
				} else {
					b = novoBanco;
				}
				bancosServico.salvar(b);
				fundo.setBanco(b);
				fundosServico.salvar(fundo);
				fundoAtivo = fundo;
			}

			// Salvar todos os fundos.
			for (Cota cota:fundo.getCotas()) {
				// Verificar se esta cota j� foi salva.
				Collection cotas = cotasServico.obterCotaPorFundoData(fundoAtivo.getId(), cota.getData());
				if ( cotas.size() <= 0 ) {
					cota.setFundo(fundoAtivo);
					cotasServico.salvar(cota);
				}
			}
		} catch (ServicosException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "", e );
		}

	}

}