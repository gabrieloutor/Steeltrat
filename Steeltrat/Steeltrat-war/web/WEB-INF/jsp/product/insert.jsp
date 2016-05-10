<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Produto - Inserir</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Inserir Produto</h3>
        <form method="POST" id="formInsert" action="home" >
            <label for="description_product">Descrição:&nbsp;</label> <input type="text" name="description_product" id="description_product" onchange="myFunctionTwo()" size="50" maxlength="50">
            <br>
            <label for="price">Preço:&nbsp;</label> <input type="number" readonly name="price" id="price" size="10" maxlength="10">
            <br>
            <input type="hidden" name="command" value="Product">
            <input type="hidden" name="action" value="insert.confirm">
            <input type="button" value="Incluir" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Product&action=read'" value="Cancelar">
        </form>
        <script>
            function myFunction() {
                var description_product = document.getElementById("description_product");
                var price = document.getElementById("price");
                var formInsert = document.getElementById("formInsert");

                switch (true) {
                    case description_product.value === "":
                        description_product.focus();
                        alert("Digite a Descrição do Produto");
                        break;
                    case price.value === "":
                        price.focus();
                        alert("Digite o Preço do Produto");
                        break;
                    default:
                        formInsert.submit();
                        break;
                }
            }
            function myFunctionTwo() {
                var description_product = document.getElementById("description_product");
                var price = document.getElementById("price");

                switch (true) {
                    case description_product.value !== "":
                        price.removeAttribute("readonly");
                        break;
                    case description_product.value === "":
                        price.value = "";
                        price.setAttribute("readonly");
                        break;
                }
            }

        </script>
    </body>
</html>
