<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Norma - Deletar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Deletar Norma</h3>
        <form method="POST" id="formDelete" action="home">
            <label for="standards">Normas:&nbsp;</label>
            <select name="standards" id="mySelect">
                <option value="">&#45;&#45; Selecione a Norma &#45;&#45;</option>
                <c:forEach var="standard" items="${standards}">
                    <option value="${standard.idStandard}">
                        <c:out value="${standard.nameStandard}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <input type="hidden" name="command" value="Standard">
            <input type="hidden" name="action" value="delete.confirm">
            <input type="button" value="Deletar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Standard&action=read'" value="Cancelar">
        </form>
        <script>
            function myFunction() {
                var mySelect = document.getElementById("mySelect");
                var formDelete = document.getElementById("formDelete");
                if (mySelect.value === "") {
                    mySelect.focus();
                    alert("Selecione a Norma");
                } else {
                    formDelete.submit();
                }
            }

        </script>
    </body>
</html>
