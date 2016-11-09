<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Criar Item</title>
</head>
<body>

<%@ page import = "entidades.Comodo" %>
<%@ page import = "entidades.Mobilia" %>
<%@ page import = "java.util.List" %>

<% List<Comodo> comodos =
	(List<Comodo>) request.getAttribute("comodos"); %>

<form method="post" action="./criar">
	<h2>Criar Item</h2>
	<input type="number" name="quantidade" placeholder="Quantidade" required><br>
	<input type="hidden" name="ambiente_id" value="<%= request.getAttribute("ambiente_id") %>">
	
	<select name="mobilia_id" required>
		<% for (Comodo comodo : comodos) { %> 
			<% if (comodo.listaMobiliaDisponivel().size() > 0) { %>
			<optgroup label="<%= comodo.obterDescricao() %>">
				<% for (Mobilia mobilia : comodo.listaMobiliaDisponivel()) { %>
					<option value="<%= mobilia.obterId() %>">
						<%= mobilia.obterDescricao()%> - R$ <%= mobilia.obterCusto() %>
					</option>
				<% } %>	
			</optgroup>
			<% } %>
		<% } %>
	</select>
	<br><br>
	
	<button type="submit">Enviar</button>
</form>

</body>
</html>