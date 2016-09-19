<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Remover Mobília</title>
</head>
<body>

<form method="post" action="./remover">
	<h2>Remover Mobília</h2>
	<input type="hidden" name="id" value="<%= request.getAttribute("id") %>">
	
	<p>Deseja remover a mobília <%= request.getAttribute("id") %>?
	<br>
	<select name="remover">
		<option value="true">Sim</option>
		<option value="false">Não</option>
	</select>
	<br>
	<button type="submit">Enviar</button>
</form>

</body>
</html>