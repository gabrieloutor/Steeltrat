<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Endereço - Inserir</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
        <script src="js/validation.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Inserir Endereço</h3>
        <form method="POST" id="formInsert" action="home" >
            <label for="zipcode">C.E.P.:</label> <input type="text" name="zipcode" id="zipcode" onchange="myFunctionTwo()" size="12" maxlength="8" onkeypress="return alpha(event,numbers)">
            * Utilizar apenas números. Exemplo: 06474050
            <br>
            <label for="number_address">Número do Endereço:</label> <input type="text" readonly name="number_address" id="number_address" size="10" maxlength="6" onkeypress="return alpha(event,numbers)">
            <br>
            <input type="hidden" name="command" value="Address">
            <input type="hidden" name="action" value="insert.confirm">
            <input type="button" value="Incluir" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Address&action=read'" value="Cancelar">
        </form>
        <script>
            function myFunction() {
                var zipcode = document.getElementById("zipcode");
                var number_address = document.getElementById("number_address");
                var formInsert = document.getElementById("formInsert");
                
                switch (true) {
                    case zipcode.value === "" || zipcode.value.length !== 8:
                        zipcode.focus();
                        alert("C.E.P. inválido");
                        break;
                    case number_address.value === "":
                        number_address.focus();
                        alert("Digite o Número do Endereço");
                        break;
                    default:
                        formInsert.submit();
                        break;
                }
            }
            function myFunctionTwo() {
                var zipcode = document.getElementById("zipcode");
                var number_address = document.getElementById("number_address");
                
                switch (true) {
                    case zipcode.value !== "" && zipcode.value.length === 8:
                        number_address.removeAttribute("readonly");
                        break;
                    case zipcode.value === "" || zipcode.value.length !== 8:
                        number_address.value = "";
                        number_address.setAttribute("readonly");
                        break;
                }
            }

        </script>
    </body>
</html>
