<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Gestion des catégories (administrateur)</title>
<link rel="stylesheet" href="/ProjetEncheresClean/static/style.css"> 
</head>
<body>
	
    <jsp:include page="/WEB-INF/Header.jsp">
        <jsp:param value="" name="Accueil" />
    </jsp:include>

	<form id="ajoutCategorieAdmin" action="GestionCategorieAdmin" method="post">
		<label for="ajoutCategorie">Nouvelle Catégorie : </label>
		<input type="text" id="ajoutCategorie" name="ajoutCategorie">
		<button type="submit">Ajouter</button>
	</form>
	
	 <c:forEach var="categorie" items="${categories}">
        <p>${categorie.libelle}</p>
        <a href="modificationCategorieServlet?id=${categorie.noCategorie}&libelle=${categorie.libelle}">Modifier</a>
        <a href="suppressionCategorieServlet?id=${categorie.noCategorie}">Supprimer</a>
    </c:forEach>
	
</body>
</html>