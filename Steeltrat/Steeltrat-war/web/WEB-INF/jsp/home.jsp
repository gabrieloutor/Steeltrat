<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Steeltrat | Home</title>
        <link rel="icon" href="../../../img/favicon.ico" />
        <link href='css/home.css' rel='stylesheet'>
    </head>
    <body>
        <c:if test="${resultado==true}">
            <%@include file="section.jspf" %>
            <%@include file="menu.jspf" %>
        </c:if>
    </body>
</html>
