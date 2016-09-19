<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ler Ambiente</title>
</head>
<body>

<h2>Ler Ambiente</h2>

<%@ page import = "entidades.Ambiente" %>
<% Ambiente ambiente = (Ambiente) request.getAttribute("ambiente"); %>

<table>
	<tr>
		<th align="right">Id</th>
		<td><%= request.getAttribute("id") %></td>
	</tr>
	<tr>
		<th align="right">Número de paredes</th>
		<td><%= ambiente.obterNumParedes() %></td>
	</tr>
	<tr>
		<th align="right">Número de portas</th>
		<td><%= ambiente.obterNumPortas() %></td>
	</tr>
	<tr>
		<th align="right">Metragem</th>
		<td><%= ambiente.obterMetragem() %></td>
	</tr>
</table>

</body>
</html>