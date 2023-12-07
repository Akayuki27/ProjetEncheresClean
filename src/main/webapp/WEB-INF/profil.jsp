<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Profil<!-- nom user a inserer ? --></title>
</head>
<body>
<jsp:include page="/WEB-INF/Header.jsp">
<jsp:param value="" name="header"/>
</jsp:include>
    <h1><!-- nom user a inserer ? --></h1>
	
    <div>
        <p>Pseudo : ${requestScope.u.pseudo}</p><!-- ne pas mettre si dans le titre -->
        <p>Nom : ${u.nom}</p>
        <!-- insérer nom -->
        <p>Prénom : ${u.prenom}</p>
        <!-- insérer prénom -->
        <p>Email : ${u.email}</p>
        <!-- insérer mail -->
        <p>Téléphone: ${u.telephone}</p><!-- pas ouf d'avoir le tel ??? : n'apparait que si c'est mon profil ?-->
        <!-- insérer tel -->
        <p>Rue : ${u.rue}</p><!-- pas ouf d'avoir l'adresse en publique : n'apparait que si c'est mon profil ?-->
        <!-- insérer rue -->
        <p>Code Postal : ${u.codePostal}</p><!-- pas ouf d'avoir l'adresse en publique : n'apparait que si c'est mon profil ?-->
        <!-- insérer CP -->
        <p>Ville : ${u.ville}</p><!-- pas ouf d'avoir l'adresse en publique : n'apparait que si c'est mon profil ?-->
        <!-- insérer ville -->
    </div>

    <!-- le boutton n'apparait que si c'est mon profil -->
    <c:if test="${v.id} == ${u.id}">
    <div>
        <a href="modifProfilServlet"><button type="submit">Modifier</button></a>
    </div>
    </c:if>
</body>
</html>