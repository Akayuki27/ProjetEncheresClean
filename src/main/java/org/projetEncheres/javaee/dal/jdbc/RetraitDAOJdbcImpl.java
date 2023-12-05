package org.projetEncheres.javaee.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Retrait;
import org.projetEncheres.javaee.dal.DALException;
import org.projetEncheres.javaee.dal.JDBCTools;
import org.projetEncheres.javaee.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	
	private static final String INSERT = "INSERT INTO RETRAITS VALUES(?,?,?,?)";

	@Override
	public void insert(Retrait data) throws DALException {
		
	}

	@Override
	public Retrait selectByID(int id) throws DALException {
		return null;
	}

	@Override
	public List<Retrait> selectAll() throws DALException {
		return null;
	}

	@Override
	public void update(Retrait data) throws DALException {
		
	}

	@Override
	public void delete(int id) throws DALException {
		
	}

	@Override
	public void insertRetrait(Retrait retrait, ArticleVendu a) throws DALException {
		try (Connection con = JDBCTools.getConnection(); PreparedStatement rqt = con.prepareStatement(INSERT);) {
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
