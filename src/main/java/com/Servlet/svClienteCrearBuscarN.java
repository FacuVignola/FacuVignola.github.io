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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
import java.io.BufferedReader;

/**
 *
 * @author Tacho
 */
@WebServlet(name = "svClienteCrearBuscarN", urlPatterns = {"/svClienteCrearBuscarN"})
public class svClienteCrearBuscarN extends HttpServlet {
    controladoraLogica ctrl = new controladoraLogica();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<cliente> listaClientes = new ArrayList<>();
        
        listaClientes = ctrl.consultarListaCliente();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        out.print(gson.toJson(listaClientes));
        out.flush();
        
        /*
        HttpSession misesion = request.getSession();
        misesion.setAttribute("listaClientes", listaClientes);
        
        if (listaClientes == null || listaClientes.isEmpty()) {
            System.out.println("La lista de clientes está vacía o es nula.");
        } else {
            System.out.println("La lista de clientes contiene " + listaClientes.size() + " elementos.");
        }
        
        response.sendRedirect("index.jsp");
        */
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader lector = request.getReader();
        Gson gson = new Gson();
        cliente cli = gson.fromJson(lector, cliente.class);
        
        ctrl.crearCliente(cli);
        
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
