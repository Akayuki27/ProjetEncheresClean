<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil du Vendeur</title>
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
    <h1>${u2.pseudo}</h1>
	
	<div class="profilContainer">
	    <div class="profilText">
	        <p>Nom : ${u2.nom}</p>
	        <p>Prénom : ${u2.prenom}</p>
	        <p>Email : ${u2.email}</p>
	        <p>Téléphone: ${u2.telephone}</p>
	    </div>
	    
	    <c:if test="${userCo.administrateur}">
	    <form action="afficherVendeurServlet" method="post">
	    <input type="hidden" name="supprimerUtilisateur" value="${u2.noUtilisateur}">
	    <button type="submit" >Supprimer utilisateur</button>
	    </form>
	    </c:if>
    </div>

    
</body>
</html>