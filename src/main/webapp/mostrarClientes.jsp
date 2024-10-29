<%-- 
    Document   : mostrarClientes
    Created on : 28/10/2024, 22:03:39
    Author     : Tacho
--%>

<%@page import="java.util.List"%>
<%@page import="com.logica.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            List<cliente> listaClientes = (List) request.getSession().getAttribute("listaClientes");
            int cont=1;
            if (listaClientes != null) {
            %>
                <% for(cliente cli : listaClientes) { %>  
                    <option value="<%=cli.getId()%>"><%=cli.toString()%></option>
                <% } %>
            <% 
                } else { 
            %>
                <option value="">No hay clientes disponibles</option>
            <% 
                } 
            %>
    </body>
</html>
