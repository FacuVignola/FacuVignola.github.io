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
import com.logica.usuario;
import javax.servlet.http.HttpSession;

/**
 *
 * @author usr
 */
@WebServlet(name = "svLogin", urlPatterns = {"/svLogin"})
public class svLogin extends HttpServlet {
    
            controladoraLogica ctrl=new controladoraLogica();
    
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
        
        
            String username = request.getParameter("username");
            String pass = request.getParameter("pass");
            
            boolean validacion=false;
            validacion=ctrl.validarIngreso(username,pass);
            
            if(validacion==true){
                HttpSession misesion = request.getSession(true);
                misesion.setAttribute("usuario", username);
                response.sendRedirect("index.jsp");
            }
            else{
                HttpSession error = request.getSession(false);
                error.setAttribute("validacion",validacion);
                response.sendRedirect("login.jsp");
            }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
