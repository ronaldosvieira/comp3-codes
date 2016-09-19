<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ler Quarto</title>
</head>
<body>

<h2>Ler Quarto</h2>

<%@ page import = "entidades.Quarto" %>
<% Quarto quarto = (Quarto) request.getAttribute("quarto"); %>

<table>
	<tr>
		<th align="right">Id</th>
		<td><%= request.getAttribute("id") %></td>
	</tr>
	<tr>
		<th align="right">Descrição</th>
		<td><%= quarto.obterDescricao() %></td>
	</tr>
</table>

</body>
</html>