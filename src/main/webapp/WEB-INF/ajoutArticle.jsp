<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvel Article</title>
<link rel="stylesheet" href="/ProjetEncheresClean/static/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap" rel="stylesheet"> 
</head>
<body>
<jsp:include page="/WEB-INF/Header.jsp">
        <jsp:param value="" name="Accueil" />
</jsp:include>
<h1>Nouvel Article</h1>
<div class="FormContainer">

    <form id="articleForm" action="ajoutArticleServlet" method="post" enctype="multipart/form-data"> 
    	
        <label for="nom">Nom de l'article :</label>
        <input type="text" id="nom" name="nom" required>
    
        <label for="Description">Description :</label>
        <input type="text" id="description" name="description" required>
           
        <label for="categories">Catégorie :</label>
      	<select id="categories" name="categories" required>
  			<c:forEach var="categorie" items="${categories}">
           		<option value="${categorie.libelle}">${categorie.libelle}</option>
        	</c:forEach>
      	</select>     
        
    	<label for="fileInput">Sélectionnez une image :</label>
    	<input type="file" name="fileInput" id="fileInput">
    	<br>
		
		<label for="prix">Prix initial (supérieur à 0) :</label>
    	<input type="number" id="prixInitial" name="prixInitial" value="100" min="1" step="1" pattern="\d+" required>
    	
    	<p id="dateEnchere">La Date de début des enchères doit être inférieur a celle de fin</p>
    
        <label for="dateDebut">Début des enchères :</label>
        <input type="date" id="dateDebut" name="dateDebut" required>
    
        <label for="dateFin">Fin des enchères :</label>
        <input type="date" id="dateFin" name="dateFin" required>
      	
      	<h2>Adresse de retrait</h2>
      	
      	<label for="rue">Rue :</label>
    	<input type="text" id="rue" name="rue" value="${u.rue}">

    	<label for="codePostal">Code Postal :</label>
    	<input type="text" id="codePostal" name="codePostal" value="${u.codePostal}">

    	<label for="ville">Ville :</label>
    	<input type="text" id="ville" name="ville" value="${u.ville}">
        
    <div class="bouttonContainer">
        <button type="submit">Créer</button>
        <button class="bouttonAnnuler" type="button" onclick="location.href='accueilServlet'">Annuler</button>
    </div>    
      </form>

</div>
</body>
</html>