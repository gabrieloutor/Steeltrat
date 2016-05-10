<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Steeltrat | Cliente - Visualizar</title>
        <link rel="icon" href="img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="../section.jspf" %>
        <%@include file="../menu.jspf" %>
        <h3>Clientes&nbsp;&#45;&nbsp;Operações</h3>
        <div id="returnMsgSuccessfully">${returnMsgSuccessfully}</div>
        ${returnMsgSuccessfully=null}
        <div id="returnMsgError">${returnMsgError}</div>
        ${returnMsgError=null}
        <table style="width:100%">
            <tr>
                <td>ID</td>
                <td>NOME</td> 
                <td>TELEFONE</td>
                <td>C.E.P. / NR</td>
                <td>ROTA</td>
            </tr>
            <c:forEach items="${clients}" var="client" varStatus="i">
                <tr>
                    <td>${client.idClient}</td>
                    <td>${client.nameClient}</td>
                    <td>${client.telephone}</td>
                    <td>${client.idAddress.zipcode} / ${client.idAddress.numberAddress}</td>
                    <td><a href="home?command=Client&action=route&zipcode=${client.idAddress.zipcode}" >Traçar Rota</a></td>
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
