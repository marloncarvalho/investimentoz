package net.marloncarvalho.investimentos.dao.hibernate;

import java.util.Collection;

import net.marloncarvalho.investimentos.dao.Criterio;
import net.marloncarvalho.investimentos.dao.DAOGenerico;
import net.marloncarvalho.investimentos.entidades.EntidadePersistente;
import net.marloncarvalho.investimentos.excecoes.DAOException;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Implementação de DAO genérico para persistência em hibernate.
 * 
 * @author Marlon Silva Carvalho
 * @since 01/06/2009
 */
@SuppressWarnings("all")
public class DAOHibernate implements DAOGenerico {
	private Class cls = null;
	
	public DAOHibernate(Class cls) {
		this.cls = cls;
	}

	@Override
	public void excluir(EntidadePersistente entidade) throws DAOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction(); 
		session.delete(entidade);
		session.flush();
		tx.commit();
	}

	@Override
	public EntidadePersistente obter(Long id) throws DAOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		EntidadePersistente entidade = (EntidadePersistente) session.load(cls, id);
		return entidade;
	}

	@Override
	public Collection<EntidadePersistente> obterTodos() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvar(EntidadePersistente entidade) throws DAOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(entidade);
		} catch (Exception e) {
			session.update(entidade);
		}
		session.flush();
		tx.commit();
	}

	@Override
	public Collection<EntidadePersistente> obterPorCriterio(Criterio criterio) throws DAOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.getNamedQuery(criterio.getNomeConsulta());
		query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if ( criterio.getQuantidade() > 0 ) { 
			query.setMaxResults(criterio.getQuantidade());
		}
		if ( criterio.getRegistroInicial() > 0 ) {
			query.setFirstResult(criterio.getRegistroInicial());
		}
		for(String key : criterio.getParametros().keySet()) {
			Object param = criterio.getParametros().get(key);
			if ( Collection.class.isInstance(param)) {
				query.setParameterList(key, (Collection) param);
			} else {
				query.setParameter(key, param);
			}
		}
		return query.list();
	}

}