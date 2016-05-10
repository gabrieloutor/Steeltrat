<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Usuário - Visualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Usuários&nbsp;&#45;&nbsp;Operações</h3>
        <div class="returnMsgSuccessfully">${returnMsgSuccessfully}</div>
        ${returnMsgSuccessfully=null}
        <div id="returnMsgError">${returnMsgError}</div>
        ${returnMsgError=null}
        <table style="width:100%">
            <tr>
                <td>ID</td>
                <td>USERNAME</td> 
                <td>SENHA</td>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.idUser}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
