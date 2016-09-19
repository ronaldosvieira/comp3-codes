<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sala</title>
</head>
<body>

<h2>Ler Sala</h2>

<%@ page import = "entidades.Sala" %>
<% Sala sala = (Sala) request.getAttribute("sala"); %>

<table>
	<tr>
		<th align="right">Id</th>
		<td><%= request.getAttribute("id") %></td>
	</tr>
	<tr>
		<th align="right">Descrição</th>
		<td><%= sala.obterDescricao() %></td>
	</tr>
</table>

</body>
</html>