<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Material - Inserir</title>
        <link rel="icon" href="../../../img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Inserir Material</h3>
        <form method="POST" id="formInsert" action="home" >
            <label for="name_material">Nome:</label> <input type="text" name="name_material" id="name_material" size="30" onchange="myFunctionTwo()">
            <br>
            <label for="mark_material">Observação do Material:</label> <input type="tel" readonly name="mark_material" id="mark_material" size="30">
            <br>
            <input type="hidden" name="command" value="Material">
            <input type="hidden" name="action" value="insert.confirm">
            <input type="button" value="Incluir" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Material&action=read'" value="Cancelar">
        </form>
        <script>
            function myFunction() {
                var name_material = document.getElementById("name_material");
                var mark_material = document.getElementById("mark_material");
                var formInsert = document.getElementById("formInsert");

                switch (true) {
                    case name_material.value === "":
                        name_material.focus();
                        alert("Digite o Nome do Material");
                        break;
                    case mark_material.value === "":
                        mark_material.focus();
                        alert("Digite a Observação do Material");
                        break;
                    default:
                        formInsert.submit();
                        break;
                }
            }
            function myFunctionTwo() {
                var name_material = document.getElementById("name_material");
                var mark_material = document.getElementById("mark_material");

                switch (true) {
                    case name_material.value !== "":
                        mark_material.removeAttribute("readonly");
                        break;
                    case name_material.value === "":
                        mark_material.value = "";
                        mark_material.setAttribute("readonly");
                        break;
                }
            }

        </script>
    </body>
</html>
