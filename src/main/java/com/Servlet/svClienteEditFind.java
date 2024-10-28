/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Servlet;

import com.logica.cliente;
import com.logica.controladoraLogica;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author usr
 */
@WebServlet(name = "svClienteEditFind", urlPatterns = {"/svClienteEditFind"})
public class svClienteEditFind extends HttpServlet {
    controladoraLogica ctrl = new controladoraLogica();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int clienteModif = Integer.parseInt(request.getParameter("idClienteEdit"));
            
            cliente cli = ctrl.consultarCliente(clienteModif);
            
            HttpSession misesion = request.getSession();
            misesion.setAttribute("clienteModificar",cli);
            response.sendRedirect("modificarCliente");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String nom = request.getParameter("nombre");
        String ape = request.getParameter("apellido");
        int tel = Integer.parseInt(request.getParameter("telefono"));
        
        cliente cli = (cliente) request.getSession().getAttribute("clienteModificar");
        
        cli.setNombre(nom);
        cli.setApellido(ape);
        cli.setTelefono(tel);
        
        ctrl.modificarCliente(cli);
        
        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
