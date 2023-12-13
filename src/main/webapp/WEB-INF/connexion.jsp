<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Connexion</title>
<link rel="stylesheet" href="/ProjetEncheresClean/static/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/Header.jsp">
<jsp:param value="" name="header"/>
</jsp:include>
    <h1>Connexion</h1>
<div class="FormContainer">
  <form id="connexionForm" action="connexionServlet" method="post"><!-- insérer servlet -->
    <label for="identifiant">Identifiant:</label>
    <input type="text" id="identifiant" name="identifiant" value="${cookie.seSouvenirDeMoi.value}" required>

    <label for="motDePasse">Mot de passe:</label>
    <input type="password" id="motDePasse" name="motDePasse" required>

    <label>
      <input type="checkbox" name="seSouvenirDeMoi"> Se souvenir de moi
    </label>

    <button type="submit">Se connecter</button>
    <br/>
    <a href="inscriptionServlet">S'inscrire</a>

    <p><a href="modifierProfilServlet">Mot de passe oublié</a></p><!-- insérer un truc -->
    <!-- insérer boutton créer un compte -->
  </form>
</div>
</body>
</html>