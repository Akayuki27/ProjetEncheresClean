  <!-- Logo renvoyant à l'accueil -->
<div class="headerContainer"> 
	<a href="accueilServlet"><img src="<%=request.getContextPath() %>/ressources/LOSNA_lowres.png" alt="Logo"></a> <!-- Logo renvoyant à l'accueil -->
    <!-- menu -->
    <nav>           
	<ul class="navItem">
	<%if (session.getAttribute("userCo") == null) { %>
		<li><a href="connexionServlet">Connexion/Inscription</a></li>
	<%} else {%>
		<li><a href="ajoutArticleServlet">Vendre un Article</a></li>
		<li><a href="afficherProfilServlet">Mon Profil</a></li>
		<li>
			<form id="deconnexionForm" action="deconnexionServlet" method="post">
			<button type="submit">Déconnexion</button>
			</form>
			<!-- sinon on peut tester ca pour plus avoir le boutton -->
			<!--
			<form id="logoutForm" action="deconnexionServlet" method="post" style="display: none;">
    		  
    		<button type="submit">Déconnexion</button>
			</form>

			<a href="#" onclick="document.getElementById('logoutForm').submit();">Déconnexion</a>
			-->
			
		</li>
	<%} %>
	</ul>
	</nav>
</div>
