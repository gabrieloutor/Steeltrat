<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Recebimento - Atualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Atualizar Recebimento</h3>
        <form method="POST" id="formUpdate" action="home">
            <label for="receipts">Recebimentos:&nbsp;</label>
            <select name="receipts" id="mySelect" onchange="select.call(this, event)" >
                <option value="">&#45;&#45; Selecione o Recebimento &#45;&#45;</option>
                <c:forEach var="receipt" items="${receipts}">
                    <option value="${receipt.idReceipt}">
                        <c:out value="${receipt.idReceipt}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="clients">Cliente: </label>
            <select name="clients" id="clients" disabled>
                <option value="">--Selecione o Cliente--</option>
                <c:forEach var="client" items="${clients}">
                    <option value="${client.id_client}">
                        <c:out value="${client.nameClient}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <input type="hidden" name="command" value="Receipt">
            <input type="hidden" name="action" value="updateById">
            <input type="button" value="Atualizar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Receipt&action=read'" value="Cancelar">
        </form>
        <script>
            function select(event) {
                var clients = document.getElementById("clients");
                var mySelect = document.getElementById("mySelect");

                if (mySelect.value !== "") {
            <c:forEach items="${receipts}" var="receipt" varStatus="status">
                    if (mySelect.value === "${receipt.idReceipt}") {
                        clients.value = "${receipt.idClient.id_client}";
                        clients.removeAttribute("disabled");
                    }
            </c:forEach>
                } else {
                    clients.value = "";
                    clients.setAttribute("disabled");
                }
            }
            function myFunction() {
                var clients = document.getElementById("clients");
                var mySelect = document.getElementById("mySelect");
                var formUpdate = document.getElementById("formUpdate");

                switch (true) {
                    case mySelect.value === "":
                        mySelect.focus();
                        alert("Selecione um Recebimento");
                        break;
                    case clients.value === "":
                        clients.focus();
                        alert("Selecione um Cliente");
                        break;
                    default:
                        formUpdate.submit();
                        break;
                }
            }


        </script>
    </body>
</html>
