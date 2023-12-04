package org.projetEncheres.javaee.dal.jdbc;

import java.util.List;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.dal.*;

public class ArticleDAOJdbcImpl implements ArticleDAO{

	public ArticleDAOJdbcImpl() {

	}

	@Override
	public void insert(ArticleVendu data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArticleVendu selectByID(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ArticleVendu data) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ArticleVendu> selectByCategorie(String libelle) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
