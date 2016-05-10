<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Norma - Atualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Atualizar Norma</h3>
        <form method="POST" id="formUpdate" action="home">
            <label for="standards">Normas:&nbsp;</label>
            <select name="standards" id="mySelect" onchange="select.call(this, event)" >
                <option value="">&#45;&#45; Selecione a Norma &#45;&#45;</option>
                <c:forEach var="standard" items="${standards}">
                    <option value="${standard.idStandard}">
                        <c:out value="${standard.nameStandard}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="name_standard">Novo name: </label> <input type="text" readonly id="name_standard" name="name_standard" size="30">
            <br>
            <label for="mark_standard">Observação do Norma:</label> <input type="tel" readonly name="mark_standard" id="mark_standard" size="30">
            <br>
            <input type="hidden" name="command" value="Standard">
            <input type="hidden" name="action" value="updateById">
            <input type="button" value="Atualizar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Standard&action=read'" value="Cancelar">
        </form>
        <script>
            function select(event) {
                var name_standard = document.getElementById("name_standard");
                var mark_standard = document.getElementById("mark_standard");
                var mySelect = document.getElementById("mySelect");

                if (mySelect.value !== "") {
            <c:forEach items="${standards}" var="standard" varStatus="status">
                    if (mySelect.value === "${standard.idStandard}") {
                        name_standard.value = "${standard.nameStandard}";
                        name_standard.removeAttribute("readonly");
                        mark_standard.value = "${standard.markStandard}";
                        mark_standard.removeAttribute("readonly");
                    }
            </c:forEach>
                } else {
                    name_standard.value = "";
                    name_standard.setAttribute("readonly");
                    mark_standard.value = "";
                    mark_standard.setAttribute("readonly");
                }
            }
            function myFunction() {
                var name_standard = document.getElementById("name_standard");
                var mark_standard = document.getElementById("mark_standard");
                var mySelect = document.getElementById("mySelect");
                var formUpdate = document.getElementById("formUpdate");

                switch (true) {
                    case mySelect.value === "":
                        mySelect.focus();
                        alert("Selecione uma Norma");
                        break;
                    case name_standard.value === "":
                        name_standard.focus();
                        alert("Digite o Nome da Norma");
                        break;
                    case mark_standard.value === "":
                        mark_standard.focus();
                        alert("Digite a Observação da Norma");
                        break;
                    default:
                        formUpdate.submit();
                        break;
                }
            }


        </script>
    </body>
</html>
