package org.projetEncheres.javaee.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Enchere;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.ConnectionProvider;
import org.projetEncheres.javaee.dal.DALException;
import org.projetEncheres.javaee.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	
	private static final String INSERT = "INSERT INTO ENCHERES VALUES(?,?,?,?)";

	@Override
	public void insert(Enchere data) throws DALException {
		
	}

	@Override
	public Enchere selectByID(int id) throws DALException {
		return null;
	}

	@Override
	public List<Enchere> selectAll() throws DALException {
		return null;
	}

	@Override
	public void update(Enchere data) throws DALException {
		
	}

	@Override
	public void delete(int id) throws DALException {
		
	}

	@Override
	public void insertEnchere(Enchere e, ArticleVendu a, Utilisateur u) throws DALException {
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(INSERT);) {
			rqt.setInt(1, u.getNoUtilisateur());
			rqt.setInt(2, a.getNoArticle());
			rqt.setDate(3, Date.valueOf(e.getDateEnchere()));
			rqt.setInt(4, e.getMontantEnchere());
			rqt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("L'insertion de l'enchere " + e + " a échouée");
		}
		
	}

}
