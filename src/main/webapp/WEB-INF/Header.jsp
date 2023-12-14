  <!-- Logo renvoyant à l'accueil -->
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
	<div class="headerContainer"> 
		<a href="accueilServlet"><img src="<%=request.getContextPath() %>/ressources/LOSNA3.png" alt="Logo"></a>
		<div class="headerTextContainer">
			<div class="headerTextCredit">
			<p>Vous avez ${userCo.credit} crédits !</p>
			</div>
		    <!-- menu -->
		    <div class="headerTextMenu">
			    <nav>           
				<ul class="ulItem">
				<%if (session.getAttribute("userCo") == null) { %>
					<li><a href="connexionServlet">Connexion/Inscription</a></li>
				<%} else {%>
					<c:if test="${userCo.administrateur}">
		    		<li><a href="GestionCategorieAdmin">Gestion<span class="hidden-text"> des catégories</span></a></li>
					</c:if>
		
					<li><a href="ajoutArticleServlet">Vendre<span class="hidden-text"> un Article</span></a></li>
					<li><a href="afficherProfilServlet"><span class="hidden-text">Mon </span>Profil</a></li>
					<li><a href="achatCreditServlet"><span class="hidden-text">Acheter des </span>Crédits</a></li>
					
					<li>
					<form id="deconnexionForm" action="deconnexionServlet" method="post" style="display: none;">	  
			    		<button type="submit">Déconnexion</button>
					</form>
					<a href="#" onclick="document.getElementById('deconnexionForm').submit();">Déconnexion</a>	
					</li>
				<%} %>
				</ul>
				</nav>
			</div>
		</div>
	</div>
</div>