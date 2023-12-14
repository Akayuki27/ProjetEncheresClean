  <!-- Logo renvoyant � l'accueil -->
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
	<div class="headerContainer"> 
		<a href="accueilServlet"><img src="<%=request.getContextPath() %>/ressources/LOSNA3.png" alt="Logo"></a> <!-- Logo renvoyant � l'accueil -->
	    <!-- menu -->
	    <nav>           
		<ul class="ulItem">
		<%if (session.getAttribute("userCo") == null) { %>
			<li><a href="connexionServlet">Connexion/Inscription</a></li>
		<%} else {%>
		<c:if test="${userCo.administrateur}">
    		<li><a href="GestionCategorieAdmin">Gestion des cat�gories</a></li>
		</c:if>

			<li><a href="ajoutArticleServlet">Vendre un Article</a></li>
			<li><a href="afficherProfilServlet">Mon Profil</a></li>
			<li>
				<!-- 
				<form id="deconnexionForm" action="deconnexionServlet" method="post">
				<button type="submit">D�connexion</button>
				</form>
				-->
				<!-- sinon on peut tester ca pour plus avoir le boutton -->
	
				<form id="deconnexionForm" action="deconnexionServlet" method="post" style="display: none;">
	    		  
	    		<button type="submit">D�connexion</button>
				</form>
	
				<a href="#" onclick="document.getElementById('deconnexionForm').submit();">D�connexion</a>
	
				
			</li>
		<%} %>
		</ul>
		</nav>
	</div>
</div>