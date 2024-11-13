/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.logica.controladoraLogica;
import com.logica.dispositivo;
import com.logica.pedido;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FaQ
 */
@WebServlet(name = "svDispositivoDestroy", urlPatterns = {"/svDispositivoDestroy"})
public class svDispositivoDestroy extends HttpServlet {
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
        int idDisp = Integer.parseInt(request.getParameter("id"));
        
        BufferedReader lector = request.getReader();
        Gson gson= new Gson();
        Type tipo = new TypeToken<String[]>() {}.getType();
        String[] datos=gson.fromJson(lector, tipo);
        
        pedido ped=ctrl.consultarPedido(Integer.parseInt(datos[4]));
        
        try{
            ctrl.borrarDispositivo(idDisp);
            ctrl.eliminarPedido(ped.getId());
            
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": true}");
        }
        catch(Exception e)
        {
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false}");
            e.printStackTrace();
        }
        
    }

        @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
