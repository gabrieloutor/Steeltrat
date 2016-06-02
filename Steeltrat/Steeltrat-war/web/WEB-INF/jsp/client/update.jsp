<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Cliente - Atualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <script src="js/validation.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Atualizar Cliente</h3>
        <form method="POST" id="formUpdate" action="home">
            <label for="clients">Clientes:&nbsp;</label>
            <select name="clients" id="mySelect" onchange="select.call(this, event)" >
                <option value="">&#45;&#45; Selecione o Cliente &#45;&#45;</option>
                <c:forEach var="client" items="${clients}">
                    <option value="${client.idClient}">
                        <c:out value="${client.nameClient}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="name_client">Novo nome do Cliente: </label> <input type="text" readonly id="name_client" name="name_client" size="60" maxlength="60" onkeypress="return alpha(event,letters)">
            <br>
            <label for="telephone_client">Telefone:</label> <input type="text" readonly name="telephone_client" id="telephone_client" size="15" maxlength="12" onkeypress="return alpha(event,numbers)">
            * Utilizar apenas números. Exemplo: 551142087181
            <br>
            <label for="addresses">Endereços:&nbsp;</label>
            <select name="addresses" id="addresses" disabled>
                <option value="">&#45;&#45; Selecione um C.E.P. &#45;&#45;</option>
                <c:forEach var="address" items="${addresses}">
                    <option value="${address.idAddress}">
                        <c:out value="${address.getFormattedZipcode()}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <input type="hidden" name="command" value="Client">
            <input type="hidden" name="action" value="updateById">
            <input type="button" value="Atualizar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Client&action=read'" value="Cancelar">
        </form>
        <script>
            function select(event) {
                var name_client = document.getElementById("name_client");
                var telephone_client = document.getElementById("telephone_client");
                var addresses = document.getElementById("addresses");
                var mySelect = document.getElementById("mySelect");

                if (mySelect.value !== "") {
            <c:forEach items="${clients}" var="client" varStatus="status">
                    if (mySelect.value === "${client.idClient}") {
                        name_client.value = "${client.nameClient}";
                        name_client.removeAttribute("readonly");
                        telephone_client.value = "${client.telephone}";
                        telephone_client.removeAttribute("readonly");
                        addresses.value = "${client.idAddress.idAddress}";
                        addresses.removeAttribute("disabled");
                    }
            </c:forEach>
                } else {
                    name_client.value = "";
                    name_client.setAttribute("readonly");
                    telephone_client.value = "";
                    telephone_client.setAttribute("readonly");
                    addresses.value = "";
                    addresses.setAttribute("disabled");
                }
            }
            function myFunction() {
                var name_client = document.getElementById("name_client");
                var telephone_client = document.getElementById("telephone_client");
                var addresses = document.getElementById("addresses");
                var mySelect = document.getElementById("mySelect");
                var formUpdate = document.getElementById("formUpdate");

                switch (true) {
                    case mySelect.value === "":
                        mySelect.focus();
                        alert("Selecione um Cliente");
                        break;
                    case name_client.value === "":
                        name_client.focus();
                        alert("Digite o Nome do Cliente");
                        break;
                    case telephone_client.value === "" || telephone_client.value.length !== 12:
                        telephone_client.focus();
                        alert("Telefone inválido");
                        break;
                    default:
                        formUpdate.submit();
                        break;
                }
            }


        </script>
    </body>
</html>
