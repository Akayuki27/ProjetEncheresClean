  <!-- Logo renvoyant � l'accueil -->
    <div>  
            <a href="accueil.html"><img src="<%=request.getContextPath() %>/ressources/LOSNA2.jpg" alt="Logo"></a> <!-- Logo renvoyant � l'accueil -->
    </div>

    <!-- menu -->
    <div>
        <nav>           
            <ul>
              <li><a href="/WEB-INF/encheres.html">Ench�res</a></li>
              <li><a href="/WEB-INF/vendre.html">Vendre un Article</a></li>
              <li><a href="afficherProfilServlet">Mon Profil</a></li>
              <li>
                <form id="deconnexionForm" action="deconnexionServlet" method="post">
                  <button type="submit">D�connexion</button>
                </form>
              </li>
            </ul>
        </nav>
    </div>
