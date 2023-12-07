package org.projetEncheres.javaee.bll;

import java.util.List;

import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.dal.CategorieDAO;
import org.projetEncheres.javaee.dal.DALException;
import org.projetEncheres.javaee.dal.DAOFactory;

public class CategorieManager {
	
	private CategorieDAO categorie;
	
	public CategorieManager () {
		this.categorie = DAOFactory.getCategorieDAO();
	}
	
	public void AjouterCategorie(Categorie c) throws BLLException, DALException {
		try {
		this.categorie.insert(c);
		} catch (DALException d) {
			throw new BLLException ("Erreur dans l'insertion des catégories");
		}
		
	}
	
	public Categorie selectByID (int id) throws DALException, BLLException {
		try {
		return this.categorie.selectByID(id);
		} catch (DALException d){
			throw new BLLException ("Erreur dans la selection par l'id");
		}
	}
	
	public List<Categorie> selectAll() throws DALException, BLLException {
		try {
			return this.categorie.selectAll();
		} catch (DALException d){
			throw new BLLException ("Erreur dans la selection des catégories");
		}
	}
	
	public void update(Categorie c) throws DALException, BLLException {
		try {
			this.categorie.update(c);
		} catch (DALException d){
			throw new BLLException ("Erreur dans l'update");
		}
	}
	
	public void delete (int id) throws DALException, BLLException {
		try {
			this.categorie.delete(id);
		} catch (DALException d){
			throw new BLLException ("Erreur dans la suppresion");
		}
	}
	
	public Categorie selectByLibelle(String libelle) throws DALException, BLLException {
		try {
			return this.categorie.selectByLibelle(libelle);
		} catch (DALException d){
			throw new BLLException ("Erreur dans la selection de la catégorie par libelle");
		}
	}
}





