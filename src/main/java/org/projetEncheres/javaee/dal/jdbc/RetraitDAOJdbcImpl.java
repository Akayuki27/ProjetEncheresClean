package org.projetEncheres.javaee.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Retrait;
import org.projetEncheres.javaee.dal.ConnectionProvider;
import org.projetEncheres.javaee.dal.DALException;
import org.projetEncheres.javaee.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	
	private static final String INSERT = "INSERT INTO RETRAITS VALUES(?,?,?,?)";
	private static final String DELETE = "DELETE FROM RETRAITS WHERE no_article=?";
	private static final String UPDATE = "UPDATE RETRAITS SET rue=?, code_postal=?, ville=? WHERE no_article=?";
	private static final String SELECTBYID = "SELECT * FROM RETRAITS WHERE no_article=?";

	@Override
	public void insert(Retrait data) throws DALException {
		
	}

	@Override
	public Retrait selectByID(int idArt) throws DALException {
		Retrait r = null;
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(SELECTBYID);) {
			rqt.setInt(1, idArt);
			ResultSet rs = rqt.executeQuery();
			if(rs.next()) {
				r = new Retrait(rs.getInt("no_article"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La suppression du retrait a échouée");
		}
		return r;
	}

	@Override
	public List<Retrait> selectAll() throws DALException {
		return null;
	}

	@Override
	public void update(Retrait r) throws DALException {
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(UPDATE);) {
			rqt.setString(1, r.getRue());
			rqt.setString(2, r.getCodePostal());
			rqt.setString(3, r.getVille());
			rqt.setInt(4, r.getNo_article());
			rqt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La suppression du retrait a échouée");
		}
		
	}

	@Override
	public void delete(int idArt) throws DALException {
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(DELETE);) {
			rqt.setInt(1, idArt);
			rqt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La suppression du retrait a échouée");
		}
		
	}

	@Override
	public void insertRetrait(Retrait retrait, ArticleVendu a) throws DALException {
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(INSERT);) {
			rqt.setInt(1, a.getNoArticle());
			rqt.setString(2, retrait.getRue());
			rqt.setString(3, retrait.getCodePostal());
			rqt.setString(4, retrait.getVille());
			rqt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("L'insertion du retrait " + retrait + " a échouée");
		}
	}

}
