<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Item Recebimento - Deletar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Deletar Item Recebimento</h3>
        <form method="POST" id="formDelete" action="home">
            <label for="receipt">Recebimento: </label>
            <select name="receipts" id="receipts" onchange="select.call(this, event)" >
                <option value="">&#45;&#45; Selecione o Nr do Recebimento &#45;&#45;</option>
                <c:forEach var="receipt" items="${receipts}">
                    <option value="${receipt.idReceipt}">
                        <c:out value="${receipt.idReceipt}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="itemReceipt">Item do Recebimento: </label>
            <select name="itemReceipts" id="itemReceipts" disabled>
                <option value="">&#45;&#45; Selecione o Nr do Item &#45;&#45;</option>
            </select>
            <br>
            <input type="hidden" name="command" value="ItemsReceipt">
            <input type="hidden" name="action" value="delete.confirm">
            <input type="button" value="Deletar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=ItemReceipt&action=read'" value="Cancelar">
        </form>
        <script>
            function select(event) {
                var receipts = document.getElementById("receipts");
                var itemReceipts = document.getElementById("itemReceipts");
                itemReceipts.options.length = 0;
                var opt = document.createElement('option');
                opt.value = "";
                opt.innerHTML = "-- Selecione o Nr do Item --";
                itemReceipts.appendChild(opt);

                itemReceipts.setAttribute("disabled");
                itemReceipts.value = "";
                if (receipts.value !== "") {
                    itemReceipts.removeAttribute("disabled");
                    itemReceipts.value = "";
            <c:forEach items="${receipts}" var="receipt" varStatus="status">
                    if (receipts.value === "${receipt.idReceipt}") {
                        i = 0;
                <c:forEach var="itemReceipt" items="${itemReceipts}">
                        if (receipts.value === "${itemReceipt.idReceipt.idReceipt}") {
                            i++;
                            opt = document.createElement('option');
                            opt.value = ${itemReceipt.idItemReceipt};
                            opt.innerHTML = i;
                            itemReceipts.appendChild(opt);
                        }
                </c:forEach>
                    }
            </c:forEach>
                } else {
                    itemReceipts.setAttribute("disabled");
                    itemReceipts.value = "";
                }
            }
            function myFunction() {
                var receipts = document.getElementById("receipts");
                var itemReceipts = document.getElementById("itemReceipts");
                var formDelete = document.getElementById("formDelete");

                switch (true) {
                    case receipts.value === "":
                        receipts.focus();
                        alert("Selecione um Recebimento");
                        break;
                    case itemReceipts.value === "":
                        itemReceipts.focus();
                        alert("Selecione um Item do Recebimnto");
                        break;
                    default:
                        formDelete.submit();
                        break;
                }
            }


        </script>
    </body>
</html>
