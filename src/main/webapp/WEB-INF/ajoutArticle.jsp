<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Nouvel Article</h1>
<div>
    <form id="articleForm" action="ajoutArticleServlet" method="post">
    	
        <label for="nom">Nom de l'article: </label>
        <input type="text" id="nom" name="nom" required>
    
        <label for="Description">Description: </label>
        <input type="text" id="description" name="description" required>
    
        <label for="dateDebut">Début des enchères: </label>
        <input type="date" id="dateDebut" name="dateDebut" required>
    
        <label for="dateFin">Fin des enchères: </label>
        <input type="date" id="dateFin" name="dateFin" required>
    
        <label for="prixInitial">Prix initial: </label>
        <input type="number" id="prixInitial" name="prixInitial" min="1" max="10000" required>
        
        <label for="categories">Catégorie:</label>
      	<select id="categories" name="categories">
        <option value="informatique">Informatique</option>
        <option value="ameublement">Ameublement</option>
        <option value="vetement">Vêtements</option>
        <option value="sport_loisirs">Sport et Loisirs</option>
      	</select>
        
    
        <button type="submit">Créer</button>
        <button type="button" onclick="location.href='accueil.jsp'">Annuler</button>
        
      </form>
</div>
</body>
</html>