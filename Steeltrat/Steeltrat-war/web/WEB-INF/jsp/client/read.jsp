<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Cliente - Visualizar</title>
        <link rel="icon" href="../../../img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Clientes&nbsp;&#45;&nbsp;Opera��es</h3>
        <div id="returnMsgSuccessfully">${returnMsgSuccessfully}</div>
        ${returnMsgSuccessfully=null}
        <div id="returnMsgError">${returnMsgError}</div>
        ${returnMsgError=null}
        <table style="width:100%">
            <tr>
                <td>ID</td>
                <td>NOME</td> 
                <td>TELEFONE</td>
                <td>C.E.P. / NR</td>
            </tr>
            <c:forEach items="${clients}" var="client" varStatus="i">
                <tr>
                    <td>${client.idClient}</td>
                    <td>${client.nameClient}</td>
                    <td>${client.telephone}</td>
                    <td>${client.idAddress.zipcode} / ${client.idAddress.numberAddress}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
