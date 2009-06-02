package net.marloncarvalho.investimentos.servicos;

import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.marloncarvalho.investimentos.dao.Criterio;
import net.marloncarvalho.investimentos.dao.DAOFactory;
import net.marloncarvalho.investimentos.dao.hibernate.DAOHibernate;
import net.marloncarvalho.investimentos.entidades.Cota;
import net.marloncarvalho.investimentos.excecoes.DAOException;
import net.marloncarvalho.investimentos.excecoes.ServicosException;

/**
 * Serviço para Cotas.
 * 
 * @author Marlon
 * @since 01/06/2009
 */
public class CotasServico {
	private DAOHibernate daoCotas = (DAOHibernate) DAOFactory.getInstance().getDAO(Cota.class);

	/**
	 * Obter todas as cotas de um fundo em uma data.
	 * 
	 * @param idFundo Identificador do Fundo.
	 * @param data Data da Cota.
	 * @return Lista de Cotas.
	 * @throws ServicosException Caso não seja possível obter as cotas.
	 */
	public Collection<Cota> obterCotaPorFundoData(Long idFundo, Date data) throws ServicosException {
		Criterio criterio = new Criterio();
		criterio.setNomeConsulta("cotasPorFundoData");
		criterio.adicionarParametro("data", data);
		criterio.adicionarParametro("idFundo", idFundo);
		try {
			Collection retorno = daoCotas.obterPorCriterio(criterio);
			return retorno;
		} catch (DAOException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Ocorreu um erro ao realizar a consulta cotasPorFundoData." );
			throw new ServicosException(e);
		}
	}

	public void salvar(Cota cota) throws ServicosException {
		try {
			daoCotas.salvar(cota);
		} catch (DAOException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Ocorreu um erro ao salvar uma cota." );
			throw new ServicosException(e);
		}
	}
	
}