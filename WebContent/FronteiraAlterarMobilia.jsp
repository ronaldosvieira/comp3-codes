<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Mobília</title>
</head>
<body>

<%@ page import = "java.util.List" %>
<%@ page import = "entidades.Comodo" %>
<%@ page import = "entidades.Mobilia" %>

<% Mobilia mobilia = 
	(Mobilia) request.getAttribute("mobilia"); %>
<% List<Comodo> comodos = 
	(List<Comodo>) request.getAttribute("comodos"); %>
<% List<Comodo> comodosMobilia = 
	(List<Comodo>) request.getAttribute("comodosMobilia"); %>
	
<form method="post" action="./alterar">
	<h2>Alterar Mobília</h2>
	<input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
	
	<input type="text" name="descricao" placeholder="Descrição" 
		value="<%= mobilia.obterDescricao() %>">
	<br>
	<input type="number" name="custo" step=0.01 placeholder="Custo (R$)" 
		value="<%= mobilia.obterCusto() %>">
	<br>
	<input type="number" name="tempoEntrega" placeholder="Tempo de Entrega (semanas)" 
		value="<%= mobilia.obterTempoEntrega() %>">
	<br>
	
	<label for="comodos">Comodos</label>
	<select name="comodos" multiple>
		<% for (Comodo comodo : comodos) { %>
			<option value="<%= comodo.obterId() %>"
				<% if (comodosMobilia.contains(comodo)) { %> selected <% } %>>
				<%= comodo.obterDescricao() %>
			</option>
		<% } %>
	</select>
	<br><br>
	
	<button type="submit">Enviar</button>
	<a href="ler">Voltar</a>
</form>

</body>
</html>