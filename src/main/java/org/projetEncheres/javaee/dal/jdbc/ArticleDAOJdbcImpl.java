package org.projetEncheres.javaee.dal.jdbc;

import java.util.List;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.dal.*;

public class ArticleDAOJdbcImpl implements ArticleDAO{

	public ArticleDAOJdbcImpl() {

	}

	@Override
	public void insert(ArticleVendu data) throws DALException {
		
	}

	@Override
	public ArticleVendu selectByID(int id) throws DALException {
		return null;
	}

	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		return null;
	}

	@Override
	public void update(ArticleVendu data) throws DALException {
		
	}

	@Override
	public void delete(int id) throws DALException {
		
	}

	@Override
	public List<ArticleVendu> selectByCategorie(String libelle) throws DALException {
		return null;
	}

	@Override
	public List<ArticleVendu> selectByNom(String libelle) throws DALException {
		return null;
	}
	
}
