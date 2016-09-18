<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Criar Ambiente</title>
</head>
<body>

<form method="get" action="./ambiente/guardar">
	<h2>Criar Ambiente</h2>
	<input type="number" name="numParedes" placeholder="Núm. de paredes"><br>
	<input type="number" name="numPortas" placeholder="Núm. de portas"><br>
	<input type="number" name="metragem" step=0.01 placeholder="Núm. de paredes"><br>
	<button type="submit">Enviar</button>
</form>

</body>
</html>