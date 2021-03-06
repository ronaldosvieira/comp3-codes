<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Criar Comodo Composto</title>
</head>
<body>

<%@ page import = "java.util.List" %>
<%@ page import = "entidades.Comodo" %>

<% List<Comodo> comodos = 
	(List<Comodo>) request.getAttribute("comodos"); %>

<form method="post" action="./criar">
	<h2>Criar Comodo Composto</h2>
	<input type="text" name="descricao" placeholder="Descrição">
	
	<br>
	
	<label for="comodos">Comodos</label>
	<select name="comodos" multiple required>
		<% for (Comodo comodo : comodos) { %>
			<option value="<%= comodo.obterId() %>">
				<%= comodo.obterDescricao() %>
			</option>
		<% } %>
	</select>
	<br><br>
	
	<button type="submit">Enviar</button>
</form>

</body>
</html>