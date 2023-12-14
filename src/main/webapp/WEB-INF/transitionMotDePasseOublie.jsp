<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mot de passe oublié ?</title>
<link rel="stylesheet" href="/ProjetEncheresClean/static/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/Header.jsp">
<jsp:param value="" name="header"/>
</jsp:include>

<form method="post" action="transitionMotDePasseServlet">
<label for="email">Veuillez rentrer votre adresse email pour pouvoir reset votre mot de passe: </label>
    <input type="text" id="email" name="email"  required>
    <button type="submit">Reset mon mot de passe</button>
</form>

</body>
</html>