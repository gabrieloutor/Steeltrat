<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Item Recebimento - Visualizar</title>
        <link rel="icon" href="../../../img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Item Recebimento&nbsp;&#45;&nbsp;Operações</h3>
        <div id="returnMsgSuccessfully">${returnMsgSuccessfully}</div>
        ${returnMsgSuccessfully=null}
        <div id="returnMsgError">${returnMsgError}</div>
        ${returnMsgError=null}
        <table style="width:100%">
            <tr>
                <td>ID</td>
                <td>NR RECEBIMENTO</td>
                <td>ITEM</td>
                <td>CLIENTE</td>
                <td>NF CLIENTE</td>
                <td>PEDIDO CLIENTE</td>
                <td>QTD PECA</td>
                <td>QTD CP</td>
                <td>PESO</td>
                <td>CORRIDA</td>
                <td>PRODUTO</td>
                <td>MATERIAL</td>
                <td>NORMA</td>
                <td>FUNCIONÁRIO</td>
                <td>OBS</td>
                <td>DATA</td>
            </tr>

            <c:forEach items="${itemReceipts}" var="itemReceipt" varStatus="i">
                <tr>
                    <td>${itemReceipt.idItemReceipt}</td>
                    <td>${itemReceipt.idReceipt.idReceipt}</td>
                    <c:if test="${i.index == 0}">
                        <c:set var="id" value="${itemReceipt.idReceipt.idReceipt}"/>
                        <c:set var="contador" value="0"/>
                    </c:if>
                    <c:if test="${itemReceipt.idReceipt.idReceipt == id}">
                        <c:set var="contador" value="${contador + 1}"/>
                        <td><c:out value="${contador}"/></td>
                    </c:if>
                    <c:if test="${itemReceipt.idReceipt.idReceipt ne id}">
                        <c:set var="contador" value="1"/>
                        <td><c:out value="${contador}"/></td>
                    </c:if>
                    <c:set var="id" value="${itemReceipt.idReceipt.idReceipt}"/>
                    <td>${itemReceipt.idReceipt.idClient.nameClient}</td>
                    <td>${itemReceipt.nfClient}</td>
                    <td>${itemReceipt.orderClient}</td>
                    <td>${itemReceipt.amountPiece}</td>
                    <td>${itemReceipt.amountSpecimen}</td>
                    <td>${itemReceipt.weight}</td>
                    <td>${itemReceipt.numberTransport}</td>
                    <td>${itemReceipt.idProduct.descriptionProduct}</td>
                    <td>${itemReceipt.idMaterial.nameMaterial}</td>
                    <td>${itemReceipt.idStandard.nameStandard}</td>
                    <td>${itemReceipt.idEmployee.nameEmployee}</td>
                    <td>${itemReceipt.markItemReceipt}</td>
                    <td>${itemReceipt.getFormattedDate()}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
