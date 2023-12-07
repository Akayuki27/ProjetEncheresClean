<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Accueil</title>
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
  
      <label for="categories">Catégories:</label>
      <select id="categories" name="categories">
        <option value="toutes">Toutes</option>
        <option value="informatique">Informatique</option>
        <option value="ameublement">Ameublement</option>
        <option value="vetement">Vêtement</option>
        <option value="sport_loisirs">Sport et Loisirs</option>
      </select>
		
      <button type="submit" name="rechercher">Rechercher</button>
    </form>
</div>
<div>
		  <c:forEach var="articles" items="${articles}">
			<div><p>${articles.nomArticle}</p>
			<p>	Prix : <c:choose>
					<c:when test="${encheres.montantEncheres !=null}">${encheres.montantEncheres}</c:when>
					<c:otherwise>${articles.miseAPrix }</c:otherwise>
				</c:choose>
			</p>
				<p>Fin de l'enchère : ${articles.dateFinEncheres} </p>		
				
				<%if (session.getAttribute("userCo") !=null) { %>
				<p><a href ="afficherVendeurServlet?id=${articles.no_utilisateur}">Vendeur : ${u2.pseudo}</a></p>
				<%} else {%>
				<p>Vendeur : ${u2.pseudo}</p>
				<%} %>			
			</div>		
		</c:forEach>
</div>
</body>
</html>