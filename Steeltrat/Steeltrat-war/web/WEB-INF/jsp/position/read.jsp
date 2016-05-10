<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Cargo - Visualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Cargos&nbsp;&#45;&nbsp;Opera��es</h3>
        <table style="width:100%">
            <tr>
                <td>ID</td>
                <td>NOME</td> 
            </tr>
            <c:forEach items="${positions}" var="position" varStatus="i">
                <tr>
                    <td>${position.idPosition}</td>
                    <td>${position.namePosition}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
