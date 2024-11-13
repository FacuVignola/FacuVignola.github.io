<%-- 
    Document   : ClienteModif
    Created on : 07/11/2024, 22:52:10
    Author     : Tacho
--%>

<%@page import="com.logica.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%cliente cli = (cliente) request.getSession().getAttribute("ClienteModif");%>
        <label><%=cli.getId()%></label>
        <label><%=cli.getNombre()%> <label>
        <label><%=cli.getApellido()%></label>
        <label><%=cli.getTelefono()%></label>
        
    </body>
</html>
