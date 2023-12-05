package org.projetEncheres.javaee.dal;

import org.projetEncheres.javaee.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur> {
	
	public Utilisateur selectByPseudo(String pseudo) throws DALException;
}
