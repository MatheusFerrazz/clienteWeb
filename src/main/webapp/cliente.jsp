<%@page import="java.util.List"%>
<%@page import="br.com.gcti.model.*"%>
<%@page import="br.com.gcti.controller.*"%>
<%@page import="br.com.gcti.services.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tela de Cliente</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<script type="text/javascript">
	function confirmar(pi) {
		if (window.confirm("Tem certeza que deseja excluir?")) {
			location.href = "cliente?acao=exc&i=" + pi;
		}
	}
</script>

</head>
<body>
	<h2 align="center">Tela de cliente</h2>
	<div align="center">${requestScope.msg}</div>

	<form method="post" action="cliente">
		<div align="center">
			<input type="hidden" name="i" value="${requestScope.iCli}">
			E-mail: <input type="text" value="${requestScope.cli.email}"
				name="email" /> <input type="submit" value="Save">
		</div>
	</form>
	<table class="table" border="1">
		<br />
		<thead class="thead-light">
			<tr>
				<th scope="col">Email</th>
				<th scope="col">Ação</th>
				<th scope="col">Opções</th>
			</tr>
		</thead>
		<c:set var="i" value="0" />
		<c:forEach items="${requestScope.lista}" var="c">
			<tr>
				<th scope="row">${i}</th>
				<td>${c.email}</td>
				<td><a href="javascript:confirmar(${i})"> excluir</a> | <a
					href="cliente?i=${i}&acao=edit"> editar</a></td>
			<tr>
				<c:set var="i" value="${i+1}" />
		</c:forEach>
	</table>

</body>
</html>