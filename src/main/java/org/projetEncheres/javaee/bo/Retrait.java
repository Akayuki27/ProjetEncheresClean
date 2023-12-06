package org.projetEncheres.javaee.bo;

public class Retrait {
	private String rue;				//30char
	private String codePostal;		//10char
	private String ville;
	private int no_article;//30char
	
	
	public Retrait() {
	}


	public Retrait(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	public Retrait(int no_article, String rue, String codePostal, String ville) {
		super();
		this.setNo_article(no_article);
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}


	public String getRue() {
		return rue;
	}


	public void setRue(String rue) {
		this.rue = rue;
	}


	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public int getNo_article() {
		return no_article;
	}


	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}
	
	
	
	
}
