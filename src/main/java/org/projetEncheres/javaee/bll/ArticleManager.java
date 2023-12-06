package org.projetEncheres.javaee.bll;

import org.projetEncheres.javaee.dal.ArticleDAO;
import org.projetEncheres.javaee.dal.DAOFactory;

public class ArticleManager {
	
	private ArticleDAO article;
	
	public ArticleManager () {
		this.article = DAOFactory.getArticleDAO();
	}

}
