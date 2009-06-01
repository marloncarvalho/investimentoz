package net.marloncarvalho.investimentos.dao;

import java.util.Collection;

import net.marloncarvalho.investimentos.entidades.EntidadePersistente;
import net.marloncarvalho.investimentos.excecoes.DAOException;

/**
 * Interface que define o comportamento padrão para objetos persistíveis.
 * 
 * @author Marlon Silva Carvalho
 * @since 01/06/2009
 */
public interface DAOGenerico {

	/**
	 * Salvar uma Entidade.
	 * 
	 * @param entidade Entidade a ser salva.
	 * @throws DAOException Caso não seja possível salvar.
	 */
	public void salvar(EntidadePersistente entidade) throws DAOException;
	
	/**
	 * Excluir uma entidade.
	 * 
	 * @param entidade Entidade a ser excluída.
	 * @throws DAOException Caso não seja possível excluir.
	 */
	public void excluir(EntidadePersistente entidade) throws DAOException;
	
	/**
	 * Obter uma entidade da Base.
	 * 
	 * @param id Identificador da entidade.
	 * @return Entidade obtida.
	 * @throws DAOException Caso não seja possível obter.
	 */
	public EntidadePersistente obter(Long id) throws DAOException;

	/**
	 * Obter uma lista completa de todas entidades de um determinado tipo.
	 * 
	 * @return Lista de entidades.
	 * @throws DAOException Caso não seja possível obter a lista.
	 */
	public Collection<EntidadePersistente> obterTodos() throws DAOException;

	/**
	 * Obter uma lista de uma determinada entidade através de um critério de consulta.
	 * 
	 * @param criterio Critério de Consulta.
	 * @return Lista.
	 * @throws DAOException Caso não seja possível realizar a consulta.
	 */
	public Collection<EntidadePersistente> obterPorCriterio(Criterio criterio) throws DAOException;
}