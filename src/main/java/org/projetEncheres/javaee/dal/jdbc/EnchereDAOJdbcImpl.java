package org.projetEncheres.javaee.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	private static final String SELECTBYID_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article=?";
	private static final String SELECTBYID_USER = "SELECT * FROM ENCHERES WHERE no_utilisateur=?";
	private static final String UPDATE = "UPDATE ENCHERES SET no_utilisateur=?, date_enchere=?, montant_enchere=? WHERE no_article=?";
	private static final String DELETE = "DELETE FROM ENCHERES WHERE no_article=?";

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
	public void update(Enchere e) throws DALException {
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(UPDATE);) {
			rqt.setInt(1, e.getNo_utilisateur());
			rqt.setDate(2, Date.valueOf(e.getDateEnchere()));
			rqt.setInt(3, e.getMontantEnchere());
			rqt.setInt(4, e.getNo_article());
			rqt.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("L'update de l'enchere a échouée");
		}
		
	}

	@Override
	public void delete(int idArt) throws DALException {
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(DELETE);) {
			rqt.setInt(1,idArt);
			rqt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La suppression de l'enchere a échouée");
		}
		
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

	@Override
	public Enchere selectByIdArticle(int idArt) throws DALException {
		Enchere e = null;
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(SELECTBYID_ARTICLE);) {
			rqt.setInt(1, idArt);
			ResultSet rs = rqt.executeQuery();
			if(rs.next()) {
				e = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), (rs.getDate("date_enchere").toLocalDate()), rs.getInt("montant_enchere"));
			}
		} catch(SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La sélection de l'enchere par no article a échouée");
		}
		return e;
	}

	@Override
	public Enchere selectByIdUtilisateur(int idUser) throws DALException {
		Enchere e = null;
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(SELECTBYID_USER);) {
			rqt.setInt(1, idUser);
			ResultSet rs = rqt.executeQuery();
			if(rs.next()) {
				e = new Enchere(rs.getInt("no_utilisateur"), rs.getInt("no_article"), (rs.getDate("date_enchere").toLocalDate()), rs.getInt("montant_enchere"));
			}
		} catch(SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La sélection de l'enchere par no utilisateur a échouée");
		}
		return e;
	}

}
