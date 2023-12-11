<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Accueil</title>
<link rel="stylesheet" href="/ProjetEncheresClean/static/style.css"> 
</head>
<body>

<jsp:include page="/WEB-INF/Header.jsp">
	<jsp:param value="" name="Accueil"/>
</jsp:include>
    <h1>Liste des enchères</h1>
<div>
    <form id="EnchereForm" action="AfficherEnchereServlet" method="get"><!-- insérer servlet -->
      <label for="nomArticle">Le nom de l'article contient:</label>
      <input type="text" id="nomArticle" name="nomArticle">
  
        <label for="categories">Catégorie :</label>
      	<select id="categories" name="categories" required>
  			<c:forEach var="categorie" items="${categories}">
           		<option value="${categorie.noCategorie}">${categorie.libelle}</option>
        	</c:forEach>
      	</select>
      	<c:if test="${session.getAttribute('userCo')!=null}">
      	</c:if>
		
      <button type="submit" name="rechercher">Rechercher</button>
    </form>
</div>
<div>
		  <c:forEach var="articles" items="${articles}">
			<div><p>${articles.nomArticle}</p>
			<p>	Prix : ${articles.prixVente}
			</p>
				<p>Fin de l'enchère : ${articles.dateFinEncheres} </p>
						
				
				<c:choose>
					<c:when test="${session.getAttribute('userCo')!=null}">
						<p><a href="EnchereArticleServlet?noArt=${articles.noArticle}">Enchérir</a>
						<p><a href ="afficherVendeurServlet?id=${articles.no_utilisateur}">Vendeur : ${u2.pseudo}</a></p>
					</c:when>
					<c:otherwise>
						<p>Vendeur : ${u2.pseudo}</p>
					</c:otherwise>
				</c:choose>		
			</div>		
		</c:forEach>
</div>
</body>
</html>