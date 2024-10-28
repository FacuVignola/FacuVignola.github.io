/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Servlet;

import com.logica.controladoraLogica;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usr
 */
@WebServlet(name = "svClienteDestroy", urlPatterns = {"/svClienteDestroy"})
public class svClienteDestroy extends HttpServlet {
    controladoraLogica ctrl = new controladoraLogica();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idBorrar = Integer.parseInt(request.getParameter("idClienteDestroy"));
        ctrl.eliminarCliente(idBorrar) ;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
