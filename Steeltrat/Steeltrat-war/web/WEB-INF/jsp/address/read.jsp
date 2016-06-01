<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Endereço - Visualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Endereços&nbsp;&#45;&nbsp;Tabela</h3>
        <table class="rwd-table">
            <tr>
                <th>ID</th>
                <th>ZIPCODE</th> 
                <th>NÚMERO</th>
            </tr>
            <c:forEach items="${addresses}" var="address" varStatus="i">
                <tr>
                    <td data-th="ID">${address.idAddress}</td>
                    <td data-th="ZIPCODE">${address.getFormattedZipcode()}</td>
                    <td data-th="NÚMERO">${address.numberAddress}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
