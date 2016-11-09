<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ler Ambiente</title>
</head>
<body>

<%@ page import = "entidades.Ambiente" %>
<%@ page import = "entidades.ItemVenda" %>
<%@ page import = "java.util.List" %>

<% List<Ambiente> ambientes = 
	(List<Ambiente>) request.getAttribute("ambientes"); %>
<% int contratoId = (Integer) request.getAttribute("contrato_id"); %>

<h2>Ambientes do Contrato <%= contratoId %></h2>

<table>
	<thead>
		<tr>
			<th>Id</th>
			<th>Número de paredes</th>
			<th>Número de portas</th>
			<th>Metragem</th>
			<th>Valor (R$)</th>
			<th>Prazo de entrega (semanas)</th>
			<th>Itens</th>
			<th>Ações</th>
		</tr>
	</thead>
	<tbody>
	<% for (Ambiente ambiente : ambientes) { %>
		<tr>
			<td><%= ambiente.obterId() %></td>
			<td><%= ambiente.obterNumParedes() %></td>
			<td><%= ambiente.obterNumPortas() %></td>
			<td><%= ambiente.obterMetragem() %></td>
			<td><%= ambiente.custo() %></td>
			<td><%= ambiente.tempoEntrega() %></td>
			<td>
				<% if (ambiente.obterItens().size() > 0) { %>
					<% for (ItemVenda item : ambiente.obterItens()) { %>
						<%= item.obterMobilia().obterDescricao() %> 
						(<%= item.obterQuantidade() %>)<br>
					<% } %>
				<% } else %> Nenhum
			</td>
			<td>
				<a href="item/ler?ambiente_id=<%= ambiente.obterId() %>">
					Itens
				</a>&nbsp;
				<a href="alterar?id=<%= ambiente.obterId() %>">
					Editar
				</a>&nbsp; 
				<a href="remover?id=<%= ambiente.obterId() %>">
					Remover
				</a> 
			</td>
		</tr>
	<% } %>
	</tbody>
</table>

<a href="../ler">Voltar</a>
<a href="criar?contrato_id=<%= contratoId %>">Criar</a>

</body>
</html>