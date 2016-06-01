<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Cliente - Deletar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Deletar Cliente</h3>
        <form method="POST" id="formDelete" action="home">
            <label for="clients">Clientes:&nbsp;</label>
            <select name="clients" id="mySelect">
                <option value="">&#45;&#45; Selecione o Cliente &#45;&#45;</option>
                <c:forEach var="client" items="${clients}">
                    <option value="${client.idClient}">
                        <c:out value="${client.nameClient}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <input type="hidden" name="command" value="Client">
            <input type="hidden" name="action" value="delete.confirm">
            <input type="button" value="Deletar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Client&action=read'" value="Cancelar">
        </form>
        <script>
            function myFunction() {
                var mySelect = document.getElementById("mySelect");
                var formDelete = document.getElementById("formDelete");
                if (mySelect.value === "") {
                    mySelect.focus();
                    alert("Selecione um Cliente");
                } else {
                    formDelete.submit();
                }
            }

        </script>
    </body>
</html>
