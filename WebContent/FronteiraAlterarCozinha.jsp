<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Cozinha</title>
</head>
<body>

<%@ page import = "entidades.Cozinha" %>

<% Cozinha cozinha = 
	(Cozinha) request.getAttribute("cozinha"); %>

<form method="post" action="./alterar">
	<h2>Alterar Cozinha</h2>
	
	<input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
	
	<input type="text" name="descricao" placeholder="Descrição" 
		value="<%= cozinha.obterDescricao() %>">
	<br>
	<button type="submit">Enviar</button>
</form>

</body>
</html>