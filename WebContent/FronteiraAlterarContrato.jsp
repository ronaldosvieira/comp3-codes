<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Contrato</title>
</head>
<body>

<form method="post" action="./alterar">
	<h2>Alterar Contrato</h2>
	<input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
	
	<input type="number" name="comissao" step=0.01 placeholder="Comissão" 
		value="<%= request.getAttribute("comissao") %>">
	<br>
	<button type="submit">Enviar</button>
	<a href="ler">Voltar</a>
</form>

</body>
</html>