package org.projetEncheres.javaee.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.dal.CategorieDAO;
import org.projetEncheres.javaee.dal.ConnectionProvider;
import org.projetEncheres.javaee.dal.DALException;


public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	private static final String INSERT = "INSERT INTO CATEGORIES VALUES(?)";
	private static final String SELECT_BY_LIBELLE = "SELECT * FROM CATEGORIES WHERE libelle=?";
	private static final String SELECTALL = "SELECT * FROM CATEGORIES";
	private static final String UPDATE = "UPDATE CATEGORIES SET libelle=? WHERE no_categorie=?";
	private static final String DELETE = "DELETE FROM CATEGORIES WHERE no_categorie=?";
	private static final String SELECTBYID = "SELECT * FROM CATEGORIES WHERE no_categorie=?";

	@Override
	public void insert(Categorie c) throws DALException {
		try ( 
				Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {
			rqt.setString(1, c.getLibelle());
			int nbRows = rqt.executeUpdate();
			if(nbRows == 1) {
				try (ResultSet rs = rqt.getGeneratedKeys();) {
					if (rs.next()) {
						c.setNoCategorie(rs.getInt(1));
					}
				}
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("L'insertion de catégorie a échoué");
		}
	}

	@Override
	public Categorie selectByID(int id) throws DALException {
		Categorie c = null;	
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(SELECTBYID);) {
			rqt.setInt(1, id);
			ResultSet rs = rqt.executeQuery();
			if (rs.next()) {
				int no_categorie = rs.getInt("no_categorie");
				String cate_libelle = rs.getString("libelle");
				c = new Categorie(no_categorie, cate_libelle);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La sélection par id a échouée");
		}
		return c;
		
		
	}

	@Override
	public List<Categorie> selectAll() throws DALException {
		
		List<Categorie> categories = new ArrayList<>();	
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(SELECTALL);) {
			ResultSet rs = rqt.executeQuery();
			while(rs.next()) {
				int no_categorie = rs.getInt("no_categorie");
				String cate_libelle = rs.getString("libelle");
				Categorie c = new Categorie(no_categorie, cate_libelle);
				categories.add(c);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La sélection des catégories a échouée");
		}
		return categories;
	}
		

	@Override
	public void update(Categorie c) throws DALException {
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(UPDATE);) {
			rqt.setString(1, c.getLibelle());
			rqt.setInt(2, c.getNoCategorie());
			rqt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La mise à jour de la catégorie " + c + " a échouée");
		}
		
	}

	@Override
	public void delete(int id) throws DALException {
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(DELETE);) {
			rqt.setInt(1, id);
			rqt.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La suppression de la catégorie no" + id + " a échouée");
		}
		
	}

	@Override
	public Categorie selectByLibelle(String libelle) throws DALException {
		Categorie c = null;	
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(SELECT_BY_LIBELLE);) {
			rqt.setString(1, libelle);
			ResultSet rs = rqt.executeQuery();
			if (rs.next()) {
				int no_categorie = rs.getInt("no_categorie");
				String cate_libelle = rs.getString("libelle");
				c = new Categorie(no_categorie, cate_libelle);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La sélection par libelle a échouée");
		}
		return c;
	}

}
