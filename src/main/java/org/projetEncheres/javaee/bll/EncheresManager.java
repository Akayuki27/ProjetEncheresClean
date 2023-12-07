package org.projetEncheres.javaee.bll;

import java.util.List;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Categorie;
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
			if(selectByIdArt(e.getNo_article()) == null) {
			
			this.encheres.insertEnchere(e, a, u);
			} else {
				update(e);
			}
		} catch (DALException d){
			throw new BLLException ("Erreur dans l'insertion de l'enchère");
		}
	}
	
	public List<Enchere> selectAll() throws DALException, BLLException {
		try {
			return this.encheres.selectAll();
		} catch (DALException d){
			throw new BLLException ("Erreur dans la selection des enchères");
		}
	}
	
	public Enchere selectByIdArt(int id) throws DALException, BLLException {
		try {
		return this.encheres.selectByIdArticle(id);
		} catch (DALException d){
			throw new BLLException ("Erreur dans la sélection de l'enchère par le no_article");
		}
	}
	
	public Enchere selectByIdUser(int id) throws DALException, BLLException {
		try {
			return this.encheres.selectByIdUtilisateur(id);
		} catch (DALException d){
			throw new BLLException ("Erreur dans la sélection de l'enchère par le no_utilisateur");
		}
	}
	
	public void update(Enchere e) throws DALException, BLLException{
		Enchere init = selectByIdArt(e.getNo_article());
		try {
			if (init.getMontantEnchere() < e.getMontantEnchere()) {
		this.encheres.update(e);
			} else {
				throw new BLLException("L'enchère ne peut pas être inférieure à celle déjà faite");
			}
		} catch (DALException d){
			throw new BLLException ("Erreur dans l'update de l'enchère par le no_utilisateur");
		}
	}
}

