<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Modification du profil</title>
<link rel="stylesheet" href="/ProjetEncheresClean/static/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap" rel="stylesheet"> 
</head>
<body>
<body>
<jsp:include page="/WEB-INF/Header.jsp">
<jsp:param value="" name="header"/>
</jsp:include>
    <p>Modification de mon profil</p>
    
<div>
    <form id="inscriptionForm" action="modifProfilServlet" method="post"><!-- insérer servlet -->
    	<input type="hidden" id="id" name="id" value="${u.noUtilisateur}">
        <label for="pseudo">Pseudo:</label>
        <input type="text" id="pseudo" name="pseudo" value="${u.pseudo}"required>
    
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" value="${u.nom}"required>
    
        <label for="prenom">Prénom:</label>
        <input type="text" id="prenom" name="prenom" value="${u.prenom}" required>
    
        <label for="email">Email:</label><!-- Etrange qu'on puisse changer de mail ? -->
        <input type="email" id="email" name="email" value="${u.email}" required>
    
        <label for="telephone">Téléphone:</label>
        <input type="tel" id="telephone" name="telephone" pattern="^0[0-9]{9}$" value="${u.telephone}"required>
        
        <label for="rue">Rue:</label>
        <input type="text" id="rue" name="rue" value="${u.rue}"required>
    
        <label for="codePostal">Code postal:</label>
        <input type="text" id="codePostal" name="codePostal" pattern="^\d{5}$" value="${u.codePostal}"required>
    
        <label for="ville">Ville:</label>
        <input type="text" id="ville" name="ville" value="${u.ville}"required>

        <p>Mot de passe actuel : ${u.motDePasse}</p>
        <!-- insérer mdp actuel -->
    
        <label for="motDePasse">Nouveau mot de passe:</label>
        <input type="password" id="motDePasse" name="motDePasse" >
    
        <label for="confirmation">Confirmation:</label>
        <input type="password" id="confirmation" name="confirmation" >

        <p>Crédit : ${u.credit}</p>
        <!-- insérer crédit -->
    
        <button type="submit">Enregister</button>
        <button type="submit" formaction="supprimerProfilServlet">Supprimer mon compte</button><!-- insérer servlet -->
        <button type="button" onclick="location.href='accueilServlet'">Annuler</button><!-- pas dans la maquette mais bon -->
      </form>     
</div>

</body>
</html>