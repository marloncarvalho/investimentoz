package net.marloncarvalho.investimentos.exportador.implementador;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.marloncarvalho.investimentos.entidades.Banco;
import net.marloncarvalho.investimentos.entidades.Fundo;
import net.marloncarvalho.investimentos.excecoes.ServicosException;
import net.marloncarvalho.investimentos.exportador.Exportador;
import net.marloncarvalho.investimentos.servicos.BancosServico;
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
	
	@Override
	public void exportar(Banco banco) {

	}

	@Override
	public void exportar(Fundo fundo) {
		try {
			// Verificar se o fundo já está salvo. 
			Fundo fBD = fundosServico.obterFundoComNome(fundo.getNome());
			if ( fBD != null ) {
				// Atualizar as informações do fundo.
				fBD.setNome(fundo.getNome());
				fBD.setUrl(fundo.getUrl());
				// Verificar se é um novo banco, ou não.
				Banco novoBanco = fundo.getBanco();
				Banco b = bancosServico.obterBancoPorNome(fundo.getBanco().getNome());
				if ( b != null ) {
					b.setNome(novoBanco.getNome());
					b.setNumero(novoBanco.getNumero());
				} 
				bancosServico.salvar(fundo.getBanco());
				fBD.setBanco(fundo.getBanco());
			} 
			fundosServico.salvar(fundo);
		} catch (ServicosException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "", e );
		}

	}

}