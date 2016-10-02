<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ler Cozinha</title>
</head>
<body>

<h2>Ler Cozinha</h2>

<%@ page import = "entidades.Cozinha" %>
<%@ page import = "java.util.List" %>

<% List<Cozinha> cozinhas = 
	(List<Cozinha>) request.getAttribute("cozinhas"); %>

<table>
	<thead>
		<tr>
			<th>Id</th>
			<th>Descrição</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
	<% for (Cozinha cozinha : cozinhas) { %>
		<tr>
			<td><%= cozinha.obterId() %></td>
			<td><%= cozinha.obterDescricao() %></td>
			<td>
				<a href="alterar?id=<%= cozinha.obterId() %>">
					Editar
				</a>&nbsp; 
				<a href="remover?id=<%= cozinha.obterId() %>">
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