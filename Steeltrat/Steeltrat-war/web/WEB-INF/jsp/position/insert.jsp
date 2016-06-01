<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Cargo - Inserir</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Inserir Cargo</h3>
        <form method="POST" id="formInsert" action="home">
            <label for="namePosition">Nome Cargo:</label> <input type="text" name="name_position" id="name_position" size="35" maxlength="35">
            <br>
            <input type="hidden" name="command" value="Position">
            <input type="hidden" name="action" value="insert.confirm">
            <input type="button" value="Incluir" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href='home?command=Position&action=read'" value="Cancelar">
        </form>
        <script>
            function myFunction() {
                var name_position = document.getElementById("name_position");
                var formInsert = document.getElementById("formInsert");
                if (name_position.value === "") {
                    name_position.focus();
                    alert("Digite o nome do Cargo");
                } else {
                    formInsert.submit();
                }
            }

        </script>
    </body>
</html>
