package org.projetEncheres.javaee.bo;

import java.io.Serializable;
import java.time.LocalDate;

public class Enchere implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate dateEnchere;
	private int montantEnchere;
	private int no_utilisateur;
	private int no_article;
	
	public Enchere() {}
	
	public Enchere (int no_utilisateur, int no_article, LocalDate dateEnchere, int montantEnchere) {
		super();
		this.setNo_utilisateur(no_utilisateur);
		this.setNo_article(no_article);
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public Enchere(LocalDate dateEnchere, int montantEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public int getNo_article() {
		return no_article;
	}

	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	public int getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}
	
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Enchere{")
          .append("dateEnchere=").append(dateEnchere)
          .append(", montantEnchere=").append(montantEnchere)
          .append(", no_utilisateur=").append(no_utilisateur)
          .append(", no_article=").append(no_article)
          .append('}');
        return sb.toString();
    }
	
	
}
