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
<%@ page import = "java.util.List" %>

<% List<Quarto> quartos = 
	(List<Quarto>) request.getAttribute("quartos"); %>

<table>
	<thead>
		<tr>
			<th>Id</th>
			<th>Descrição</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
	<% for (Quarto quarto : quartos) { %>
		<tr>
			<td><%= quarto.obterId() %></td>
			<td><%= quarto.obterDescricao() %></td>
			<td>
				<a href="alterar?id=<%= quarto.obterId() %>">
					Editar
				</a>&nbsp; 
				<a href="remover?id=<%= quarto.obterId() %>">
					Remover
				</a> 
			</td>
		</tr>
	<% } %>
	</tbody>
</table>

<a href="criar">Criar</a>

</body>
</html>