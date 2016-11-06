<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Comodo Composto</title>
</head>
<body>

<%@ page import = "java.util.List" %>
<%@ page import = "entidades.ComodoComposto" %>
<%@ page import = "entidades.Comodo" %>

<% ComodoComposto comodoComposto = 
	(ComodoComposto) request.getAttribute("comodoComposto"); %>
<% List<Comodo> comodos = 
	(List<Comodo>) request.getAttribute("comodos"); %>

<form method="post" action="./alterar">
	<h2>Alterar Comodo Composto</h2>
	
	<input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
	
	<input type="text" name="descricao" placeholder="Descrição" 
		value="<%= comodoComposto.obterDescricao() %>">
	<br>
	
	<label for="comodos">Comodos</label>
	<select name="comodos" multiple required>
		<% for (Comodo comodo : comodos) { %>
			<option value="<%= comodo.obterId() %>" 
			<% if (comodoComposto.obterComodos().contains(comodo)) { %> selected <% } %>>
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