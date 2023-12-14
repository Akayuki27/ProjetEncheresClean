<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Inscription</title>
<link rel="stylesheet" href="/ProjetEncheresClean/static/style.css">
<link rel="icon" href="/ProjetEncheresClean/ressources/LOSNAfavicon.png" type="image/png">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap" rel="stylesheet">  
</head>
<body>
<jsp:include page="/WEB-INF/Header.jsp">
<jsp:param value="" name="header"/>
</jsp:include>
	<h1>Inscription</h1>
	<!--Le mot de passe doit contenir 8 charactères, au moins 1 Maj, 1 min et 1 chiffre, à mettre en popup-->

<div class="FormContainer">
	<div class="inscriptionContainer">
	    <form id="inscriptionForm" action="inscriptionServlet" method="post">
	    	<div class="inscriptionText">
			    <div class="inscriptionText1">
				    <div class="inscriptionTextItem">
				        <label for="pseudo">Pseudo:</label>
				        <input type="text" id="pseudo" name="pseudo" required>
				    </div>
				    <div class="inscriptionTextItem">
				        <label for="nom">Nom:</label>
				        <input type="text" id="nom" name="nom" required>
				    </div>
				    <div class="inscriptionTextItem">
				        <label for="prenom">Prénom:</label>
				        <input type="text" id="prenom" name="prenom" required>
				    </div>
				    <div class="inscriptionTextItem">
				        <label for="email">Email:</label>
				        <input type="email" id="email" name="email" required>
				    </div>
				    <div class="inscriptionTextItem">
				        <label for="telephone">Téléphone:</label>
				        <input type="tel" id="telephone" name="telephone" pattern="^0[0-9]{9}$" required>
				    </div>
				</div>    
			    <div class="inscriptionText2">
				    <div class="inscriptionTextItem">
				        <label for="rue">Rue:</label>
				        <input type="text" id="rue" name="rue" required>
				    </div>
				    <div class="inscriptionTextItem">
				        <label for="codePostal">Code postal:</label>
				        <input type="text" id="codePostal" name="codePostal" pattern="^\d{5}$" required>
				    </div>
				    <div class="inscriptionTextItem">
				        <label for="ville">Ville:</label>
				        <input type="text" id="ville" name="ville" required>
				    </div>
				    <div class="inscriptionTextItem">
				        <label for="motDePasse">Mot de passe:</label>
				        <input type="password" id="motDePasse" name="motDePasse" required>
				    </div>
				    <div class="inscriptionTextItem">
				        <label for="confirmation">Confirmation:</label>
				        <input type="password" id="confirmation" name="confirmation" required> <!-- controle MDP-->
				    </div> 
			    </div>   
		    </div>
		    <div class="inscriptionBoutton">
		        <button type="submit">Créer</button>
		        <button class="bouttonAnnuler" type="button" onclick="location.href='accueilServlet'">Annuler</button>
		    </div>    
	    </form>
	</div>
</div>
</body>
</html>