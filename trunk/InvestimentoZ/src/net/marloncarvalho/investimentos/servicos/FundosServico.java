package net.marloncarvalho.investimentos.servicos;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.marloncarvalho.investimentos.dao.Criterio;
import net.marloncarvalho.investimentos.dao.DAOFactory;
import net.marloncarvalho.investimentos.dao.hibernate.DAOHibernate;
import net.marloncarvalho.investimentos.entidades.Fundo;
import net.marloncarvalho.investimentos.excecoes.DAOException;
import net.marloncarvalho.investimentos.excecoes.ServicosException;

public class FundosServico {
	private DAOHibernate daoFundos = (DAOHibernate) DAOFactory.getInstance().getDAO(Fundo.class);

	public Fundo obterFundoComNome(String nome) throws ServicosException {
		Criterio criterio = new Criterio();
		criterio.setNomeConsulta("fundosPorNome");
		criterio.adicionarParametro("nome", nome);
		
		try {
			Collection c = daoFundos.obterPorCriterio(criterio);
			if ( c.size() > 0 )
				return (Fundo) c.iterator().next();
			return null;
		} catch (DAOException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Ocorreu um erro ao realizar a consulta fundosPorNome." );
			throw new ServicosException(e);
		}
	}

	public Fundo obter(Long id) throws ServicosException {
		try {
			return (Fundo) this.daoFundos.obter(id);
		} catch (DAOException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Ocorreu um erro ao obter um fundo." );
			throw new ServicosException(e);
		}
	}

	public void salvar(Fundo fundo) throws ServicosException {
		try {
			this.daoFundos.salvar(fundo);
		} catch (DAOException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Ocorreu um erro ao salvar um fundo." );
			throw new ServicosException(e);
		}
	}
}
