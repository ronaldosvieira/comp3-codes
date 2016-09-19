<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ler Mobília</title>
</head>
<body>

<h2>Ler Mobília</h2>

<%@ page import = "entidades.Mobilia" %>
<% Mobilia mobilia = (Mobilia) request.getAttribute("mobilia"); %>

<table>
	<tr>
		<th align="right">Id</th>
		<td><%= request.getAttribute("id") %></td>
	</tr>
	<tr>
		<th align="right">Descrição</th>
		<td><%= mobilia.obterDescricao() %></td>
	</tr>
	<tr>
		<th align="right">Custo (R$)</th>
		<td><%= mobilia.obterCusto() %></td>
	</tr>
	<tr>
		<th align="right">Tempo de entrega (em dias)</th>
		<td><%= mobilia.obterTempoEntrega() %></td>
	</tr>
</table>

</body>
</html>