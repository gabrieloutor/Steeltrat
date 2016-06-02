<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Item Recebimento - Visualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Item Recebimento&nbsp;&#45;&nbsp;Tabela</h3>
        <table class="rwd-table">
            <tr>
                <th>ID</th>
                <th>NR RECEBIMENTO</th>
                <th>ITEM</th>
                <th>CLIENTE</th>
                <th>NF CLIENTE</th>
                <th>PEDIDO CLIENTE</th>
                <th>QTD PECA</th>
                <th>QTD C.P.</th>
                <th>PESO</th>
                <th>CORRIDA</th>
                <th>PRODUTO</th>
                <th>MATERIAL</th>
                <th>NORMA</th>
                <th>RESPONSÁVEL</th>
                <th>OBSERVAÇÃO</th>
                <th>DATA</th>
            </tr>

            <c:forEach items="${itemReceipts}" var="itemReceipt" varStatus="i">
                <tr>
                    <td data-th="ID">${itemReceipt.idItemReceipt}</td>
                    <td data-th="NR RECEBIMENTO">${itemReceipt.idReceipt.idReceipt}</td>
                    <c:if test="${i.index == 0}">
                        <c:set var="id" value="${itemReceipt.idReceipt.idReceipt}"/>
                        <c:set var="contador" value="0"/>
                    </c:if>
                    <c:if test="${itemReceipt.idReceipt.idReceipt == id}">
                        <c:set var="contador" value="${contador + 1}"/>
                        <td data-th="ITEM"><c:out value="${contador}"/></td>
                    </c:if>
                    <c:if test="${itemReceipt.idReceipt.idReceipt ne id}">
                        <c:set var="contador" value="1"/>
                        <td data-th="ITEM"><c:out value="${contador}"/></td>
                    </c:if>
                    <c:set var="id" value="${itemReceipt.idReceipt.idReceipt}"/>
                    <td data-th="CLIENTE">${itemReceipt.idReceipt.idClient.nameClient}</td>
                    <td data-th="NF CLIENTE">${itemReceipt.nfClient}</td>
                    <td data-th="PEDIDO CLIENTE">${itemReceipt.orderClient}</td>
                    <td data-th="QTD PECA">${itemReceipt.getFormattedAmountPiece()}</td>
                    <td data-th="QTD C.P.">${itemReceipt.getFormattedAmountSpecimen()}</td>
                    <td data-th="PESO">${itemReceipt.getFormattedWeight()}</td>
                    <td data-th="CORRIDA">${itemReceipt.getFormattedNumberTransport()}</td>
                    <td data-th="PRODUTO">${itemReceipt.idProduct.descriptionProduct}</td>
                    <td data-th="MATERIAL">${itemReceipt.idMaterial.nameMaterial}</td>
                    <td data-th="NORMA">${itemReceipt.idStandard.nameStandard}</td>
                    <td data-th="RESPONSÁVEL">${itemReceipt.idEmployee.nameEmployee}</td>
                    <td data-th="OBSERVAÇÃO">${itemReceipt.markItemReceipt}</td>
                    <td data-th="DATA">${itemReceipt.getFormattedDate()}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
