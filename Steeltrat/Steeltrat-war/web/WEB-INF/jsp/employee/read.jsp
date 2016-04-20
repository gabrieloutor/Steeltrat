<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Funcionário - Visualizar</title>
        <link rel="icon" href="../../../img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Funcionários&nbsp;&#45;&nbsp;Operações</h3>
        <div id="returnMsgSuccessfully">${returnMsgSuccessfully}</div>
        ${returnMsgSuccessfully=null}
        <div id="returnMsgError">${returnMsgError}</div>
        ${returnMsgError=null}
        <table style="width:100%">
            <tr>
                <td>ID</td>
                <td>NOME</td> 
                <td>USUARIO</td>
                <td>DEPARTAMENTO</td>
                <td>CARGO</td>
                <td>CPF</td>
            </tr>
            <c:forEach items="${employees}" var="employee" varStatus="i">
                <tr>
                    <td>${employee.idEmployee}</td>
                    <td>${employee.nameEmployee}</td>
                    <td>${employee.idUser.username}</td>
                    <td>${employee.idDepartament.nameDepartament}</td>
                    <td>${employee.idPosition.namePosition}</td>
                    <td>${employee.cpf}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
