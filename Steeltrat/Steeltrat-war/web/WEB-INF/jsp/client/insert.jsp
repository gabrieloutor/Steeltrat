<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Cliente - Inserir</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <div id="returnMsgError">${returnMsgError}</div>
        ${returnMsgError=null}
        <h3>Inserir Cliente</h3>
        <form method="POST" id="formInsert" action="home" >
            <label for="name_client">Nome Cliente:</label> <input type="text" name="name_client" id="name_client" size="30" onchange="myFunctionTwo()">
            <br>
            <label for="telephone_client">Telefone Cliente:</label> <input type="number" readonly name="telephone_client" id="telephone_client" size="30" maxlength="19">
            <br>
            <label for="addresses">Endere�os:&nbsp;</label>
            <select name="addresses" id="addresses" disabled>
                <option value="">&#45;&#45; Selecione um C.E.P. &#45;&#45;</option>
                <c:forEach var="address" items="${addresses}">
                    <option value="${address.idAddress}">
                        <c:out value="${address.zipcode}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <input type="hidden" name="command" value="Client">
            <input type="hidden" name="action" value="insert.confirm">
            <input type="button" value="Incluir" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Client&action=read'" value="Cancelar">
        </form>
        <script>
            function myFunction() {
                var name_client = document.getElementById("name_client");
                var telephone_client = document.getElementById("telephone_client");
                var addresses = document.getElementById("addresses");
                var formInsert = document.getElementById("formInsert");

                switch (true) {
                    case name_client.value === "":
                        name_client.focus();
                        alert("Digite o Nome do Cliente");
                        break;
                    case telephone_client.value === "":
                        telephone_client.focus();
                        alert("Digite o Telefone do Cliente");
                        break;
                    case addresses.value === "":
                        addresses.focus();
                        alert("Selecione um C.E.P.");
                        break;
                    default:
                        formInsert.submit();
                        break;
                }
            }
            function myFunctionTwo() {
                var name_client = document.getElementById("name_client");
                var addresses = document.getElementById("addresses");
                var telephone_client = document.getElementById("telephone_client");

                switch (true) {
                    case name_client.value !== "":
                        telephone_client.removeAttribute("readonly");
                        addresses.removeAttribute("disabled");
                        break;
                    case name_client.value === "":
                        telephone_client.value = "";
                        telephone_client.setAttribute("readonly");
                        addresses.value = "";
                        addresses.setAttribute("disabled");
                        break;
                }
            }

        </script>
    </body>
</html>
