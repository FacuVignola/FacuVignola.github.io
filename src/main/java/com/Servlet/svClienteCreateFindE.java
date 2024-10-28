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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
@WebServlet(name = "svClienteCreateFind", urlPatterns = {"/svClienteCreateFind"})
public class svClienteCreateFindE extends HttpServlet {

    controladoraLogica ctrl = new controladoraLogica();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<cliente> listaClientes= new ArrayList<>();
        
        listaClientes = ctrl.consultarListaCliente();
        
         HttpSession misesion =request.getSession();
         misesion.setAttribute("listaCliente", listaClientes);
         response.sendRedirect("MostrarCliente.jsp");
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nombre");
        String ape = request.getParameter("apellido");
        int tel = Integer.parseInt(request.getParameter("telefono"));
        
        cliente cli = new cliente();
        
        cli.setNombre(nom);
        cli.setApellido(ape);
        cli.setTelefono(tel);
        
        ctrl.crearCliente(cli);
        
        response.sendRedirect("index.jsp");
    }
    

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
