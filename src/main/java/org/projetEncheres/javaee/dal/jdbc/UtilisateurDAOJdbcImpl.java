package org.projetEncheres.javaee.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.ConnectionProvider;
import org.projetEncheres.javaee.dal.DALException;
import org.projetEncheres.javaee.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE pseudo=?";
	private static final String INSERT = "INSERT INTO UTILISATEURS VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_BY_NO_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE no_utilisateur=?";
	private static final String SELECTALL = "SELECT * FROM UTILISATEURS";
	private static final String UPDATE = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?,"
			+ "credit=?, administrateur=? WHERE no_utilisateur=?";
	private static final String DELETE = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
	private static final String LOGIN = "SELECT * FROM UTILISATEURS WHERE pseudo=? and mot_de_passe=?";
	private static final String LOGIN_MAIL = "SELECT * FROM UTILISATEURS WHERE email=? and mot_de_passe=?";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email=?";
	
	@Override
	public Utilisateur selectByPseudo(String pseudo) throws DALException {
		Utilisateur u = null;
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(SELECT_BY_PSEUDO);) {
			rqt.setString(1, pseudo);
			ResultSet rs = rqt.executeQuery();
			if(rs.next()) {
				u = mapping(rs);
				}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException();
		}
		return u;
	}

	@Override
	public void insert(Utilisateur u) throws DALException {
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {
			rqt.setString(1, u.getPseudo());
			rqt.setString(2, u.getNom());
			rqt.setString(3, u.getPrenom());
			rqt.setString(4, u.getEmail());
			rqt.setString(5, u.getTelephone());
			rqt.setString(6, u.getRue());
			rqt.setString(7, u.getCodePostal());
			rqt.setString(8, u.getVille());
			rqt.setString(9, u.getMotDePasse());
			rqt.setInt(10, u.getCredit());
			rqt.setBoolean(11, u.getAdministrateur());
			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				try (ResultSet rs = rqt.getGeneratedKeys();) {
					if (rs.next()) {
						u.setNoUtilisateur(rs.getInt(1));
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("L'insertion de l'utilisateur " + u + " a échouée");
		}
	}

	@Override
	public Utilisateur selectByID(int id) throws DALException {
		Utilisateur u = null;
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(SELECT_BY_NO_UTILISATEUR);) {
			rqt.setInt(1, id);
			ResultSet rs = rqt.executeQuery();
			if(rs.next()) {
			u = mapping(rs);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("Sélection par id a échouée");
		}
		return u;
		
	}

	@Override
	public List<Utilisateur> selectAll() throws DALException {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(SELECTALL);) {
				ResultSet rs = rqt.executeQuery();
				while(rs.next()) {
					Utilisateur u = mapping(rs);
					utilisateurs.add(u);
				}
		}catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La sélection des utilisateurs a échouée");
		}
		return utilisateurs;
	}

	@Override
	public void update(Utilisateur u) throws DALException {
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(UPDATE);) {
			
			rqt.setString(1, u.getPseudo());
			rqt.setString(2, u.getNom());
			rqt.setString(3, u.getPrenom());
			rqt.setString(4, u.getEmail());
			rqt.setString(5, u.getTelephone());
			rqt.setString(6, u.getRue());
			rqt.setString(7, u.getCodePostal());
			rqt.setString(8, u.getVille());
			rqt.setString(9, u.getMotDePasse());
			rqt.setInt(10, u.getCredit());
			rqt.setBoolean(11, u.getAdministrateur());
			rqt.setInt(12, u.getNoUtilisateur());
			rqt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La modification des données de l'utilisateur " + u + " a échouée");
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
			throw new DALException("La suppression des données de l'utilisateur no" + id + " a échouée");
		}
	}

	private Utilisateur mapping(ResultSet rs) throws SQLException {
		Utilisateur u = null;
		int userId = rs.getInt("no_utilisateur");
		String pseudo = rs.getString("pseudo");
		String nom = rs.getString("nom");
		String prenom = rs.getString("prenom");
		String email = rs.getString("email");
		String telephone = rs.getString("telephone");
		String rue = rs.getString("rue");
		String codeP = rs.getString("code_postal");
		String ville = rs.getString("ville");
		String password = rs.getString("mot_de_passe");
		int credit = rs.getInt("credit");
		boolean admin = rs.getBoolean("administrateur");
		u = new Utilisateur(userId, pseudo, nom, prenom, email, telephone, rue, codeP, ville, password, credit, admin);

		return u;
	}
	
	public Utilisateur login(String pseudo, String motDePasse) throws DALException{

		Utilisateur u = null;
		
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement rqt = con.prepareStatement(LOGIN);
			rqt.setString(1, pseudo);
			rqt.setString(2, motDePasse);
			ResultSet rs = rqt.executeQuery();
			if(rs.next()) {
				u = mapping(rs);
				}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème dans la récupération des données utilisateur pour le login");
		}
		
		return u;
	}

	public Utilisateur loginEmail(String email, String motDePasse) throws DALException {
		Utilisateur u = null;
		
		try {
			Connection con = ConnectionProvider.getConnection();
			PreparedStatement rqt = con.prepareStatement(LOGIN_MAIL);
			rqt.setString(1, email);
			rqt.setString(2, motDePasse);
			ResultSet rs = rqt.executeQuery();
			if(rs.next()) {
				u = mapping(rs);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Problème dans la récupération des données utilisateur pour le login");
		}
		
		return u;
	}

	@Override
	public Utilisateur selectByEmail(String email) throws DALException {
		Utilisateur u = null;
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(SELECT_BY_EMAIL);) {
			rqt.setString(1, email);
			ResultSet rs = rqt.executeQuery();
			if(rs.next()) {
				u = mapping(rs);
				}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("Problème dans la récupération des données de l'utilisateur par l'email");
		}
		return u;
	}

	
}