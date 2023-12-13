package org.projetEncheres.javaee.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class ArticleVendu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int noArticle;
	private String nomArticle;			 	//30char
	private String description;				//300char
	private LocalDate dateDebutEncheres;		
	private LocalDate dateFinEncheres;		
	private int miseAPrix;			
	private int prixVente;			
	private boolean etatVente;
	private int no_utilisateur;
	private Utilisateur u2;
	private int no_categorie;
	private String image;
	private int winner;
	
	public ArticleVendu () {
		
	}
	
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, int no_utilisateur, int no_categorie, String image ) {
		super();
		
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
		this.image = image;
		
	}
	
	

	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, boolean etatVente, int no_utilisateur, int no_categorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
	}
	
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, boolean etatVente, int no_utilisateur, int no_categorie, String image) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
		this.image = image;
	}
	
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, boolean etatVente, int no_utilisateur, int no_categorie, String image, int winner) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
		this.image = image;
		this.winner = winner;
	}
	
	
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, int no_utilisateur, int no_categorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
	}
	
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, int no_utilisateur, int no_categorie) {
		super();
		
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
	}
	
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVente, boolean etatVente, int no_utilisateur, int no_categorie, String image, int winner, Utilisateur u2) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
		this.image = image;
		this.winner = winner;
		this.u2 = u2;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public boolean getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(boolean etatVente) {
		this.etatVente = etatVente;
	}

	public int getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public int getNo_categorie() {
		return no_categorie;
	}

	public void setNo_categorie(int no_categorie) {
		this.no_categorie = no_categorie;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}
	
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("ArticleVendu{")
          .append("noArticle=").append(noArticle)
          .append(", nomArticle='").append(nomArticle).append('\'')
          .append(", description='").append(description).append('\'')
          .append(", dateDebutEncheres=").append(dateDebutEncheres)
          .append(", dateFinEncheres=").append(dateFinEncheres)
          .append(", miseAPrix=").append(miseAPrix)
          .append(", prixVente=").append(prixVente)
          .append(", etatVente=").append(etatVente)
          .append(", no_utilisateur=").append(no_utilisateur)
          .append(", no_categorie=").append(no_categorie)
          .append(", image='").append(image).append('\'')
          .append(", winner=").append(winner)
          .append('}');
        return sb.toString();
    }

	public Utilisateur getU2() {
		return u2;
	}

	public void setU2(Utilisateur u2) {
		this.u2 = u2;
	}

	
	
	
}
