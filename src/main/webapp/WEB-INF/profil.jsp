<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">

<head>
<meta charset="UTF-8">
	<title>${requestScope.u.pseudo}</title>
	<link rel="stylesheet" href="/ProjetEncheresClean/static/style.css"> 
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap" rel="stylesheet"> 
</head>

<body>
<jsp:include page="/WEB-INF/Header.jsp">
	<jsp:param value="" name="header"/>
</jsp:include>
    <div>
    	<h1>${u.pseudo }</h1>
    </div>
<div class="profilContainer">

    <div class="profilText">	
        <p>Nom : ${u.nom}</p>
        <p>Prénom : ${u.prenom}</p>
        <p>Email : ${u.email}</p>
        <p>Téléphone: ${u.telephone}</p>
        <p>Rue : ${u.rue}</p>
        <p>Code Postal : ${u.codePostal}</p>
        <p>Ville : ${u.ville}</p>
    </div>
    <!-- forcement mon profil donc -->
    <div>
        <a href="modifProfilServlet"><button type="submit">Modifier</button></a>
        <button class="bouttonAnnuler" type="button" onclick="location.href='accueilServlet'">Retour</button>
    </div>
</div>
</body>

</html>