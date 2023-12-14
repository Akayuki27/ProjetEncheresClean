<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Inscription</title>
<link rel="stylesheet" href="/ProjetEncheresClean/static/style.css"> 
</head>
<body>
<jsp:include page="/WEB-INF/Header.jsp">
<jsp:param value="" name="header"/>
</jsp:include>
	<h1>Inscription</h1>
	<!--Le mot de passe doit contenir 8 charactères, au moins 1 Maj, 1 min et 1 chiffre, à mettre en popup-->
<div class="FormContainer">
    <form id="inscriptionForm" action="inscriptionServlet" method="post">
        <label for="pseudo">Pseudo:</label>
        <input type="text" id="pseudo" name="pseudo" required>
    
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" required>
    
        <label for="prenom">Prénom:</label>
        <input type="text" id="prenom" name="prenom" required>
    
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
    
        <label for="telephone">Téléphone:</label>
        <input type="tel" id="telephone" name="telephone" pattern="^0[0-9]{9}$" required>
        
        <label for="rue">Rue:</label>
        <input type="text" id="rue" name="rue" required>
    
        <label for="codePostal">Code postal:</label>
        <input type="text" id="codePostal" name="codePostal" pattern="^\d{5}$" required>
    
        <label for="ville">Ville:</label>
        <input type="text" id="ville" name="ville" required>
    
        <label for="motDePasse">Mot de passe:</label>
        <input type="password" id="motDePasse" name="motDePasse" required>
    
        <label for="confirmation">Confirmation:</label>
        <input type="password" id="confirmation" name="confirmation" required> <!-- controle MDP-->
    
        <button type="submit">Créer</button>
        <button type="button" onclick="location.href='accueilServlet'">Annuler</button>
      </form>
</div>
</body>
</html>