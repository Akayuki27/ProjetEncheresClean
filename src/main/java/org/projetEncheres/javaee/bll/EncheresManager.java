package org.projetEncheres.javaee.bll;

import java.util.List;

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
			if(selectByIdArt(e.getNo_article()) == null && enchereValide(e)) {
			
			this.encheres.insertEnchere(e, a, u);
			} else {
				e.setNo_utilisateur(u.getNoUtilisateur());
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
	
	public List<Enchere> selectByIdUser(int id) throws DALException, BLLException {
		try {
			return this.encheres.selectByIdUtilisateur(id);
		} catch (DALException d){
			throw new BLLException ("Erreur dans la sélection de l'enchère par le no_utilisateur");
		}
	}
	
	public void update(Enchere e) throws DALException, BLLException{
		
		try {
			if (enchereValide(e)) {
		this.encheres.update(e);
			} else {
				throw new BLLException("L'enchère ne peut pas être inférieure à celle déjà faite");
			}
		} catch (DALException d){
			throw new BLLException ("Erreur dans l'update de l'enchère par le no_utilisateur");
		}
	}
	
	public boolean enchereValide(Enchere e) {
		boolean enchereOK = true;
		ArticleVendu a;
		ArticleManager amgr = new ArticleManager();
		try {
			a = amgr.selectByID(e.getNo_article());
			if (e.getDateEnchere().isAfter(a.getDateFinEncheres())) {
				enchereOK = false;
			}
			if (e.getMontantEnchere() <= a.getPrixVente() || e.getMontantEnchere() <= a.getMiseAPrix()) {
				enchereOK = false;
			}
		} catch (DALException e1) {
			e1.printStackTrace();
		}
		return enchereOK;
	}
}

