<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Usuário - Visualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Usuários&nbsp;&#45;&nbsp;Tabela</h3>
        <table class="rwd-table">
            <tr>
                <th>ID</th>
                <th>USERNAME</th> 
                <th>SENHA</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td data-th="ID">${user.idUser}</td>
                    <td data-th="USERNAME">${user.username}</td>
                    <td data-th="SENHA">${user.password}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
