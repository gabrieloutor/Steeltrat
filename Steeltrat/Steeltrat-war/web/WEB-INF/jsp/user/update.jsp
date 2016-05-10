<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Usuário - Atualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Atualizar Usuário</h3>
        <form method="POST" id="formUpdate" action="home">
            <label for="users">Usuarios:&nbsp;</label>
            <select name="users" id="mySelect" onchange="select.call(this, event)" >
                <option value="">&#45;&#45; Selecione o Usuário &#45;&#45;</option>
                <c:forEach var="user" items="${users}">
                    <option value="${user.idUser}">
                        <c:out value="${user.username}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="username">Novo username: </label> <input type="text" readonly id="username" name="username" size="15" maxlength="15">
            <br>
            <label for="password">Senha:</label> <input type="password" readonly name="password" id="password" size="32" max="32">
            <br>
            <input type="button" value="Atualizar" onclick="myFunction()">
            <br>
            <input type="hidden" name="command" value="User">
            <input type="hidden" name="action" value="updateById">
            <input type="button" onClick="location.href = 'home?command=User&action=read'" value="Cancelar">
        </form>
        <script>
            function select(event) {
                var username = document.getElementById("username");
                var password = document.getElementById("password");
                var mySelect = document.getElementById("mySelect");

                if (mySelect.value !== "") {
            <c:forEach items="${users}" var="user" varStatus="status">
                    if (mySelect.value === "${user.idUser}") {
                        username.value = "${user.username}";
                        username.removeAttribute("readonly");
                        password.value = "${user.password}";
                        password.removeAttribute("readonly");
                    }
            </c:forEach>
                } else {
                    username.value = "";
                    username.setAttribute("readonly");
                    password.value = "";
                    password.setAttribute("readonly");
                }
            }
            function myFunction() {
                var username = document.getElementById("username");
                var password = document.getElementById("password");
                var mySelect = document.getElementById("mySelect");
                var formUpdate = document.getElementById("formUpdate");

                switch (true) {
                    case mySelect.value === "":
                        mySelect.focus();
                        alert("Selecione um Usuário");
                        break;
                    case username.value === "":
                        username.focus();
                        alert("Digite o Nome do Usuário");
                        break;
                    case password.value === "":
                        password.focus();
                        alert("Digite a Senha do Usuário");
                        break;
                    default:
                        formUpdate.submit();
                        break;
                }
            }


        </script>
    </body>
</html>
