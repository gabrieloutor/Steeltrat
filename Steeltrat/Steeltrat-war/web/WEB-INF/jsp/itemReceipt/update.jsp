<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Item Recebimento - Atualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <script src="js/validation.js" type="text/javascript"></script>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Atualizar Item Recebimento</h3>
        <form method="POST" id="formUpdate" action="home">
            <label for="receipt">Recebimento: </label>
            <select name="receipts" id="receipts" onchange="select.call(this, event)" >
                <option value="">&#45;&#45; Selecione o Nr do Recebimento &#45;&#45;</option>
                <c:forEach var="receipt" items="${receipts}">
                    <option value="${receipt.idReceipt}">
                        <c:out value="${receipt.idReceipt}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="itemReceipt">Item do Recebimento: </label>
            <select name="itemReceipts" id="itemReceipts" disabled onchange="myFunctionTwo()">
                <option value="">&#45;&#45; Selecione o Nr do Item &#45;&#45;</option>
            </select>
            <br>
            <label for="nf_client">Nf Cliente:</label> <input type="text" readonly name="nf_client" id="nf_client" size="15" maxlength="15">
            <br>
            <label for="order_client">Pedido Cliente:</label> <input type="text" readonly name="order_client" id="order_client" size="25" maxlength="25">
            <br>
            <label for="amount_piece">Quantidade de Pe�as:</label> <input type="text" readonly name="amount_piece" id="amount_piece" size="10" maxlength="5" onkeypress="return alpha(event,numbers)">
            <br>
            <label for="amount_specimen">Quantidade de Corpo de Prova:</label> <input type="text" readonly name="amount_specimen" id="amount_specimen" size="5" maxlength="10" onkeypress="return alpha(event,numbers)">
            <br>
            <label for="weight">Peso:</label> <input type="text" readonly name="weight" id="weight" size="10" maxlength="5" onkeypress="return alpha(event,weight)">
            <br>
            <label for="number_transport">Corrida:</label> <input type="text" readonly name="number_transport" id="number_transport" size="10" maxlength="5" onkeypress="return alpha(event,numbers)">
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
            <label for="employee">Funcion�rio:</label>
            <select name="employees" id="employees" disabled>
                <option value="">&#45;&#45; Selecione o Funcion�rio &#45;&#45;</option>
                <c:forEach var="employee" items="${employees}">
                    <option value="${employee.idEmployee}">
                        <c:out value="${employee.nameEmployee}" />
                    </option>
                </c:forEach>
            </select>
            <br>
            <label for="mark_item_receipt">Observa��o:</label> <textarea name="mark_item_receipt" id="mark_item_receipt" readonly maxlength="150" size="150" ></textarea>
            <br>
            <label for="date_item_receipt">Data de Recebimento:</label> <input type="date" readonly name="date_item_receipt" id="date_item_receipt" size="20" maxlength="20">
            <br>
            <input type="hidden" name="command" value="ItemsReceipt">
            <input type="hidden" name="action" value="updateById">
            <input type="button" value="Atualizar" onclick="myFunction()">
            <br>
            <input type="button" onClick="location.href = 'home?command=ItemReceipt&action=read'" value="Cancelar">
        </form>
        <script>
            function select(event) {
                var receipts = document.getElementById("receipts");
                var itemReceipts = document.getElementById("itemReceipts");
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
                var data = document.getElementById("date_item_receipt");

                itemReceipts.options.length = 0;
                var opt = document.createElement('option');
                opt.value = "";
                opt.innerHTML = "-- Selecione o Nr do Item --";
                itemReceipts.appendChild(opt);

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
                itemReceipts.setAttribute("disabled");
                itemReceipts.value = "";
                data.setAttribute("readonly");
                data.value = "";
                if (receipts.value !== "") {
                    itemReceipts.removeAttribute("disabled");
                    itemReceipts.value = "";
            <c:forEach items="${receipts}" var="receipt" varStatus="status">
                    if (receipts.value === "${receipt.idReceipt}") {
                        i = 0;
                <c:forEach var="itemReceipt" items="${itemReceipts}">
                        if (receipts.value === "${itemReceipt.idReceipt.idReceipt}") {
                            i++;
                            opt = document.createElement('option');
                            opt.value = ${itemReceipt.idItemReceipt};
                            opt.innerHTML = i;
                            itemReceipts.appendChild(opt);
                        }
                </c:forEach>
                    }
            </c:forEach>
                } else {
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
                    itemReceipts.setAttribute("disabled");
                    itemReceipts.value = "";
                    data.setAttribute("readonly");
                    data.value = "";
                }
            }
            function myFunctionTwo() {
                var itemReceipts = document.getElementById("itemReceipts");
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
                var data = document.getElementById("date_item_receipt");

                switch (true) {
                    case itemReceipts.value !== "":
            <c:forEach var="itemReceipt" items="${itemReceipts}">
                        if (itemReceipts.value === "${itemReceipt.idItemReceipt}") {
                            nf_client.value = "${itemReceipt.nfClient}";
                            nf_client.removeAttribute("readonly");
                            order_client.value = "${itemReceipt.orderClient}";
                            order_client.removeAttribute("readonly");
                            amount_piece.value = "${itemReceipt.amountPiece}";
                            amount_piece.removeAttribute("readonly");
                            amount_specimen.value = "${itemReceipt.amountSpecimen}";
                            amount_specimen.removeAttribute("readonly");
                            weight.value = "${itemReceipt.weight}";
                            weight.removeAttribute("readonly");
                            number_transport.value = "${itemReceipt.numberTransport}";
                            number_transport.removeAttribute("readonly");
                            products.value = "${itemReceipt.idProduct.idProduct}";
                            products.removeAttribute("disabled");
                            materials.value = "${itemReceipt.idMaterial.idMaterial}";
                            materials.removeAttribute("disabled");
                            standards.value = "${itemReceipt.idStandard.idStandard}";
                            standards.removeAttribute("disabled");
                            employees.value = "${itemReceipt.idEmployee.idEmployee}";
                            employees.removeAttribute("disabled");
                            mark_item_receipt.value = "${itemReceipt.markItemReceipt}";
                            mark_item_receipt.removeAttribute("readonly");
                            data.value = "${itemReceipt.getFormattedDate()}";
                            break;
                        }
            </c:forEach>
                    case itemReceipts.value === "":
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
                        data.value = "";
                        break;
                }
            }


            function myFunction() {
                var receipts = document.getElementById("receipts");
                var itemReceipts = document.getElementById("itemReceipts");
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
                var formUpdate = document.getElementById("formUpdate");

                switch (true) {
                    case receipts.value === "":
                        receipts.focus();
                        alert("Selecione um Recebimento");
                        break;
                    case itemReceipts.value === "":
                        itemReceipts.focus();
                        alert("Selecione um Item do Recebimnto");
                        break;
                    case amount_piece.value === "":
                        amount_piece.focus();
                        alert("Digite a Quantidade de Pe�a");
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
                        alert("Selecione um Funcion�rio");
                        break;
                    default:
                        formUpdate.submit();
                        break;
                }
            }


        </script>
    </body>
</html>
