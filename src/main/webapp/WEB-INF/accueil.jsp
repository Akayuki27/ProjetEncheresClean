<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.time.LocalDate" %>
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
        <input type="radio" id="Achat" name="ChoixAchatVente" value="Achat" checked>
        <label for="radioOption1">Achats</label>

		<!-- checkbox si radioOption 1 est enclenché -->
        <div class="checkbox-container">
            <input type="checkbox" id="EnchereOuvertes" name="EnchereOuvertes" value="EnchereOuvertes" checked>
            <label for="checkbox1a">Enchères ouvertes</label>

            <input type="checkbox" id="EnchereEnCours" name="EnchereEnCours" value="EnchereEnCours">
            <label for="checkbox1b">Mes enchères en cours</label>

            <input type="checkbox" id="EnchereRemportes" name="EnchereRemportes" value="EnchereRemportes">
            <label for="checkbox1c">Mes enchères remportées</label>
        </div>
        <input type="radio" id="Ventes" name="ChoixAchatVente" value="Ventes">
        <label for="radioOption2">Mes ventes</label>
        
        <!-- checkbox si radioOption 2 est enclenché -->
         <div class="checkbox-container">
            <input type="checkbox" id="VenteEnCours" name="VenteEnCours" value="VenteEnCours">
            <label for="checkbox2a">Mes ventes en cours</label>

            <input type="checkbox" id="VenteNonDebutes" name="VenteNonDebutes" value="VenteNonDebutes">
            <label for="checkbox2b">Vente non débutées</label>

            <input type="checkbox" id="VentesTermines" name="VentesTermines" value="VentesTermines">
            <label for="checkbox2c">Ventes terminées</label>
        </div>
        
    </div>
	<script src="/ProjetEncheresClean/static/filtre.js"></script> 
      	<% } %>
		
      <button type="submit" name="rechercher">Rechercher</button>
    </form>
</div>
<div>
		  <c:forEach var="articles" items="${articles}">
			<div><p>${articles.nomArticle}</p>
			<p><img src="<c:url value='/uploads/${articles.image}' />"></p>
			<p>	Prix : ${articles.prixVente}
			</p>
				<p>Fin de l'enchère : ${articles.dateFinEncheres} </p>
						
				
				<%if (session.getAttribute ("userCo") != null) { %>
				<c:choose>
				<c:when test="${articles.dateFinEncheres.isAfter(LocalDate.now()) }"> 
						<p><a href="EnchereArticleServlet?noArt=${articles.noArticle}">Enchérir</a>
				</c:when>
				<c:otherwise>
					<p><a href="EnchereArticleServlet?noArt=${articles.noArticle}">Détail</a>
				</c:otherwise>
				</c:choose>
						<p><a href ="afficherVendeurServlet?id=${articles.no_utilisateur}">Vendeur : ${u2.pseudo}</a></p>
				<%}else { %>
						<p>Vendeur : ${u2.pseudo}</p>
				<%} %>
			</div>		
		</c:forEach>
</div>
</body>
</html>