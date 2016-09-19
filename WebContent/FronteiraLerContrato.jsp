<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ler Contrato</title>
</head>
<body>

<h2>Ler Contrato</h2>

<%@ page import = "entidades.Contrato" %>
<% Contrato contrato = (Contrato) request.getAttribute("contrato"); %>

<table>
	<tr>
		<th align="right">Id</th>
		<td><%= request.getAttribute("id") %></td>
	</tr>
	<tr>
		<th align="right">Comissão</th>
		<td><%= contrato.obterComissao() %></td>
	</tr>
</table>

</body>
</html>