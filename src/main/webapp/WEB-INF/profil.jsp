<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <p>Pseudo :</p><!-- ne pas mettre si dans le titre -->
        <p>Nom :</p>
        <!-- insérer nom -->
        <p>Prénom :</p>
        <!-- insérer prénom -->
        <p>Email :</p>
        <!-- insérer mail -->
        <p>Téléphone</p><!-- pas ouf d'avoir le tel ??? : n'apparait que si c'est mon profil ?-->
        <!-- insérer tel -->
        <p>Rue :</p><!-- pas ouf d'avoir l'adresse en publique : n'apparait que si c'est mon profil ?-->
        <!-- insérer rue -->
        <p>Code Postal :</p><!-- pas ouf d'avoir l'adresse en publique : n'apparait que si c'est mon profil ?-->
        <!-- insérer CP -->
        <p>Ville :</p><!-- pas ouf d'avoir l'adresse en publique : n'apparait que si c'est mon profil ?-->
        <!-- insérer ville -->
    </div>

    <!-- le boutton n'apparait que si c'est mon profil -->
    <div>
        <a href="modifProfilServlet"><button type="submit">Modifier</button></a>
    </div>
</body>
</html>