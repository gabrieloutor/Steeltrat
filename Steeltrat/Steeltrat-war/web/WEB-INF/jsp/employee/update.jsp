<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Funcionário - Atualizar</title>
        <link rel="icon" href="../../../img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Atualizar Funcionário</h3>
        <form method="POST" id="formUpdate" action="home">
            <label for="employees">Funcionários:&nbsp;</label>
            <select name="employees" id="mySelect" onchange="select.call(this, event)" >
                <option value="">&#45;&#45; Selecione o Funcionário &#45;&#45;</option>
                <c:forEach var="employee" items="${employees}">
                    <option value="${employee.idEmployee}">
                        <c:out value="${employee.nameEmployee}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="name_employee">Nome:</label> <input type="text" readonly name="name_employee" id="name_employee" size="30">
            <br>
            <label for="cpf_employee">Cpf:</label> <input type="text" readonly name="cpf_employee" id="cpf_employee" size="11" maxlength="11">
            <br>
            <label for="position">Cargo: </label>
            <select name="positions" id="positions" disabled>
                <option value="">--Selecione o Cargo--</option>
                <c:forEach var="position" items="${positions}">
                    <option value="${position.idPosition}">
                        <c:out value="${position.namePosition}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="departament">Departamento: </label>
            <select name="departaments" id="departaments" disabled>
                <option value="">--Selecione o Departamento--</option>
                <c:forEach var="departament" items="${departaments}">
                    <option value="${departament.idDepartament}">
                        <c:out value="${departament.nameDepartament}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="user">Usuário: </label>
            <select name="users" id="users" disabled>
                <option value="">--Selecione o Usuário--</option>
                <c:forEach var="user" items="${users}">
                    <option value="${user.idUser}">
                        <c:out value="${user.username}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <input type="hidden" name="command" value="Employee">
            <input type="hidden" name="action" value="updateById">
            <input type="button" value="Atualizar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Employee&action=read'" value="Cancelar">
        </form>
        <script>
            function select(event) {
                var name_employee = document.getElementById("name_employee");
                var cpf_employee = document.getElementById("cpf_employee");
                var position = document.getElementById("positions");
                var departament = document.getElementById("departaments");
                var user = document.getElementById("users");
                var mySelect = document.getElementById("mySelect");

                if (mySelect.value !== "") {
            <c:forEach items="${employees}" var="employee" varStatus="status">
                    if (mySelect.value === "${employee.idEmployee}") {
                        name_employee.value = "${employee.nameEmployee}";
                        name_employee.removeAttribute("readonly");
                        cpf_employee.value = "${employee.cpf}";
                        cpf_employee.removeAttribute("readonly");
                        position.value = "${employee.idPosition.idPosition}";
                        position.removeAttribute("disabled");
                        departament.value = "${employee.idDepartament.idDepartament}";
                        departament.removeAttribute("disabled");
                        user.value = "${employee.idUser.idUser}";
                        user.removeAttribute("disabled");
                    }
            </c:forEach>
                } else {
                    name_employee.value = "";
                    name_employee.setAttribute("readonly");
                    cpf_employee.value = "";
                    cpf_employee.setAttribute("readonly");
                    position.value = "";
                    position.setAttribute("disabled");
                    departament.value = "";
                    departament.setAttribute("disabled");
                    user.value = "";
                    user.setAttribute("disabled");
                }
            }
            function myFunction() {
                var name_employee = document.getElementById("name_employee");
                var cpf_employee = document.getElementById("cpf_employee");
                var position = document.getElementById("positions");
                var departament = document.getElementById("departaments");
                var user = document.getElementById("users");
                var mySelect = document.getElementById("mySelect");
                var formUpdate = document.getElementById("formUpdate");

                switch (true) {
                    case mySelect.value === "":
                        mySelect.focus();
                        alert("Selecione um Funcionário");
                        break;
                    case name_employee.value === "":
                        name_employee.focus();
                        alert("Digite o Nome do Funcionário");
                        break;
                    case cpf_employee.value === "":
                        cpf_employee.focus();
                        alert("Digite o Cpf do Funcionário");
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
                        formUpdate.submit();
                        break;
                }
            }


        </script>
    </body>
</html>
