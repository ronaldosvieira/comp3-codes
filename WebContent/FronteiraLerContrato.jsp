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
<%@ page import = "java.util.List" %>

<% List<Contrato> contratos = 
	(List<Contrato>) request.getAttribute("contratos"); %>

<table>
	<thead>
		<tr>
			<th>Id</th>
			<th>Comissão</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
	<% for (Contrato contrato : contratos) { %>
		<tr>
			<td><%= contrato.obterId() %></td>
			<td><%= contrato.obterComissao() %></td>
			<td>
				<a href="alterar?id=<%= contrato.obterId() %>">
					Editar
				</a>&nbsp; 
				<a href="remover?id=<%= contrato.obterId() %>">
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