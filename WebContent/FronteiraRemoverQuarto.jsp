<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Remover Quarto</title>
</head>
<body>

<form method="post" action="./remover">
	<h2>Remover Quarto</h2>
	<input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
	
	<p>Deseja remover o quarto <%= request.getAttribute("id") %>?
	<br>
	<button type="submit">Sim</button>
	<a href="./ler">Não</a>
</form>
</body>
</html>