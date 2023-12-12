package org.projetEncheres.javaee.bo;

import java.io.Serializable;

public class Categorie implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int noCategorie;
	private String libelle;
	
	public Categorie () {}

	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Categorie{")
          .append("noCategorie=").append(noCategorie)
          .append(", libelle='").append(libelle).append('\'')
          .append('}');
        return sb.toString();
    }
	
	
}
