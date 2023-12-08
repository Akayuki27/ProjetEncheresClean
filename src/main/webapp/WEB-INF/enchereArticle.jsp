<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${a.nomArticle}</title>
<link rel="stylesheet" href="/ProjetEncheresClean/static/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/Header.jsp">
<jsp:param value="" name="header"/>
</jsp:include>
    <h1>Article : ${a.nomArticle}</h1>
	
    <div>
        
        <p>Description : ${a.description}</p>
        
        <p>Catégorie: ${c.libelle} </p>
        
        <p>Début des enchères: ${a.dateDebutEncheres}</p>
       
        <p>Fin des enchères : ${a.dateFinEncheres}</p>
        
        <p>Prix de vente: <c:choose>
					<c:when test="${a.prixVente !=null}">${a.prixVente}</c:when>
					<c:otherwise>${a.miseAPrix}</c:otherwise>
				</c:choose></p>
        
        <p><a href ="afficherVendeurServlet?id=${a.no_utilisateur}">Vendeur : ${u2.pseudo}</a></p>
        
        <form method="post" action="EnchereArticleServlet">
        <input type="hidden" id="idArt" name="idArt" value="${a.noArticle}">
        <input type="number" id="enchere" name="enchere" value="${a.prixVente}"><input type="submit" value="Encherir">
        </form>
        
        
    </div>

    

   

</body>
</html>