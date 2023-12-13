<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@page import="java.time.LocalDate" %>
	<%@page import="java.sql.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modification Article</title>
<link rel="stylesheet" href="/ProjetEncheresClean/static/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/Header.jsp">
	<jsp:param value="" name="Accueil"/>
</jsp:include>

<h1>Modification de l'article</h1>
<div>

    <form id="articleForm" action="modifArticleServlet" method="post" enctype="multipart/form-data"> 
    	
    	<input type="hidden" name="idArt" id ="idArt" value="${a.noArticle}">
        <label for="nom">Nom de l'article :</label>
        <input type="text" id="nom" name="nom" value="${a.nomArticle}" required>
        
    
        <label for="Description">Description :</label>
        <input type="text" id="description" name="description" value="${a.description}" required>
           
        <label for="categories">Catégorie :</label>
      	<select id="categories" name="categories" required>
  			<c:forEach var="categorie" items="${categories}">
           		<option value="${categorie.libelle}">${categorie.libelle}</option>
        	</c:forEach>
      	</select>     
        
    	<label for="fileInput">Sélectionnez une image :</label>
    	<input type="file" name="fileInput" id="fileInput" value="${a.image}">
    	<br>
		
		<label for="prix">Prix initial (supérieur à 0) :</label>
    	<input type="number" id="prixInitial" name="prixInitial" value="100" min="1" step="1" pattern="\d+" value="${a.miseAPrix}"required>
    
    	<p>La date de début des enchère doit être inférieure à celle de fin des enchères</p>
    
        <label for="dateDebut">Début des enchères :</label>
        <input type="date" id="dateDebut" name="dateDebut" value="${Date.valueOf(a.dateDebutEncheres)}" required>
    
        <label for="dateFin">Fin des enchères :</label>
        <input type="date" id="dateFin" name="dateFin" value="${Date.valueOf(a.dateFinEncheres)}" required>
      	
      	<h2>Adresse de retrait</h2>
      	
      	<label for="rue">Rue :</label>
    	<input type="text" id="rue" name="rue" value="${u.rue}">

    	<label for="codePostal">Code Postal :</label>
    	<input type="text" id="codePostal" name="codePostal" value="${u.codePostal}">

    	<label for="ville">Ville :</label>
    	<input type="text" id="ville" name="ville" value="${u.ville}">
        
    
        <button type="submit">Modifier</button>
        <button type="button" onclick="location.href='accueil.jsp'">Annuler</button>
        
      </form>
      
      <form id="boutonAnnulerVente" action="AnnulerVente" method="post">
      <input type="hidden" name="noArticle" value="${a.noArticle}">
      <button type="submit" >Annuler la vente</button>
      </form>

</div>

</body>
</html>