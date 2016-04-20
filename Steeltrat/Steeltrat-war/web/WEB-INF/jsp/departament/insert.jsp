<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Departamento - Inserir</title>
        <link rel="icon" href="../../../img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Inserir Departamento</h3>
        <form method="POST" id="formInsert" action="home">
            <label for="name_departament">Nome do Departamento:</label> <input type="text" name="name_departament" id="name_departament" size="30">
            <br>
            <input type="hidden" name="command" value="Departament">
            <input type="hidden" name="action" value="insert.confirm">
            <input type="button" value="Incluir" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Departament&action=read'" value="Cancelar">
        </form>
        <script>
            function myFunction() {
                var name_departament = document.getElementById("name_departament");
                var formInsert = document.getElementById("formInsert");
                if (name_departament.value === "") {
                    name_departament.focus();
                    alert("Digite o nome do Departamento");
                } else {
                    formInsert.submit();
                }
            }

        </script>
    </body>
</html>
