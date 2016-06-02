<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Funcionário - Inserir</title>
        <link rel="icon" href="img/favicon.ico" />
        <script src="js/validation.js" type="text/javascript"></script>
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Inserir Funcionário</h3>
        <form method="POST" id="formInsert" action="home">
            <label for="name_employee">Nome:</label> <input type="text" name="name_employee" id="name_employee" onchange="myFunctionTwo()" size="35" maxlength="35" onkeypress="return alpha(event,letters)">
            <br>
            <label for="cpf_employee">C.P.F.:</label> <input type="text" readonly name="cpf_employee" id="cpf_employee" size="11" maxlength="11" onkeypress="return alpha(event,numbers)">
            <br>
            <label for="position">Cargo: </label>
            <select name="positions" id="positions" disabled>
                <option value="">&#45;&#45; Selecione o Cargo &#45;&#45;</option>
                <c:forEach var="position" items="${positions}">
                    <option value="${position.idPosition}">
                        <c:out value="${position.namePosition}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="departament">Departamento: </label>
            <select name="departaments" id="departaments" disabled>
                <option value="">&#45;&#45; Selecione o Departamento &#45;&#45;</option>
                <c:forEach var="departament" items="${departaments}">
                    <option value="${departament.idDepartament}">
                        <c:out value="${departament.nameDepartament}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="user">Usuário: </label>
            <select name="users" id="users" disabled>
                <option value="">&#45;&#45; Selecione o Usuário &#45;&#45;</option>
                <c:forEach var="user" items="${users}">
                    <option value="${user.idUser}">
                        <c:out value="${user.username}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <input type="hidden" name="command" value="Employee">
            <input type="hidden" name="action" value="insert.confirm">
            <input type="button" value="Incluir" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Employee&action=read'" value="Cancelar">
        </form>
        <script>
            function myFunction() {
                var name_employee = document.getElementById("name_employee");
                var cpf_employee = document.getElementById("cpf_employee");
                var position = document.getElementById("positions");
                var departament = document.getElementById("departaments");
                var user = document.getElementById("users");
                var formInsert = document.getElementById("formInsert");

                switch (true) {
                    case name_employee.value === "":
                        name_employee.focus();
                        alert("Digite o Nome do Funcionário");
                        break;
                    case cpf_employee.value === "" || cpf_employee.value.length < 11:
                        cpf_employee.focus();
                        alert("C.P.F. inválido");
                        break;
                    case position.value === "":
                        position.focus();
                        alert("Selecione um Cargo");
                        break;
                    case departament.value === "":
                        departament.focus();
                        alert("Selecione um Departamento");
                        break;
                    case user.value === "":
                        user.focus();
                        alert("Selecione um Usuário");
                        break;
                    default:
                        formInsert.submit();
                        break;
                }
            }
            function myFunctionTwo() {
                var name_employee = document.getElementById("name_employee");
                var cpf_employee = document.getElementById("cpf_employee");
                var position = document.getElementById("positions");
                var departament = document.getElementById("departaments");
                var user = document.getElementById("users");

                switch (true) {
                    case name_employee.value !== "":
                        cpf_employee.removeAttribute("readonly");
                        user.removeAttribute("disabled");
                        position.removeAttribute("disabled");
                        departament.removeAttribute("disabled");
                        break;
                    case name_employee.value === "":
                        cpf_employee.value = "";
                        cpf_employee.setAttribute("readonly");
                        user.value = "";
                        user.setAttribute("disabled");
                        position.value = "";
                        position.setAttribute("disabled");
                        departament.value = "";
                        departament.setAttribute("disabled");
                        break;
                }
            }

        </script>
    </body>
</html>
