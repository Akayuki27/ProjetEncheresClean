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
<form>

	<input type="hidden" id="id" name="id" value="${u.noUtilisateur }">
	
	<label for="motDePasse">Nouveau mot de passe:</label>
    <input type="password" id="motDePasse" name="motDePasse" >
    
    <label for="confirmation">Confirmation:</label>
    <input type="password" id="confirmation" name="confirmation" >
</form>
</body>
</html>