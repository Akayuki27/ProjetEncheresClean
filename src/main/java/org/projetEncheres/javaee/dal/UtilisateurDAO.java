package org.projetEncheres.javaee.dal;

import java.sql.Connection;
import java.sql.SQLException;

import org.projetEncheres.javaee.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur> {
	
	public Utilisateur selectByPseudo(String pseudo) throws DALException;
	public Utilisateur login(String pseudo, String motDePasse) throws DALException;
	public Utilisateur loginEmail(String email, String motDePasse) throws DALException;
}
