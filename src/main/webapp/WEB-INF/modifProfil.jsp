<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Modification du profil</title>
</head>
<body>
<body>
<jsp:include page="/WEB-INF/Header.jsp">
<jsp:param value="" name="header"/>
</jsp:include>
    <p>Modification de mon profil</p>
<div>
    <form id="inscriptionForm" action="modificationServlet" method="post"><!-- insérer servlet -->
        <label for="pseudo">Pseudo:</label>
        <input type="text" id="pseudo" name="pseudo" required>
    
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" required>
    
        <label for="prenom">Prénom:</label>
        <input type="text" id="prenom" name="prenom" required>
    
        <label for="email">Email:</label><!-- Etrange qu'on puisse changer de mail ? -->
        <input type="email" id="email" name="email" required>
    
        <label for="telephone">Téléphone:</label>
        <input type="tel" id="telephone" name="telephone" pattern="^0[0-9]{9}$" required>
        
        <label for="rue">Rue:</label>
        <input type="text" id="rue" name="rue" required>
    
        <label for="codePostal">Code postal:</label>
        <input type="text" id="codePostal" name="codePostal" pattern="^\d{5}$" required>
    
        <label for="ville">Ville:</label>
        <input type="text" id="ville" name="ville" required>

        <p>mot de passe actuel :</p>
        <!-- insérer mdp actuel -->
    
        <label for="motDePasse">Nouveau mot de passe:</label>
        <input type="password" id="motDePasse" name="motDePasse" required>
    
        <label for="confirmation">Confirmation:</label>
        <input type="password" id="confirmation" name="confirmation" required>

        <p>Crédit :</p>
        <!-- insérer crédit -->
    
        <button type="submit">Enregister</button>
        <button type="submit" formaction="supprimerCompteServlet">Supprimer mon compte</button><!-- insérer servlet -->
        <button type="button" onclick="location.href='accueil.html'">Annuler</button><!-- pas dans la maquette mais bon -->
      </form>     
</div>

</body>
</html>