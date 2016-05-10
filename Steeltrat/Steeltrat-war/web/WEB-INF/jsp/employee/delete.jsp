<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Funcionário - Deletar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Deletar Funcionário</h3>
        <form method="POST" id="formDelete" action="home">
            <label for="employees">Funcionários:&nbsp;</label>
            <select name="employees" id="mySelect">
                <option value="">&#45;&#45; Selecione o Funcionário &#45;&#45;</option>
                <c:forEach var="employee" items="${employees}">
                    <option value="${employee.idEmployee}">
                        <c:out value="${employee.nameEmployee}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <input type="hidden" name="command" value="Employee">
            <input type="hidden" name="action" value="delete.confirm">
            <input type="button" value="Deletar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Employee&action=read'" value="Cancelar">
        </form>
        <script>
            function myFunction() {
                var mySelect = document.getElementById("mySelect");
                var formDelete = document.getElementById("formDelete");
                if (mySelect.value === "") {
                    mySelect.focus();
                    alert("Selecione o Funcionário");
                } else {
                    formDelete.submit();
                }
            }

        </script>
    </body>
</html>
