<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Criar Mobília</title>
</head>
<body>

<form method="get" action="./mobilia/guardar">
	<h2>Criar Mobília</h2>
	<input type="text" name="descricao" placeholder="Descrição"><br>
	<input type="number" name="descricao" step=0.01 placeholder="Custo (R$)"><br>
	<input type="number" name="descricao" placeholder="Tempo de Entrega (dias)"><br>
	<button type="submit">Enviar</button>
</form>

</body>
</html>