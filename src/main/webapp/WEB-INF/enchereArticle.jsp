<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${a.nomArticle}</title>
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
    <h1>Article : ${a.nomArticle}</h1>
	
    <div class="detailArticleContainer">
    	<div class="detailArticleItem">     
	        <div class="imgAfficherAticleContainer">
				<c:choose>
					<c:when test="${not empty a.image }">
						<img class="imgAfficherEnchere" src="<c:url value='/uploads/${a.image}' />">
					</c:when>
					<c:otherwise>
						<img class="imgAfficherEnchere" src="<c:url value='/ressources/noImage.jpg' />">
					</c:otherwise>
				</c:choose>
			</div>
		    <div class="detailArticleText">
		        <p>Description : ${a.description}</p>        
		        <p>Catégorie : ${c.libelle} </p>      
		        <p>Début des enchères : ${a.dateDebutEncheres}</p>      
		        <p>Fin des enchères : ${a.dateFinEncheres}</p>     
		        <p>Prix de vente : 
		        <c:choose>
					<c:when test="${a.prixVente !=null}">${a.prixVente}</c:when>
					<c:otherwise>${a.miseAPrix}</c:otherwise>
				</c:choose>
				<a href ="afficherVendeurServlet?id=${a.no_utilisateur}">Vendeur : ${u2.pseudo}</a>
		    </div>
	    </div>
		<div>
	        <c:if test="${a.dateFinEncheres.isAfter(LocalDate.now()) || a.dateFinEncheres.isEqual(LocalDate.now())}">        
		        <form class="bouttonEncherir" method="post" action="EnchereArticleServlet">
			        <input type="hidden" id="idArt" name="idArt" value="${a.noArticle}">
			        <input type="number" id="enchere" name="enchere" value="${a.prixVente}">
			        <!--  <input type="submit" value="Encherir">-->
			        <button type="submit" value="Encherir">Encherir</button>
	        	</form>
        	</c:if>
        </div>
    </div>

    

   

</body>
</html>