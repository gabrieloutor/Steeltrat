<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Recebimento - Visualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Recebimento&nbsp;&#45;&nbsp;Operações</h3>
        <div id="returnMsgSuccessfully">${returnMsgSuccessfully}</div>
        ${returnMsgSuccessfully=null}
        <div id="returnMsgError">${returnMsgError}</div>
        ${returnMsgError=null}
        <table style="width:100%">
            <tr>
                <td>NR RECEBIMENTO</td>
                <td>CLIENTE</td>
                <td>ITENS</td>
            </tr>
            <c:forEach items="${receipts}" var="receipt" varStatus="i">
                <tr>
                    <td>${receipt.idReceipt}</td>
                    <td>${receipt.idClient.nameClient}</td>
                    <td>
                        <c:forEach begin="1" end="${receipt.getItemsReceiptList().size()}" var="items">
                            ${items}
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
