<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Cliente - Visualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Clientes&nbsp;&#45;&nbsp;Tabela</h3>
        <table class="rwd-table">
            <tr>
                <th>ID</th>
                <th>NOME</th> 
                <th>TELEFONE</th>
                <th>C.E.P. / NR</th>
                <th>ROTA</th>
            </tr>
            <c:forEach items="${clients}" var="client" varStatus="i">
                <tr>
                    <td data-th="ID">${client.idClient}</td>
                    <td data-th="NOME">${client.nameClient}</td>
                    <td data-th="TELEFONE">+${client.telephone.toString().substring(0,2)} (${client.telephone.toString().substring(2,4)}) ${client.telephone.toString().substring(4,8)}-${client.telephone.toString().substring(8)}</td>
                    <td data-th="C.E.P. / NR">${client.idAddress.getFormattedZipcode()} / ${client.idAddress.numberAddress}</td>
                    <td data-th="ROTA"><a href="home?command=Client&action=route&zipcode=${client.idAddress.zipcode}" >Traçar Rota</a></td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${start!=null && end !=null}">
            <iframe 
                width="600"
                height="450"
                frameborder="0" style="border:0"
                src="https://www.google.com/maps/embed/v1/directions?origin=${end.long_name}
                      &destination=${start.long_name}
                      &key=AIzaSyCCGEQb7tDuF0zBfklIvq34zCkvdRiSWOs">
              </iframe>
        </c:if>
        ${end=null}
        ${start=null}
    </body>
</html>
