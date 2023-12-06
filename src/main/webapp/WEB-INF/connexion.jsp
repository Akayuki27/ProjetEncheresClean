<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.html">
<jsp:param value="" name="header"/>
</jsp:include>
    <h1>Connexion</h1>
<div>
  <form id="connexionForm" action="connexionServlet" method="post"><!-- insérer servlet -->
    <label for="identifiant">Identifiant:</label>
    <input type="text" id="identifiant" name="identifiant" required>

    <label for="motDePasse">Mot de passe:</label>
    <input type="password" id="motDePasse" name="motDePasse" required>

    <label>
      <input type="checkbox" name="seSouvenirDeMoi"> Se souvenir de moi
    </label>

    <button type="submit">Se connecter</button>

    <p><a href="modifierProfil">Mot de passe oublié</a></p><!-- insérer un truc -->
    <!-- insérer boutton créer un compte -->
  </form>
</div>
</body>
</html>