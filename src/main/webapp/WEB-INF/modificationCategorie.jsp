<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.time.LocalDate" %>
<!DOCTYPE html>

<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Modification de la catégorie</title>
    <link rel="stylesheet" href="/ProjetEncheresClean/static/style.css">
    <link rel="icon" href="/ProjetEncheresClean/ressources/LOSNAfavicon.png" type="image/png">
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap" rel="stylesheet"> 
</head>

<body>

    <jsp:include page="/WEB-INF/Header.jsp">
        <jsp:param value="" name="Accueil" />
    </jsp:include>

	<form id="modifCategorie" method="post" action="modificationCategorieServlet">
		<label for="nomCategorie">Nouveau nom :</label>
		<input type="hidden" id="id" name="id" value="${categorie.noCategorie }">
		<input type="text" id="nomCategorie" name="nomCategorie" value="${categorie.libelle }">
		<button type="submit">Modifier</button>
	</form>

</body>
</html>