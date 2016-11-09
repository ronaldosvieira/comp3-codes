<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Remover Ambiente</title>
</head>
<body>

<form method="post" action="./remover">
	<h2>Remover Ambiente do Contrato <%= request.getAttribute("contrato_id") %></h2>
	<input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
	<input type="hidden" name="contrato_id" value="<%= request.getAttribute("contrato_id") %>">
	
	<p>Deseja remover o ambiente <%= request.getAttribute("id") %>?
	<br>
	<button type="submit">Sim</button>
	<a href="./ler?contrato_id=<%= request.getAttribute("contrato_id") %>">Não</a>
</form>
</body>
</html>