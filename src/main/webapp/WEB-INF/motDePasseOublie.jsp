<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mot de passe oublié</title>
<link rel="stylesheet" href="/ProjetEncheresClean/static/style.css">
</head>
<body>
<jsp:include page="/WEB-INF/Header.jsp">
<jsp:param value="" name="header"/>
</jsp:include>
<form id="resetMotDePasse" method="post" action="modificationMotDePasse">

	<input type="hidden" id="id" name="id" value="${idUser}">
	
	<label for="motDePasse">Nouveau mot de passe:</label>
    <input type="password" id="motDePasse" name="motDePasse" required>
    
    <label for="confirmation">Confirmation:</label>
    <input type="password" id="confirmation" name="confirmation" required>
    <button type="submit">Confirmer mon nouveau mot de passe</button>
</form>
</body>
</html>