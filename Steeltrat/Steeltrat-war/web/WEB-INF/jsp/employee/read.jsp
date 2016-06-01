<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Funcionário - Visualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Funcionários&nbsp;&#45;&nbsp;Tabela</h3>
        <table class="rwd-table">
            <tr>
                <th>ID</th>
                <th>NOME</th> 
                <th>USUÁRIO</th>
                <th>DEPARTAMENTO</th>
                <th>CARGO</th>
                <th>C.P.F.</th>
            </tr>
            <c:forEach items="${employees}" var="employee" varStatus="i">
                <tr>
                    <td data-th="ID">${employee.idEmployee}</td>
                    <td data-th="NOME">${employee.nameEmployee}</td>
                    <td data-th="USUÁRIO">${employee.idUser.username}</td>
                    <td data-th="DEPARTAMENTO">${employee.idDepartament.nameDepartament}</td>
                    <td data-th="CARGO">${employee.idPosition.namePosition}</td>
                    <td data-th="C.P.F.">${employee.cpf.substring(0,3)}.${employee.cpf.substring(3,6)}.${employee.cpf.substring(6,9)}-${employee.cpf.substring(9,11)}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
