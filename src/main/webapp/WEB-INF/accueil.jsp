<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.time.LocalDate" %>
<!DOCTYPE html>

<html lang="fr">

<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
    <link rel="stylesheet" href="/ProjetEncheresClean/static/style.css">
    <link rel="icon" href="/ProjetEncheresClean/ressources/LOSNAfavicon.png" type="image/png">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap" rel="stylesheet"> 
</head>

<body>

    <jsp:include page="/WEB-INF/Header.jsp">
        <jsp:param value="" name="Accueil" />
    </jsp:include>
    <h1>Liste des enchères</h1>

	<c:if test="${not empty erreurSuppression && erreurSuppression != null}">
		${erreurSuppression}
	</c:if>

    <div class="divEnchereFormContainer">
        <form id="enchereForm" action="AfficherEnchereServlet" method="get">
            <div id="inputEnchere">
                <div id="inputEnchereBase">
                    <div id="inputNomEnchere">
                        <label for="nomArticle">Nom<span class="hidden-text"> de l'article</span> :</label>
                        <input type="text" id="nomArticle" name="nomArticle">
                    </div>

                    <div id="inputCategorieEnchere">
                        <label for="categories">Catégorie :</label>
                        <select id="categories" name="categories" required>
                            <c:forEach var="categorie" items="${categories}">
                                <option value="${categorie.noCategorie}">${categorie.libelle}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div id="inputFiltreEnchere">
                    <%if (session.getAttribute ("userCo") !=null) { %>
                        <div class="radio-option">
	                        <div class="filtreContainer">
	                        	<div class="filtreRadio">
	                            	<input type="radio" id="Achat" name="ChoixAchatVentes" value="Achat" checked>
	                            	<label for="radioOption1">Achats</label>
	                            </div>
	
	                            <!-- checkbox si radioOption 1 est enclenché -->
	                            <div class="checkbox-container">
		                            <div class="checkbox-Item">
		                                <input type="checkbox" id="EnchereOuvertes" name="filter[]" value="EnchereOuvertes"
		                                    checked>
		                                <label for="EnchereOuvertes">Enchères ouvertes</label>
									</div>
									<div class="checkbox-Item">
		                                <input type="checkbox" id="EnchereEnCours" name="filter[]" value="EnchereEnCours">
		                                <label for="EnchereEnCours">Mes enchères en cours</label>
									</div>
									<div class="checkbox-Item">
		                                <input type="checkbox" id="EnchereRemportes" name="filter[]" value="EnchereRemportes">
		                                <label for="EnchereRemportes">Mes enchères remportées</label>
		                            </div>
	                            </div>
	                          </div>  
                            
                            <div class="filtreContainer">
	                            <div class="filtreRadio">
		                            <input type="radio" id="Ventes" name="ChoixAchatVentes" value="Ventes">
		                            <label for="Ventes">Mes ventes</label>
	                            </div>
							
	                            <!-- checkbox si radioOption 2 est enclenché -->
	                            <div class="checkbox-container">
	                            	<div class="checkbox-Item">
	                                	<input type="checkbox" id="VenteEnCours" name="filter[]" value="VenteEnCours" disabled>
	                                	<label for="VenteEnCours">Mes ventes en cours</label>
	                                </div>
									<div class="checkbox-Item">
	                                	<input type="checkbox" id="VenteNonDebutes" name="filter[]" value="VenteNonDebutes"
	                                    disabled>
	                                	<label for="VenteNonDebutes">Ventes non débutées</label>
	                                </div>
									<div class="checkbox-Item">
	                               		<input type="checkbox" id="VentesTermines" name="filter[]" value="VentesTermines"
	                                    disabled>
	                                	<label for="VentesTermines">Ventes terminées</label>
	                                </div>
	                            </div>
                            </div>

                        </div>
                        <script src="/ProjetEncheresClean/static/filtre.js"></script>
                        <% } %>
                </div>
            </div>

            <div class="inputChercher">
                <button type="submit" name="rechercher">Rechercher</button>
            </div>

        </form>
    </div>


    <div class="afficherEnchereContainer" id="enchereContainer">
        <c:forEach var="articles" items="${articles}">
            <div class="afficherEnchereItem">
                <div class="imgAfficherEnchereBloc">
                    <c:choose>
                        <c:when test="${not empty articles.image}">
                            <img class="imgAfficherEnchere" src="<c:url value='/uploads/${articles.image}' />">
                        </c:when>
                        <c:otherwise>
                            <img class="imgAfficherEnchere" src="<c:url value='/ressources/noImage2.jpg' />">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="texteAfficherEnchere">
                    <h3>${articles.nomArticle}</h3>
                    <p> Prix : ${articles.prixVente}</p>
                    <p>Fin de l'enchère : ${articles.dateFinEncheres} </p>

                    <%if (session.getAttribute ("userCo") !=null) { %>
                        <c:choose>
                            <c:when
                                test="${articles.dateDebutEncheres.isAfter(LocalDate.now()) && u.noUtilisateur == articles.no_utilisateur}">
                                <p><a href="modifArticleServlet?noArt=${articles.noArticle}">Modifier</a>
                            </c:when>
                            <c:when test="${articles.dateFinEncheres.isAfter(LocalDate.now()) || articles.dateFinEncheres.isEqual(LocalDate.now())}">
                                <p><a href="EnchereArticleServlet?noArt=${articles.noArticle}">Enchérir</a>
                            </c:when>
                            <c:otherwise>
                                <p><a href="EnchereArticleServlet?noArt=${articles.noArticle}">Détails</a>
                            </c:otherwise>
                        </c:choose>
                        <p>Vendeur : <a href="afficherVendeurServlet?id=${articles.no_utilisateur}">${articles.u2.pseudo}</a></p>
                        <%}else { %>
                            <p>Vendeur : ${articles.u2.pseudo}</p>
                            <%} %>
                </div>
            </div>
        </c:forEach>
    </div>
	<script src="/ProjetEncheresClean/static/enchereReverse.js"></script>
</body>

</html>