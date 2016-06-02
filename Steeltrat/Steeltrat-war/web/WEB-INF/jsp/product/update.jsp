<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Produto - Atualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <script src="js/validation.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Atualizar Produto</h3>
        <form method="POST" id="formUpdate" action="home">
            <label for="products">Produtos:&nbsp;</label>
            <select name="products" id="mySelect" onchange="select.call(this, event)" >
                <option value="">&#45;&#45;&nbsp;Selecione o Produto&nbsp;&#45;&#45;</option>
                <c:forEach var="product" items="${products}">
                    <option value="${product.idProduct}">
                        <c:out value="${product.descriptionProduct}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="description_product">Nova descrição:&nbsp;</label> <input type="text" readonly id="description_product" name="description_product" size="50" maxlength="50">
            <br>
            <label for="price">Preço do Produto:&nbsp;</label> <input type="text" readonly name="price" id="price" size="10" maxlength="10" onkeypress="return alpha(event,numbers)">
            <br>
            <input type="hidden" name="command" value="Product">
            <input type="hidden" name="action" value="updateById">
            <input type="button" value="Atualizar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=Product&action=read'" value="Cancelar">
        </form>
        <script>
            function select(event) {
                var description_product = document.getElementById("description_product");
                var price = document.getElementById("price");
                var mySelect = document.getElementById("mySelect");

                if (mySelect.value !== "") {
            <c:forEach items="${products}" var="product" varStatus="status">
                    if (mySelect.value === "${product.idProduct}") {
                        description_product.value = "${product.descriptionProduct}";
                        description_product.removeAttribute("readonly");
                        price.value = "${product.price}";
                        price.removeAttribute("readonly");
                    }
            </c:forEach>
                } else {
                    description_product.value = "";
                    description_product.setAttribute("readonly");
                    price.value = "";
                    price.setAttribute("readonly");
                }
            }
            function myFunction() {
                var description_product = document.getElementById("description_product");
                var price = document.getElementById("price");
                var mySelect = document.getElementById("mySelect");
                var formUpdate = document.getElementById("formUpdate");

                switch (true) {
                    case mySelect.value === "":
                        mySelect.focus();
                        alert("Selecione um Produto");
                        break;
                    case description_product.value === "":
                        description_product.focus();
                        alert("Digite a Descrição do Produto");
                        break;
                    case price.value === "":
                        price.focus();
                        alert("Digite o Preço do Produto");
                        break;
                    default:
                        formUpdate.submit();
                        break;
                }
            }


        </script>
    </body>
</html>
