<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Recebimento - Visualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Recebimento&nbsp;&#45;&nbsp;Tabela</h3>
        <table class="rwd-table">
            <tr>
                <th>NR RECEBIMENTO</th>
                <th>CLIENTE</th>
                <th>ITENS</th>
            </tr>
            <c:forEach items="${receipts}" var="receipt" varStatus="i">
                <tr>
                    <td data-th="NR RECEBIMENTO">${receipt.idReceipt}</td>
                    <td data-th="CLIENTE">${receipt.idClient.nameClient}</td>
                    <td data-th="ITENS">
                        <c:forEach begin="1" end="${receipt.getItemsReceiptList().size()}" var="items">
                            ${items}
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
