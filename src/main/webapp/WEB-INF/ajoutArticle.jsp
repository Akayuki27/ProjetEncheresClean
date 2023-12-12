<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/ProjetEncheresClean/static/style.css">
</head>
<body>
<h1>Nouvel Article</h1>
<div>

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
		
		<label for="prix">Prix initial :</label>
    	<input type="number" id="prixInitial" name="prixInitial" value="100" min="0" step="1" pattern="\d+" required>
    
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
        
    
        <button type="submit">Créer</button>
        <button class="bouttonAnnuler" type="button" onclick="location.href='accueilServlet'">Annuler</button>
        
      </form>

</div>
</body>
</html>