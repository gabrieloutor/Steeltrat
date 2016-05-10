<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Material - Visualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Materiais&nbsp;&#45;&nbsp;Operações</h3>
        <table style="width:100%">
            <tr>
                <td>ID</td>
                <td>NOME</td> 
                <td>OBSERVAÇÃO</td>
            </tr>
            <c:forEach items="${materials}" var="material" varStatus="i">
                <tr>
                    <td>${material.idMaterial}</td>
                    <td>${material.nameMaterial}</td>
                    <td>${material.markMaterial}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
