<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Item Recebimento - Inserir</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Inserir Item Recebimento</h3>
        <form method="POST" id="formInsert" action="home">
            <label for="receipt">Recebimento: </label>
            <select name="receipts" id="receipts" onchange="myFunctionTwo()">
                <option value="">&#45;&#45; Selecione o Nr do Recebimento &#45;&#45;</option>
                <c:forEach var="receipt" items="${receipts}">
                    <option value="${receipt.idReceipt}">
                        <c:out value="${receipt.idReceipt}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="nf_client">Nf Cliente:</label> <input type="text" readonly name="nf_client" id="nf_client" size="10">
            <br>
            <label for="order_client">Pedido Cliente:</label> <input type="text" readonly name="order_client" id="order_client" size="10">
            <br>
            <label for="amount_piece">Quantidade de Peças:</label> <input type="number" readonly name="amount_piece" id="amount_piece" size="10">
            <br>
            <label for="amount_specimen">Quantidade de Corpo de Prova:</label> <input type="number" readonly name="amount_specimen" id="amount_specimen" size="10">
            <br>
            <label for="weight">Peso: </label> <input type="number" readonly name="weight" id="weight" size="10">
            <br>
            <label for="number_transport">Corrida:</label> <input type="number" readonly name="number_transport" id="number_transport" size="10">
            <br>
            <label for="product">Produto:</label>
            <select name="products" id="products" disabled>
                <option value="">&#45;&#45; Selecione o Produto &#45;&#45;</option>
                <c:forEach var="product" items="${products}">
                    <option value="${product.idProduct}">
                        <c:out value="${product.descriptionProduct}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="material">Material:</label>
            <select name="materials" id="materials" disabled>
                <option value="">&#45;&#45; Selecione o Material &#45;&#45;</option>
                <c:forEach var="material" items="${materials}">
                    <option value="${material.idMaterial}">
                        <c:out value="${material.nameMaterial}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="standard">Norma:</label>
            <select name="standards" id="standards" disabled>
                <option value="">&#45;&#45; Selecione a Norma &#45;&#45;</option>
                <c:forEach var="standard" items="${standards}">
                    <option value="${standard.idStandard}">
                        <c:out value="${standard.nameStandard}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="employee">Funcionário:</label>
            <select name="employees" id="employees" disabled>
                <option value="">&#45;&#45; Selecione o Funcionário &#45;&#45;</option>
                <c:forEach var="employee" items="${employees}">
                    <option value="${employee.idEmployee}">
                        <c:out value="${employee.nameEmployee}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="mark_item_receipt">Observação:</label> <input type="text" readonly name="mark_item_receipt" id="mark_item_receipt" size="30">
            <br>
            <label for="date_item_receipt">Data de Recebimento:</label> <input type="date" readonly name="date_item_receipt" id="date_item_receipt" size="20" maxlength="20">
            <br>
            <input type="hidden" name="command" value="ItemsReceipt">
            <input type="hidden" name="action" value="insert.confirm">
            <input type="button" value="Incluir" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=ItemReceipt&action=read'" value="Cancelar">
        </form>
        <script>
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth() + 1;
            var yyyy = today.getFullYear();
            var hh = today.getHours();
            var min = today.getMinutes();

            if (dd < 10) {
                dd = '0' + dd;
            }

            if (mm < 10) {
                mm = '0' + mm;
            }

            if (hh < 10) {
                hh = '0' + hh;
            }

            if (min < 10) {
                min = '0' + min;
            }

            today = dd + '/' + mm + '/' + yyyy + ' ' + hh + ':' + min;
            document.getElementById("date_item_receipt").value = today;

            function myFunction() {
                var receipts = document.getElementById("receipts");
                var nf_client = document.getElementById("nf_client");
                var order_client = document.getElementById("order_client");
                var amount_piece = document.getElementById("amount_piece");
                var amount_specimen = document.getElementById("amount_specimen");
                var weight = document.getElementById("weight");
                var number_transport = document.getElementById("number_transport");
                var products = document.getElementById("products");
                var materials = document.getElementById("materials");
                var standards = document.getElementById("standards");
                var employees = document.getElementById("employees");
                var mark_item_receipt = document.getElementById("mark_item_receipt");
                var formInsert = document.getElementById("formInsert");

                switch (true) {
                    case receipts.value === "":
                        receipts.focus();
                        alert("Selecione um Recebimento");
                        break;
                    case amount_piece.value === "":
                        amount_piece.focus();
                        alert("Digite a Quantidade de Peça");
                        break;
                    case amount_specimen.value === "":
                        amount_specimen.focus();
                        alert("Digite a Quantidade de Corpo de Prova");
                        break;
                    case weight.value === "":
                        weight.focus();
                        alert("Digite o weight");
                        break;
                    case number_transport.value === "":
                        number_transport.focus();
                        alert("Digite a Corrida");
                        break;
                    case products.value === "":
                        products.focus();
                        alert("Selecione um Produto");
                        break;
                    case materials.value === "":
                        materials.focus();
                        alert("Selecione um Material");
                        break;
                    case standards.value === "":
                        standards.focus();
                        alert("Selecione uma Norma");
                        break;
                    case employees.value === "":
                        employees.focus();
                        alert("Selecione um Funcionário");
                        break;
                    default:
                        formInsert.submit();
                        break;
                }
            }
            function myFunctionTwo() {
                var receipts = document.getElementById("receipts");
                var nf_client = document.getElementById("nf_client");
                var order_client = document.getElementById("order_client");
                var amount_piece = document.getElementById("amount_piece");
                var amount_specimen = document.getElementById("amount_specimen");
                var weight = document.getElementById("weight");
                var number_transport = document.getElementById("number_transport");
                var products = document.getElementById("products");
                var materials = document.getElementById("materials");
                var standards = document.getElementById("standards");
                var employees = document.getElementById("employees");
                var mark_item_receipt = document.getElementById("mark_item_receipt");
                var formInsert = document.getElementById("formInsert");

                switch (true) {
                    case receipts.value !== "":
                        nf_client.removeAttribute("readonly");
                        order_client.removeAttribute("readonly");
                        amount_piece.removeAttribute("readonly");
                        amount_specimen.removeAttribute("readonly");
                        weight.removeAttribute("readonly");
                        number_transport.removeAttribute("readonly");
                        products.removeAttribute("disabled");
                        materials.removeAttribute("disabled");
                        standards.removeAttribute("disabled");
                        employees.removeAttribute("disabled");
                        mark_item_receipt.removeAttribute("readonly");
                        break;
                    case receipts.value === "":
                        nf_client.value = "";
                        nf_client.setAttribute("readonly");
                        order_client.value = "";
                        order_client.setAttribute("readonly");
                        amount_piece.value = "";
                        amount_piece.setAttribute("readonly");
                        amount_specimen.value = "";
                        amount_specimen.setAttribute("readonly");
                        weight.value = "";
                        weight.setAttribute("readonly");
                        number_transport.value = "";
                        number_transport.setAttribute("readonly");
                        products.value = "";
                        products.setAttribute("disabled");
                        materials.value = "";
                        materials.setAttribute("disabled");
                        standards.value = "";
                        standards.setAttribute("disabled");
                        employees.value = "";
                        employees.setAttribute("disabled");
                        mark_item_receipt.value = "";
                        mark_item_receipt.setAttribute("readonly");
                        break;
                }
            }

        </script>
    </body>
</html>
