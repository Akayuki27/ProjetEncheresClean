package org.projetEncheres.javaee.bll;

import java.util.List;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.ArticleDAO;
import org.projetEncheres.javaee.dal.DALException;
import org.projetEncheres.javaee.dal.DAOFactory;

public class ArticleManager {

	private ArticleDAO article;

	public ArticleManager() {
		this.article = DAOFactory.getArticleDAO();
	}

	public void AjouterArticle(ArticleVendu a, Utilisateur u, Categorie c) throws DALException, BLLException {
		boolean dateOk, prixOk;
		try {
			dateOk = verifDateEncheres(a);
			prixOk = verifPrix(a);
			// Test si declaration des variables utiles
			if (dateOk && prixOk) {
				this.article.insertArticle(a, u, c);
			}
		} catch (BLLException e) {
			throw new BLLException("La date de début des enchères ne peux pas être après la date de fin");
		}

	}

	public ArticleVendu selectByID(int id) throws DALException, BLLException {
		return this.article.selectByID(id);
	}

	public List<ArticleVendu> selectAll() throws DALException, BLLException {
		return this.article.selectAll();
	}

	public void update(ArticleVendu a) throws DALException, BLLException {
		boolean dateOk, prixOk;
		try {
			dateOk = verifDateEncheres(a);
			prixOk = verifPrix(a);
			if (dateOk && prixOk) {
				this.article.update(a);
			}
		} catch (BLLException e) {
			throw new BLLException("La date de début des enchères ne peux pas être après la date de fin");
		}

	}

	public void delete(int id) throws DALException, BLLException {
		this.article.delete(id);
	}

	public List<ArticleVendu> selectByCategorie(int id_categorie) throws DALException, BLLException {
		return this.article.selectByCategorie(id_categorie);
	}

	public List<ArticleVendu> selectByNom(String nom) throws DALException, BLLException {
		return this.article.selectByNom(nom);
	}

	public boolean verifDateEncheres(ArticleVendu a) throws BLLException {
		boolean dateOk = true;
		if (a.getDateDebutEncheres().isAfter(a.getDateFinEncheres())) {
			dateOk = false;
			throw new BLLException("La date de début des enchères ne peux pas être après la date de fin");
		}

		return dateOk;
	}

	public boolean verifPrix(ArticleVendu a) throws BLLException {
		boolean prixOk = true;
		if (a.getMiseAPrix() <= 0) {
			prixOk = false;
		}
		return prixOk;
	}

	public List<ArticleVendu> selectByNometCategorie(String nom, int id) throws DALException {

		return this.article.selectByNometCategorie(nom, id);

	}

	public void updateEtatVente(int idArt) throws DALException {
		this.article.updateEtatVente(idArt);
	}

}
