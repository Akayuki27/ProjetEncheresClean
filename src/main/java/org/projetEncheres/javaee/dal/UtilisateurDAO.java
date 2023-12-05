package org.projetEncheres.javaee.dal;

import java.util.List;

import org.projetEncheres.javaee.bo.Utilisateur;

@SuppressWarnings("rawtypes")
public interface UtilisateurDAO extends DAO {
	public void insert (Utilisateur data) throws DALException;
	public Utilisateur selectByID(int id) throws DALException;
	public List<Utilisateur> selectAll() throws DALException;
	public void update (Utilisateur u) throws DALException;
	public void delete(int id) throws DALException;
	public Utilisateur selectByPseudo(String pseudo) throws DALException;
}
