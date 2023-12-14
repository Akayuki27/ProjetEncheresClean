<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Acheter des crédits</title>
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
	

	<h1>Achat de crédit </h1>
	<div class="FormContainer">
		<div class="creditContainer">			
			<form id="achatCredit" action="achatCreditServlet" method="post">
				<div class="creditEntry">
				<label for="creditAchat">Veuillez insérer le nombre de crédits que vous souhaitez acheter</label>
				<input type="number" id="creditAchat" name="creditAchat" value="10" min="1" step="1">
				</div>
				<div class="creditBoutton">
				<button type="submit">Acheter</button>
				</div>
			</form>	
		</div>
	</div>
	
</body>
</html>