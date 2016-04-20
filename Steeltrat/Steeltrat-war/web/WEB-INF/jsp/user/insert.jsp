<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Usuário - Inserir</title>
        <link rel="icon" href="../../../img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Inserir Usuário</h3>
        <form method="POST" id="formInsert" action="home" >
            <label for="username">Username:</label> <input type="text" name="username" id="username" size="30" onchange="myFunctionTwo()">
            <br>
            <label for="password">Senha:</label> <input type="password" readonly name="password" id="password" size="30">
            <br>
            <input type="button" value="Incluir" onclick="myFunction()">
            <br>
            <input type="hidden" name="command" value="User">
            <input type="hidden" name="action" value="insert.confirm">
            <input type="button" onClick="location.href = 'home?command=User&action=read'" value="Cancelar">
        </form>
        <script>
            function myFunction() {
                var username = document.getElementById("username");
                var password = document.getElementById("password");
                var formInsert = document.getElementById("formInsert");

                switch (true) {
                    case username.value === "":
                        username.focus();
                        alert("Digite o Username do Usuário");
                        break;
                    case password.value === "":
                        password.focus();
                        alert("Digite a Senha do Usuário");
                        break;
                    default:
                        formInsert.submit();
                        break;
                }
            }
            function myFunctionTwo() {
                var username = document.getElementById("username");
                var password = document.getElementById("password");

                switch (true) {
                    case username.value !== "":
                        password.removeAttribute("readonly");
                        break;
                    case username.value === "":
                        password.value = "";
                        password.setAttribute("readonly");
                        break;
                }
            }

        </script>
    </body>
</html>
