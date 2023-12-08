package org.projetEncheres.javaee.bll;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Retrait;
import org.projetEncheres.javaee.dal.DALException;
import org.projetEncheres.javaee.dal.DAOFactory;
import org.projetEncheres.javaee.dal.RetraitDAO;

public class RetraitManager {

	private RetraitDAO retrait;

	public RetraitManager() {
		this.retrait = DAOFactory.getRetraitDAO();
	}

	public Retrait selectByIdArt(int idArt) throws DALException, BLLException {
		try {
			return this.retrait.selectByID(idArt);
		} catch (DALException d) {
			throw new BLLException("Erreur dans la sélection du retrait");
		}
	}

	public void updateRetrait(Retrait r) throws DALException, BLLException {
		try {
			if (verifRetrait(r)) {
				this.retrait.update(r);
			}
		} catch (DALException d) {
			throw new BLLException("Erreur dans la mise à jour du retrait");
		}
	}

	public void deleteRetrait(int idArt) throws DALException, BLLException {
		try {
			this.retrait.delete(idArt);
		} catch (DALException d) {
			throw new BLLException("Erreur dans la suppression du retrait");
		}
	}

	public void insertRetrait(Retrait r) throws DALException, BLLException {
		try {
			if (verifRetrait(r)) {
				this.retrait.insertRetrait(r);
			} else {
				throw new BLLException("Le format de l'adresse est invalide");
			}
		} catch (DALException d) {
			throw new BLLException("Erreur dans la mise à jour du retrait");
		}
	}

	private boolean verifRetrait(Retrait r) {
		boolean retraitOK = true;
		if (r.getRue().length() > 40 || r.getCodePostal().length() > 15 || r.getVille().length() > 30) {
			retraitOK = false;
		}
		return retraitOK;
	}

}
