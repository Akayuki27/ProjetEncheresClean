<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Connexion</title>
<link rel="stylesheet" href="/ProjetEncheresClean/static/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap" rel="stylesheet"> 
</head>
<body>
<jsp:include page="/WEB-INF/Header.jsp">
<jsp:param value="" name="header"/>
</jsp:include>
    <h1>Connexion</h1>
    <c:if test="${not empty error && error != null}">
    	${error}
    </c:if>
<div class="FormContainer">
  <form id="connexionForm" action="connexionServlet" method="post"><!-- insérer servlet -->
    <label for="identifiant">Identifiant:</label>
    <input type="text" id="identifiant" name="identifiant" value="${cookie.seSouvenirDeMoi.value}" required>

    <label for="motDePasse">Mot de passe:</label>
    <input type="password" id="motDePasse" name="motDePasse" required>

    <label>
      <input type="checkbox" name="seSouvenirDeMoi"> Se souvenir de moi
    </label>
	<div class="bouttonContainer">
	    <button type="submit">Se connecter</button>
	</div>
	<div id="connexionLink">
    <a href="inscriptionServlet">S'inscrire</a>
    <a href="modifierProfilServlet">Mot de passe oublié</a><!-- insérer un truc -->
    </div>
  </form>
</div>
</body>
</html>