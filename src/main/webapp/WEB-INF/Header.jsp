  <!-- Logo renvoyant à l'accueil -->
    <div>  
            <a href="accueil.jsp"><img src="<%=request.getContextPath() %>/ressources/LOSNA_lowres.png" alt="Logo"></a> <!-- Logo renvoyant à l'accueil -->
    </div>

    <!-- menu -->
    <div>
        <nav>           
            <ul>
              <li><a href="connexionServlet">Connexion/Inscription</a></li>
              <li><a href="/WEB-INF/vendre.html">Vendre un Article</a></li>
              <li><a href="afficherProfilServlet">Mon Profil</a></li>
              <li>
                <form id="deconnexionForm" action="deconnexionServlet" method="post">
                  <button type="submit">Déconnexion</button>
                </form>
              </li>
            </ul>
        </nav>
    </div>
