<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Norma - Visualizar</title>
    <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Normas&nbsp;&#45;&nbsp;Tabela</h3>
        <table class="rwd-table">
            <tr>
                <th>ID</th>
                <th>NOME</th> 
                <th>OBSERVAÇÃO</th>
            </tr>
            <c:forEach items="${standards}" var="standard" varStatus="i">
                <tr>
                    <td data-th="ID">${standard.idStandard}</td>
                    <td data-th="NOME">${standard.nameStandard}</td>
                    <td data-th="OBSERVAÇÃO">${standard.markStandard}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
