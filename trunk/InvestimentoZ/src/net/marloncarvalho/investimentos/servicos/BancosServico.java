package net.marloncarvalho.investimentos.servicos;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.marloncarvalho.investimentos.dao.Criterio;
import net.marloncarvalho.investimentos.dao.DAOFactory;
import net.marloncarvalho.investimentos.dao.hibernate.DAOHibernate;
import net.marloncarvalho.investimentos.entidades.Banco;
import net.marloncarvalho.investimentos.excecoes.DAOException;
import net.marloncarvalho.investimentos.excecoes.ServicosException;

public class BancosServico {
	private DAOHibernate daoBancos = (DAOHibernate) DAOFactory.getInstance().getDAO(Banco.class);
	
	public Banco obterBancoPorNome(String nome) throws ServicosException {
		Criterio criterio = new Criterio();
		criterio.setNomeConsulta("bancosPorNome");
		criterio.adicionarParametro("nome", nome);
		
		try {
			Collection c = daoBancos.obterPorCriterio(criterio);
			if ( c.size() > 0 )
				return (Banco) c.iterator().next();
			return null;
		} catch (DAOException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Ocorreu um erro ao realizar a consulta bancosPorNome." );
			throw new ServicosException(e);
		}
	}

	public void salvar(Banco banco) throws ServicosException {
		try {
			this.daoBancos.salvar(banco);
		} catch (DAOException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "", e);
			throw new ServicosException(e);
		}
	}

}
