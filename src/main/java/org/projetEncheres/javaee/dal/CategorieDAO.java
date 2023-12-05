package org.projetEncheres.javaee.dal;

import java.util.List;

import org.projetEncheres.javaee.bo.Categorie;

public interface CategorieDAO extends DAO<Categorie> {
	public List<Categorie> selectByLibelle(String libelle) throws DALException;

}
