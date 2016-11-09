<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ler Item Venda</title>
</head>
<body>

<h2>Itens do Ambiente <%= request.getAttribute("ambiente_id") %></h2>

<%@ page import = "entidades.ItemVenda" %>
<%@ page import = "java.util.List" %>

<% List<ItemVenda> itemVendas = 
	(List<ItemVenda>) request.getAttribute("itemVendas"); %>

<table>
	<thead>
		<tr>
			<th>Id</th>
			<th>Mob�lia</th>
			<th>Quantidade</th>
			<th>A��es</th>
		</tr>
	</thead>
	<tbody>
	<% for (ItemVenda itemVenda : itemVendas) { %>
		<tr>
			<td><%= itemVenda.obterId() %></td>
			<td><%= itemVenda.obterMobilia().obterDescricao() %></td>
			<td><%= itemVenda.obterQuantidade() %></td>
			<td>
				<a href="alterar?id=<%= itemVenda.obterId() %>">
					Editar
				</a>
			</td>
		</tr>
	<% } %>
	</tbody>
</table>

<a href="javascript:history.go(-1)">Voltar</a>
<a href="criar?ambiente_id=<%= request.getAttribute("ambiente_id") %>">Criar</a>

</body>
</html>