<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Departamento - Atualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Atualizar Departamento</h3>
        <form method="POST" id="formUpdate" action="home">
            <label for="departaments">Departamentos:&nbsp;</label>
            <select name="departaments" id="mySelect" onchange="select.call(this, event)" >
                <option value="">&#45;&#45; Selecione o Departamento &#45;&#45;</option>
                <c:forEach var="departament" items="${departaments}">
                    <option value="${departament.idDepartament}">
                        <c:out value="${departament.nameDepartament}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="name_departament">Novo name do Departamento: </label> <input type="text" readonly id="name_departament" name="name_departament" size="35" maxlength="35">
            <br>
            <input type="hidden" name="command" value="Departament">
            <input type="hidden" name="action" value="updateById">
            <input type="button" value="Atualizar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Departament&action=read'" value="Cancelar">
        </form>
        <script>
            function select(event) {
                var name_departament = document.getElementById("name_departament");
                var mySelect = document.getElementById("mySelect");

                if (mySelect.value !== "") {
            <c:forEach items="${departaments}" var="departament" varStatus="status">
                    if (mySelect.value === "${departament.idDepartament}") {
                        name_departament.value = "${departament.nameDepartament}";
                        name_departament.removeAttribute("readonly");
                    }
            </c:forEach>

                } else {
                    name_departament.value = "";
                    name_departament.setAttribute("readonly");
                }
            }

            function myFunction() {
                var name_departament = document.getElementById("name_departament");
                var mySelect = document.getElementById("mySelect");
                var formUpdate = document.getElementById("formUpdate");

                switch (true) {
                    case mySelect.value === "":
                        mySelect.focus();
                        alert("Selecione um Departamento");
                        break;
                    case name_departament.value === "":
                        name_departament.focus();
                        alert("Digite um Departamento");
                        break;
                    default:
                        formUpdate.submit();
                        break;
                }
            }
        </script>
    </body>
</html>
