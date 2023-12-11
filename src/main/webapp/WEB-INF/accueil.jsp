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
      	<%if (session.getAttribute ("userCo") != null) { %>
      	<div class="radio-option">
        <input type="radio" id="radioOption1" name="radioOptions" value="option1">
        <label for="radioOption1">Achats</label>

        <div class="checkbox-container">
            <input type="checkbox" id="checkbox1a" name="checkbox1a" value="checkbox1a">
            <label for="checkbox1a">Enchères ouvertes</label>

            <input type="checkbox" id="checkbox1b" name="checkbox1b" value="checkbox1b">
            <label for="checkbox1b">Mes enchères en cours</label>

            <input type="checkbox" id="checkbox1c" name="checkbox1c" value="checkbox1c">
            <label for="checkbox1c">Mes enchères remportées</label>
        </div>
    </div>

    <div class="radio-option">
        <input type="radio" id="radioOption2" name="radioOptions" value="option2">
        <label for="radioOption2">Mes ventes</label>

        <div class="checkbox-container">
            <input type="checkbox" id="checkbox2a" name="checkbox2a" value="checkbox2a">
            <label for="checkbox2a">Mes ventes en cours</label>

            <input type="checkbox" id="checkbox2b" name="checkbox2b" value="checkbox2b">
            <label for="checkbox2b">Vente non débutées</label>

            <input type="checkbox" id="checkbox2c" name="checkbox2c" value="checkbox2c">
            <label for="checkbox2c">Ventes terminées</label>
        </div>
    </div>
      	<% } %>
		
      <button type="submit" name="rechercher">Rechercher</button>
    </form>
</div>
<div>
		  <c:forEach var="articles" items="${articles}">
			<div><p>${articles.nomArticle}</p>
			<p>	Prix : ${articles.prixVente}
			</p>
				<p>Fin de l'enchère : ${articles.dateFinEncheres} </p>
						
				
				<%if (session.getAttribute ("userCo") != null) { %>
						<p><a href="EnchereArticleServlet?noArt=${articles.noArticle}">Enchérir</a>
						<p><a href ="afficherVendeurServlet?id=${articles.no_utilisateur}">Vendeur : ${u2.pseudo}</a></p>
				<%}else { %>
						<p>Vendeur : ${u2.pseudo}</p>
				<%} %>
			</div>		
		</c:forEach>
</div>
</body>
</html>