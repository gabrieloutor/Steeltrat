<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Cargo - Atualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Atualizar Cargo</h3>
        <form method="POST" id="formUpdate" action="home">
            <label for="positions">Cargos:&nbsp;</label>
            <select name="positions" id="mySelect" onchange="select.call(this, event)" >
                <option value="">&#45;&#45; Selecione o Cargo &#45;&#45;</option>
                <c:forEach var="position" items="${positions}">
                    <option value="${position.idPosition}">
                        <c:out value="${position.namePosition}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="namePosition">Novo nome do Cargo: </label> <input type="text" readonly id="name_position" name="name_position" size="35" maxlength="35">
            <br>
            <input type="hidden" name="command" value="Position">
            <input type="hidden" name="action" value="updateById">
            <input type="button" value="Atualizar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Position&action=read'" value="Cancelar">
        </form>
        <script>
            function select(event) {
                var name_position = document.getElementById("name_position");
                var mySelect = document.getElementById("mySelect");

                if (mySelect.value !== "") {
            <c:forEach items="${positions}" var="position" varStatus="status">
                    if (mySelect.value === "${position.idPosition}") {
                        name_position.value = "${position.namePosition}";
                        name_position.removeAttribute("readonly");
                    }
            </c:forEach>

                } else {
                    name_position.value = "";
                    name_position.setAttribute("readonly");
                }
            }

            function myFunction() {
                var name_position = document.getElementById("name_position");
                var mySelect = document.getElementById("mySelect");
                var formUpdate = document.getElementById("formUpdate");

                switch (true) {
                    case mySelect.value === "":
                        mySelect.focus();
                        alert("Selecione um Cargo");
                        break;
                    case name_position.value === "":
                        name_position.focus();
                        alert("Digite o nome do Cargo");
                        break;
                    default:
                        formUpdate.submit();
                        break;
                }
            }
        </script>
    </body>
</html>
