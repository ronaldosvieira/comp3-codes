<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Mobília</title>
</head>
<body>

<form method="post" action="./alterar">
	<h2>Alterar Mobília</h2>
	<input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
	
	<input type="text" name="descricao" placeholder="Descrição" 
		value="<%= request.getAttribute("descricao") %>">
	<br>
	<input type="number" name="custo" step=0.01 placeholder="Custo (R$)" 
		value="<%= request.getAttribute("custo") %>">
	<br>
	<input type="number" name="tempoEntrega" placeholder="Tempo de Entrega (dias)" 
		value="<%= request.getAttribute("tempoEntrega") %>">
	<br>
	<button type="submit">Enviar</button>
	<a href="ler">Voltar</a>
</form>

</body>
</html>