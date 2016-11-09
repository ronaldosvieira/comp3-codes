<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Item</title>
</head>
<body>

<%@ page import = "entidades.ItemVenda" %>

<% ItemVenda itemVenda = (ItemVenda) request.getAttribute("itemVenda"); %>

<form method="post" action="./alterar">
	<h2>Alterar Item</h2>
	<input type="hidden" name="id" value="<%= itemVenda.obterId() %>">
	
	<input type="number" name="quantidade" placeholder="Quantidade" 
		value="<%= itemVenda.obterQuantidade() %>">
	<br>
	<select name="mobilia_id" disabled>
		<option value="<%= itemVenda.obterMobilia().obterId() %>">
			<%= itemVenda.obterMobilia().obterDescricao() %>
		</option>
	</select>
	<br><br>
	
	<button type="submit">Enviar</button>
	<a href="javascript:history.go(-1)">Voltar</a>
</form>

</body>
</html>