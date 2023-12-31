package org.projetEncheres.javaee.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.projetEncheres.javaee.bo.ArticleVendu;
import org.projetEncheres.javaee.bo.Categorie;
import org.projetEncheres.javaee.bo.Utilisateur;
import org.projetEncheres.javaee.dal.ArticleDAO;
import org.projetEncheres.javaee.dal.ConnectionProvider;
import org.projetEncheres.javaee.dal.DALException;


public class ArticleDAOJdbcImpl implements ArticleDAO {

	private final static String INSERT = "INSERT INTO ARTICLES_VENDUS VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private final static String SELECTBYID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?";
	private final static String SELECTALL = "SELECT * FROM ARTICLES_VENDUS WHERE etat_vente=0";
	private final static String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, "
			+ "prix_vente=?, no_utilisateur=?, no_categorie=?, image=?, winner=? WHERE no_article=?";
	private final static String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";
	private final static String SELECTBYID_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie=?";
	private final static String SELECTBYNOM_ARTICLE = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ?";
	private final static String SELECTBYNOM_ARTICLEETCATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ? AND no_categorie =?";
	private static String SELECTBYCRITERIA = "SELECT * FROM ARTICLES_VENDUS WHERE 1=1";
	private static final String UPDATEVENTE = "UPDATE ARTICLES_VENDUS SET etat_vente = 1 WHERE no_article=?";
	private final static String SELECTBYID_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur=?";

	public ArticleDAOJdbcImpl() {

	}

	@Override
	public void insert(ArticleVendu a) throws DALException {
		throw new DALException("Cette fonction n'a rien à faire là, utliser insertArticle() à la place");
	}

	@Override
	public ArticleVendu selectByID(int id) throws DALException {
		ArticleVendu a = null;
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(SELECTBYID);) {
			rqt.setInt(1, id);
			ResultSet rs = rqt.executeQuery();
			if(rs.next()) {
			a = mapping(rs);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La selection de l'article no " + id + " a échouée");
		}
		return a;
	}

	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		List<ArticleVendu> articles = new ArrayList<>();
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(SELECTALL);) {
			ResultSet rs = rqt.executeQuery();
			while (rs.next()) {
				ArticleVendu a = mapping(rs);
				articles.add(a);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La selection des articles a échouée");
		}
		return articles;
	}

	@Override
	public void update(ArticleVendu a) throws DALException {
		try (Connection con = ConnectionProvider.getConnection();
			PreparedStatement rqt = con.prepareStatement(UPDATE);){
			rqt.setString(1, a.getNomArticle());
			rqt.setString(2, a.getDescription());
			rqt.setDate(3, Date.valueOf(a.getDateDebutEncheres()));
			rqt.setDate(4, Date.valueOf(a.getDateFinEncheres()));
			rqt.setInt(5, a.getMiseAPrix());
			rqt.setInt(6, a.getPrixVente());
			rqt.setInt(7, a.getNo_utilisateur());
			rqt.setInt(8, a.getNo_categorie());
			rqt.setString(9, a.getImage());
			rqt.setInt(10, a.getWinner());
			rqt.setInt(11, a.getNoArticle());
			rqt.executeUpdate();
		
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La mise à jour de l'article " + a + " a échouée");
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
			throw new DALException("La suppression de l'article no " + id + " a échouée");
		}

	}

	@Override
	public List<ArticleVendu> selectByCategorie(int id_categorie) throws DALException {
		List<ArticleVendu> articles = new ArrayList<>();
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(SELECTBYID_CATEGORIE);) {
			rqt.setInt(1, id_categorie);
			ResultSet rs = rqt.executeQuery();
			while (rs.next()) {
				ArticleVendu a = mapping(rs);
				articles.add(a);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La selection des articles par catégorie a échouée");
		}
		return articles;
		
	}

	@Override
	public List<ArticleVendu> selectByNom(String nom) throws DALException {
		List<ArticleVendu> articles = new ArrayList<>();
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(SELECTBYNOM_ARTICLE);) {
			rqt.setString(1, "%" + nom + "%");
			ResultSet rs = rqt.executeQuery();
			while (rs.next()) {
				ArticleVendu a = mapping(rs);
				articles.add(a);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La selection des articles nommés " + nom + " a échouée");
		}
		return articles;
	}

	@Override
	public int insertArticle(ArticleVendu a, Utilisateur u, Categorie c) throws DALException {
		
		int numeroArticle = -1;
		
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {
			
			rqt.setString(1, a.getNomArticle());
			rqt.setString(2, a.getDescription());
			rqt.setDate(3, Date.valueOf(a.getDateDebutEncheres()));
			rqt.setDate(4, Date.valueOf(a.getDateFinEncheres()));
			rqt.setInt(5, a.getMiseAPrix());
			rqt.setInt(6, a.getPrixVente());
			rqt.setInt(7, u.getNoUtilisateur());
			rqt.setInt(8, c.getNoCategorie());
			rqt.setBoolean(9,false);
			rqt.setString(10, a.getImage());
			rqt.setInt(11, 0);
			
			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				try (ResultSet rs = rqt.getGeneratedKeys()) {
					if(rs.next()) {
					a.setNoArticle(rs.getInt(1));
					numeroArticle = rs.getInt(1);
					}
				}
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("Linsertion de l'article " + a + " a échouée");
		}
		return numeroArticle;
	}

	private ArticleVendu mapping(ResultSet rs) throws SQLException {
		ArticleVendu a = null;
		int noArt = rs.getInt("no_article");
		String nom = rs.getString("nom_article");
		String description = rs.getString("description");
		LocalDate dateDebut = rs.getDate("date_debut_encheres").toLocalDate();
		LocalDate dateFin = rs.getDate("date_fin_encheres").toLocalDate();

		int prixInitial = rs.getInt("prix_initial");
		int prixVente = rs.getInt("prix_vente");
		boolean etatVente = rs.getBoolean("etat_vente");
		int no_utilisateur = rs.getInt("no_utilisateur");
		int no_categorie = rs.getInt("no_categorie");
		
		String image = rs.getString("image");
		int winner = rs.getInt("winner");

		a = new ArticleVendu(noArt, nom, description, dateDebut, dateFin, prixInitial, prixVente, etatVente, no_utilisateur,
				no_categorie, image, winner);

		return a;
	}
	
	@Override
	public List<ArticleVendu> selectByNometCategorie(String nom, int id) throws DALException {
		List<ArticleVendu> articles = new ArrayList<>();
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(SELECTBYNOM_ARTICLEETCATEGORIE);) {
			rqt.setString(1, "%" + nom + "%");
			rqt.setInt(2, id);
			ResultSet rs = rqt.executeQuery();
			while (rs.next()) {
				ArticleVendu a = mapping(rs);
				articles.add(a);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La selection des articles nommés " + nom + id + " a échouée");
		}
		return articles;
	}

	@Override
	public List<ArticleVendu> selectByCriteria(String c1, String c2, String c3, String c4, String c5)
			throws DALException {
		
		List<ArticleVendu> articles = new ArrayList<>();
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(SELECTBYCRITERIA)) {
			
			if (c1 != null) {
				SELECTBYCRITERIA += "AND date_debut_encheres <=?";
				rqt.setDate(1, Date.valueOf(LocalDate.now()));
			}
			if (c2 != null) {
				SELECTBYCRITERIA += "AND no_utilisateur =?";
				int id = 0;
				rqt.setInt(2, id);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		
		return articles;
	}

	@Override
	public void updateEtatVente(int id) throws DALException {
		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement rqt = con.prepareStatement(UPDATEVENTE);){
		rqt.setInt(1, id);
		rqt.executeUpdate();
		
	} catch (SQLException e1) {
		e1.printStackTrace();
		throw new DALException("La mise à jour de l'état de la vente a échoué");
	}
	}

	@Override
	public List<ArticleVendu> selectByIdUtilisateur(int id) throws DALException {
		List<ArticleVendu> articles = new ArrayList<>();
		try (Connection con = ConnectionProvider.getConnection(); PreparedStatement rqt = con.prepareStatement(SELECTBYID_UTILISATEUR);) {
			rqt.setInt(1, id);
			ResultSet rs = rqt.executeQuery();
			while (rs.next()) {
				ArticleVendu a = mapping(rs);
				articles.add(a);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new DALException("La selection des articles par utilisateur a échouée");
		}
		return articles;
	}
	
	

}
