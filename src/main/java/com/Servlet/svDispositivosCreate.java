/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.logica.controladoraLogica;

/**
 *
 * @author usr
 */
@WebServlet(name = "svDispositivosCreate", urlPatterns = {"/svDispositivosCreate"})
public class svDispositivosCreate extends HttpServlet {
    controladoraLogica ctrl=new controladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipoDis=request.getParameter("tipoDis");
        String mod = request.getParameter("mode");
        String Desc = request.getParameter("desc");
        String due = request.getParameter("dueño");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
