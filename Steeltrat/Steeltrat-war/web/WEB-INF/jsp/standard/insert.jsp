<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Norma - Inserir</title>
        <link rel="icon" href="../../../img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Inserir Norma</h3>
        <form method="POST" id="formInsert" action="home" >
            <label for="name_standard">Nome:</label> <input type="text" name="name_standard" id="name_standard" size="30" onchange="myFunctionTwo()">
            <br>
            <label for="mark_standard">Observação da Norma:</label> <input type="tel" readonly name="mark_standard" id="mark_standard" size="30">
            <br>
            <input type="hidden" name="command" value="Standard">
            <input type="hidden" name="action" value="insert.confirm">
            <input type="button" value="Incluir" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Standard&action=read'" value="Cancelar">
        </form>
        <script>
            function myFunction() {
                var name_standard = document.getElementById("name_standard");
                var mark_standard = document.getElementById("mark_standard");
                var formInsert = document.getElementById("formInsert");

                switch (true) {
                    case name_standard.value === "":
                        name_standard.focus();
                        alert("Digite o Nome da Norma");
                        break;
                    case mark_standard.value === "":
                        mark_standard.focus();
                        alert("Digite o Observação da Norma");
                        break;
                    default:
                        formInsert.submit();
                        break;
                }
            }
            function myFunctionTwo() {
                var name_standard = document.getElementById("name_standard");
                var mark_standard = document.getElementById("mark_standard");

                switch (true) {
                    case name_standard.value !== "":
                        mark_standard.removeAttribute("readonly");
                        break;
                    case name_standard.value === "":
                        mark_standard.value = "";
                        mark_standard.setAttribute("readonly");
                        break;
                }
            }

        </script>
    </body>
</html>
