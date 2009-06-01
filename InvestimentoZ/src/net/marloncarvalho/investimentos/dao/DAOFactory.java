package net.marloncarvalho.investimentos.dao;

import net.marloncarvalho.investimentos.dao.hibernate.DAOHibernate;
import net.marloncarvalho.investimentos.entidades.Cota;

public final class DAOFactory {

	private static DAOFactory instance = new DAOFactory();
	
	public static DAOFactory getInstance() {
		return instance;
	}

	public DAOGenerico getDAO(Class cls) {
		return new DAOHibernate(cls);
	}


}