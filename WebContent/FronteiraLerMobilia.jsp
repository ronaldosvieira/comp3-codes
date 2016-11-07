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
<%@ page import = "java.util.List" %>

<% List<Mobilia> mobilias = 
	(List<Mobilia>) request.getAttribute("mobilias"); %>

<table>
	<thead>
		<tr>
			<th>Id</th>
			<th>Descrição</th>
			<th>Custo (R$)</th>
			<th>Tempo de entrega (semanas)</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
	<% for (Mobilia mobilia : mobilias) { %>
		<tr>
			<td><%= mobilia.obterId() %></td>
			<td><%= mobilia.obterDescricao() %></td>
			<td><%= mobilia.obterCusto() %></td>
			<td><%= mobilia.obterTempoEntrega() %></td>
			<td>
				<a href="alterar?id=<%= mobilia.obterId() %>">
					Editar
				</a>&nbsp; 
				<a href="remover?id=<%= mobilia.obterId() %>">
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