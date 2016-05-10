<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Endereço - Atualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Atualizar Endereço</h3>
        <form method="POST" id="formUpdate" action="home">
            <label for="addresses">Endereços:&nbsp;</label>
            <select name="addresses" id="mySelect" onchange="select.call(this, event)" >
                <option value="">&#45;&#45; Selecione um C.E.P. &#45;&#45;</option>
                <c:forEach var="address" items="${addresses}">
                    <option value="${address.idAddress}">
                        <c:out value="${address.zipcode}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="zipcode">Novo C.E.P. do Endereço </label> <input type="text" readonly id="zipcode" name="zipcode" size="8" maxlength="8">
            <br>
            <label for="number_address">Número do Endereço</label> <input type="number" readonly name="number_address" id="number_address" size="10" maxlength="10">
            <br>
            <input type="hidden" name="command" value="Address">
            <input type="hidden" name="action" value="updateById">
            <input type="button" value="Atualizar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Address&action=read'" value="Cancelar">
        </form>
        <script>
            function select(event) {
                var zipcode = document.getElementById("zipcode");
                var number_address = document.getElementById("number_address");
                var mySelect = document.getElementById("mySelect");

                if (mySelect.value !== "") {
            <c:forEach items="${addresses}" var="address" varStatus="status">
                    if (mySelect.value === "${address.idAddress}") {
                        zipcode.value = "${address.zipcode}";
                        zipcode.removeAttribute("readonly");
                        number_address.value = "${address.numberAddress}";
                        number_address.removeAttribute("readonly");
                    }
            </c:forEach>
                } else {
                    zipcode.value = "";
                    zipcode.setAttribute("readonly");
                    number_address.value = "";
                    number_address.setAttribute("readonly");
                }
            }
            function myFunction() {
                var zipcode = document.getElementById("zipcode");
                var number_address = document.getElementById("number_address");
                var mySelect = document.getElementById("mySelect");
                var formUpdate = document.getElementById("formUpdate");

                switch (true) {
                    case mySelect.value === "":
                        mySelect.focus();
                        alert("Selecione um C.E.P.");
                        break;
                    case zipcode.value === "":
                        zipcode.focus();
                        alert("Digite o C.E.P. do Endereço");
                        break;
                    case number_address.value === "":
                        number_address.focus();
                        alert("Digite o Número do Endereço");
                        break;
                    default:
                        formUpdate.submit();
                        break;
                }
            }


        </script>
    </body>
</html>
