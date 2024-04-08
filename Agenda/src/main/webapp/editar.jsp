<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editor de Contatos</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<h1 class="text-primary">Editar Contato</h1>
	<div class="form-group">
		<form name="frmContato" action="update">
			<table>
				<tr>
					<td><input type="text" class="caixa1" name="idcon" readonly value="<%out.print(request.getAttribute("Idcon"));%>"></td>
				</tr>

				<tr>
					<td><input type="text" class="caixa2 form-control" name="nome" value="<%out.print(request.getAttribute("Nome"));%>"></td>
				</tr>

				<tr>
					<td><input type="text" class="caixa3 form-control" name="fone" value="<%out.print(request.getAttribute("Fone"));%>"></td>
				</tr>

				<tr>
					<td><input type="text" class="caixa4 form-control" name="email"  value="<%out.print(request.getAttribute("Email"));%>"></td>
				</tr>
			</table>
			<input type="submit" value="Salvar" class="btn1"
				onclick="validar()">
		</form>
	</div>
	<script type="text/javascript" src="scripts/validacao.js"></script>
</body>
</html>