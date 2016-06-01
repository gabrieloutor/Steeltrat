<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Produto - Visualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Produtos&nbsp;&#45;&nbsp;Tabela</h3>
        <table class="rwd-table">
            <tr>
                <th>ID</th>
                <th>NOME</th> 
                <th>PREÇO</th>
            </tr>
            <c:forEach items="${products}" var="product" varStatus="i">
                <tr>
                    <td data-th="ID">${product.idProduct}</td>
                    <td data-th="NOME">${product.descriptionProduct}</td>
                    <td data-th="PREÇO">R$&nbsp;&nbsp;&nbsp;${product.price}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
