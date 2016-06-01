<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Material - Atualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <script src="js/validation.js" type="text/javascript"></script>
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Atualizar Material</h3>
        <form method="POST" id="formUpdate" action="home">
            <label for="materials">Materiais:&nbsp;</label>
            <select name="materials" id="mySelect" onchange="select.call(this, event)" >
                <option value="">&#45;&#45; Selecione o Material &#45;&#45;</option>
                <c:forEach var="material" items="${materials}">
                    <option value="${material.idMaterial}">
                        <c:out value="${material.nameMaterial}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="name_material">Novo nome do Material: </label> <input type="text" readonly id="name_material" name="name_material" size="60" maxlength="60">
            <br>
            <label for="mark_material">Observação do Material:</label> <textarea name="mark_material" id="mark_material" readonly maxlength="150" size="150" ></textarea>
            <br>
            <input type="hidden" name="command" value="Material">
            <input type="hidden" name="action" value="updateById">
            <input type="button" value="Atualizar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Material&action=read'" value="Cancelar">
        </form>
        <script>
            function select(event) {
                var name_material = document.getElementById("name_material");
                var mark_material = document.getElementById("mark_material");
                var mySelect = document.getElementById("mySelect");

                if (mySelect.value !== "") {
            <c:forEach items="${materials}" var="material" varStatus="status">
                    if (mySelect.value === "${material.idMaterial}") {
                        name_material.value = "${material.nameMaterial}";
                        name_material.removeAttribute("readonly");
                        mark_material.value = "${material.markMaterial}";
                        mark_material.removeAttribute("readonly");
                    }
            </c:forEach>
                } else {
                    name_material.value = "";
                    name_material.setAttribute("readonly");
                    mark_material.value = "";
                    mark_material.setAttribute("readonly");
                }
            }
            function myFunction() {
                var name_material = document.getElementById("name_material");
                var mark_material = document.getElementById("mark_material");
                var mySelect = document.getElementById("mySelect");
                var formUpdate = document.getElementById("formUpdate");

                switch (true) {
                    case mySelect.value === "":
                        mySelect.focus();
                        alert("Selecione um Material");
                        break;
                    case name_material.value === "":
                        name_material.focus();
                        alert("Digite o Nome do Material");
                        break;
                    case mark_material.value === "":
                        mark_material.focus();
                        alert("Digite o Observação do Material");
                        break;
                    default:
                        formUpdate.submit();
                        break;
                }
            }


        </script>
    </body>
</html>
