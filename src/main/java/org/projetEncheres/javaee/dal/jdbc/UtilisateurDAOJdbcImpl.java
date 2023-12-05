package org.projetEncheres.javaee.dal.jdbc;

import java.util.List;

import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.DALException;
import org.projetEncheres.javaee.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	@Override
	public void insert(Object data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object data) throws DALException {
		
	}

	@Override
	public void insert(Utilisateur data) throws DALException {
		
	}

	@Override
	public Utilisateur selectByID(int id) throws DALException {
		return null;
	}

	@Override
	public List<Utilisateur> selectAll() throws DALException {
		return null;
	}

	@Override
	public void update(Utilisateur u) throws DALException {
		
	}

	@Override
	public void delete(int id) throws DALException {
		
	}

	@Override
	public Utilisateur selectByPseudo(String pseudo) throws DALException {
		return null;
	}

}
