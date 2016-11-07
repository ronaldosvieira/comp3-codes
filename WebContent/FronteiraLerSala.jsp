<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ler Sala</title>
</head>
<body>

<h2>Ler Sala</h2>

<%@ page import = "entidades.Sala" %>
<%@ page import = "java.util.List" %>

<% List<Sala> salas = 
	(List<Sala>) request.getAttribute("salas"); %>

<table>
	<thead>
		<tr>
			<th>Id</th>
			<th>Descrição</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
	<% for (Sala sala : salas) { %>
		<tr>
			<td><%= sala.obterId() %></td>
			<td><%= sala.obterDescricao() %></td>
			<td>
				<a href="alterar?id=<%= sala.obterId() %>">
					Editar
				</a>&nbsp; 
				<a href="remover?id=<%= sala.obterId() %>">
					Remover
				</a> 
			</td>
		</tr>
	<% } %>
	</tbody>
</table>

<a href="..">Voltar</a>
<a href="criar">Criar</a>

</body>
</html>