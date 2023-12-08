package org.projetEncheres.javaee.dal;

import org.projetEncheres.javaee.bo.Retrait;

public interface RetraitDAO extends DAO<Retrait> {
	
	public void insertRetrait(Retrait retrait) throws DALException;

}
