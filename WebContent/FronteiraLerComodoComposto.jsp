<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ler Comodo Composto</title>
</head>
<body>

<h2>Ler Comodo Composto</h2>

<%@ page import = "entidades.Comodo" %>
<%@ page import = "entidades.ComodoComposto" %>
<%@ page import = "java.util.List" %>

<% List<ComodoComposto> comodoCompostos = 
	(List<ComodoComposto>) request.getAttribute("comodoCompostos"); %>

<table>
	<thead>
		<tr>
			<th>Id</th>
			<th>Descrição</th>
			<th>Cômodos</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
	<% for (ComodoComposto comodoComposto : comodoCompostos) { %>
		<tr>
			<td><%= comodoComposto.obterId() %></td>
			<td><%= comodoComposto.obterDescricao() %></td>
			<td>
				<% for (Comodo comodo : comodoComposto.obterComodos()) { %>
					<%= comodo.obterDescricao() %> <br>
				<% } %>
			</td>
			<td>
				<a href="alterar?id=<%= comodoComposto.obterId() %>">
					Editar
				</a>&nbsp; 
				<a href="remover?id=<%= comodoComposto.obterId() %>">
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