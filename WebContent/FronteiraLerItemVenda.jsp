<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ler Item Venda</title>
</head>
<body>

<h2>Ler Item Venda</h2>

<%@ page import = "entidades.ItemVenda" %>
<%@ page import = "java.util.List" %>

<% List<ItemVenda> itemVendas = 
	(List<ItemVenda>) request.getAttribute("itemVendas"); %>

<table>
	<thead>
		<tr>
			<th>Id</th>
			<th>Mobília</th>
			<th>Quantidade</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
	<% for (ItemVenda itemVenda : itemVendas) { %>
		<tr>
			<td><%= itemVenda.obterId() %></td>
			<td><%= itemVenda.obterMobilia() %></td>
			<td><%= itemVenda.obterQuantidade() %></td>
			<td>
				<a href="alterar?id=<%= itemVenda.obterId() %>">
					Editar
				</a>&nbsp; 
				<a href="remover?id=<%= itemVenda.obterId() %>">
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