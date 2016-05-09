<%-- 
    Document   : Maps
    Created on : 29/04/2016, 09:38:02
    Author     : GabrielOutor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mapas</title>
    </head>
    <body>
        <iframe 
            width="600"
            height="450"
            frameborder="0" style="border:0"
            src="https://www.google.com/maps/embed/v1/directions?origin=${end.long_name}
                  &destination=${start.long_name}
                  &key=AIzaSyCCGEQb7tDuF0zBfklIvq34zCkvdRiSWOs">
          </iframe>
    </body>
</html>
