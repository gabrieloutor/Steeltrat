<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/loginTeste.css' rel='stylesheet'>
        <title>Steeltrat | Login</title>
    </head>
    <body>
        <section id="login">
            <div id="returnMsgSuccessfully">${returnMsgSuccessfully}</div>
            ${returnMsgSuccessfully=null}
            <div id="returnMsgError">${returnMsgError}</div>
            ${returnMsgError=null}
            <h2 class="section-heading">Steeltrat - Sistema</h2>
            <img src="img/logos/logosteel.jpg" alt="Steeltrat Tratamento Térmico>" id="logoSteel" />
            <form action="home" id="formLogin" class="formLogin" method="POST">
                <p><input type="text" placeholder="Seu Login *" name="username" id="username" value="${cookie.user.value}" required/></p>
                <p><input type="password" placeholder="Sua Senha *" name="password" id="password" value="${cookie.password.value}" required/></p>
                <p><input type="checkbox" name="session" value="session">Mantenha-me conectado</p>
                <p><input type="hidden" name="command" value="User"/></p>
                <p><input type="hidden" name="action" value="login"/></p>
                <p><input type="button" value="Acessar" onclick="myFunction()" required></p>
            </form>

            <script>
                function myFunction() {
                    var username = document.getElementById("username");
                    var password = document.getElementById("password");
                    var formLogin = document.getElementById("formLogin");
                    switch (true) {
                        case (username.value === ""):
                            username.focus();
                            alert("Digite o Usuário");
                            break;
                        case (password.value === ""):
                            password.focus();
                            alert("Digite a Senha");
                            break;
                        default:
                            formLogin.submit();
                            break;
                    }
                }

            </script>
        </section>
    </body>
</html>
