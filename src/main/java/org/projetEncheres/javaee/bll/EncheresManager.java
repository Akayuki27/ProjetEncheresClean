package org.projetEncheres.javaee.bll;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Enchere;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.DALException;
import org.projetEncheres.javaee.dal.DAOFactory;
import org.projetEncheres.javaee.dal.EnchereDAO;

public class EncheresManager {
	
	private EnchereDAO encheres;
	
	public EncheresManager() {
		this.encheres = DAOFactory.getEnchereDAO();
	}
	
	public void insertEnchere (Enchere e, ArticleVendu a, Utilisateur u) throws DALException, BLLException {
		try {
			this.encheres.insertEnchere(e, a, u);
		} catch (DALException d){
			throw new BLLException ("Erreur dans l'insertion de l'enchère");
		}
	}
	
	
}

