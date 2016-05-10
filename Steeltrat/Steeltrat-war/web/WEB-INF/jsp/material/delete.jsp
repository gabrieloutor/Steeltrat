<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Material - Deletar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Deletar Material</h3>
        <form method="POST" id="formDelete" action="home">
            <label for="materials">Materiais:&nbsp;</label>
            <select name="materials" id="mySelect">
                <option value="">&#45;&#45; Selecione o Material &#45;&#45;</option>
                <c:forEach var="material" items="${materials}">
                    <option value="${material.idMaterial}">
                        <c:out value="${material.nameMaterial}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <input type="hidden" name="command" value="Material">
            <input type="hidden" name="action" value="delete.confirm">
            <input type="button" value="Deletar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Material&action=read'" value="Cancelar">
        </form>
        <script>
            function myFunction() {
                var mySelect = document.getElementById("mySelect");
                var formDelete = document.getElementById("formDelete");
                if (mySelect.value === "") {
                    mySelect.focus();
                    alert("Selecione um Material");
                } else {
                    formDelete.submit();
                }
            }

        </script>
    </body>
</html>
