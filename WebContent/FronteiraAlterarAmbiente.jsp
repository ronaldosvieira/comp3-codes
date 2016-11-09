<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alterar Ambiente</title>
</head>
<body>

<%@ page import = "entidades.Ambiente" %>

<% Ambiente ambiente = 
	(Ambiente) request.getAttribute("ambiente"); %>

<form method="post" action="./alterar">
	<h2>Alterar Ambiente</h2>
	
	<input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
	<input type="hidden" name="contrato_id" value="<%= ambiente.obterContratoId() %>">
	
	<input type="number" name="numParedes" placeholder="Núm. de paredes" 
		value="<%= ambiente.obterNumParedes() %>">
		<br>
	<input type="number" name="numPortas" placeholder="Núm. de portas" 
		value="<%= ambiente.obterNumPortas() %>">
		<br>
	<input type="number" name="metragem" step=0.01 placeholder="Metragem"
		value="<%= ambiente.obterMetragem() %>">
	<br>
	<button type="submit">Enviar</button>
	<a href="ler?contrato_id=<%= ambiente.obterContratoId() %>">Voltar</a>
</form>

</body>
</html>