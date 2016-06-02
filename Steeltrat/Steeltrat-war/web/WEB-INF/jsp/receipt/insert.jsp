<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Recebimento - Inserir</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Inserir Recebimento</h3>
        <form method="POST" id="formInsert" action="home">
            <label for="number_receipt">Nr Recebimento:</label> <input type="number" readonly value="${number_receipt}" name="number_receipt" id="number_receipt" size="10" onchange="myFunctionTwo()">
            <br>
            <label for="client">Cliente: </label>
            <select name="clients" id="clients">
                <option value="">--Selecione o Cliente--</option>
                <c:forEach var="client" items="${clients}">
                    <option value="${client.idClient}">
                        <c:out value="${client.nameClient}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <input type="hidden" name="command" value="Receipt">
            <input type="hidden" name="action" value="insert.confirm">
            <input type="button" value="Incluir" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Receipt&action=read'" value="Cancelar">
        </form>
        <script>
            function myFunction() {
                var number_receipt = document.getElementById("number_receipt");
                var clients = document.getElementById("clients");
                var formInsert = document.getElementById("formInsert");

                switch (true) {
                    case number_receipt.value === "":
                        number_receipt.focus();
                        alert("Sem Número de Recebimento");
                        break;
                    case clients.value === "":
                        clients.focus();
                        alert("Selecione um Cliente");
                        break;
                    default:
                        formInsert.submit();
                        break;
                }
            }
            function myFunctionTwo() {
                var number_receipt = document.getElementById("number_receipt");
                var clients = document.getElementById("clients");

                switch (true) {
                    case number_receipt.value !== "":
                        clients.removeAttribute("disabled");
                        break;
                    case number_receipt.value === "":
                        clients.value = "";
                        clients.setAttribute("disabled");
                        number_receipt.setAttribute("readonly");
                        break;
                }
            }

        </script>
    </body>
</html>
