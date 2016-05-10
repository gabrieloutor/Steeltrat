<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="icon" href="img/favicon.ico" />
        <link rel="stylesheet" href="css/reset.css">
        <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css'>
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <c:if test="${returnMsgSuccessfully!=null}">
            <div class="returnMsgSuccessfully">${returnMsgSuccessfully}</div>
            ${returnMsgSuccessfully=null}
        </c:if>
        <form class="login" action="home" method="POST">
            <fieldset>
                <legend class="legend">Login</legend>
                <div class="input">
                    <input type="text" placeholder="Usuário" name="username" maxlength="15" required />
                    <span><i class="fa fa-user"></i></span>
                </div>
                <div class="input">
                    <input type="password" placeholder="Senha" name="password" maxlength="32" required />
                    <span><i class="fa fa-lock"></i></span>
                </div>
                <button type="submit" class="submit" id="submit"><i class="fa fa-long-arrow-right" id="button"></i></button>
            </fieldset>
            <c:if test="${returnMsgError!=null}">
                <div class="returnMsgError">${returnMsgError}</div>
                <script>
                    var inputs = document.getElementsByTagName("INPUT")[0];
                    inputs.style.borderColor = '#E02A28';
                    inputs = document.getElementsByTagName("INPUT")[1];
                    inputs.style.borderColor = '#E02A28';
                </script>
                ${returnMsgError=null}
            </c:if>
            <input type="hidden" name="command" value="User" />
            <input type="hidden" name="action" value="login" />
        </form>
        <script src="js/jquery.min.js"></script>
        <script src="js/index.js"></script>
    </body>
</html>
