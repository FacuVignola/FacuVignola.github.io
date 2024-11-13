/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.logica.cliente;
import com.logica.controladoraLogica;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        
        int clienteModif = Integer.parseInt(request.getParameter("id"));
        
        cliente cli = ctrl.consultarCliente(clienteModif);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        if(cli!=null){
            Map<String, String> clienteMap = new HashMap<>();
            clienteMap.put("id", Integer.toString(cli.getId()));
            clienteMap.put("nombre", cli.getNombre());
            clienteMap.put("apellido", cli.getApellido());
            clienteMap.put("telefono", Long.toString(cli.getTelefono()));
            
            
            String json = new Gson().toJson(clienteMap);
            
            response.getWriter().write(json);
        }
        else
        {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\": \"ID inválido\"}");
        }
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        BufferedReader lector = request.getReader();
        int idclienteModif = Integer.parseInt(request.getParameter("id"));
        Gson gson = new Gson();
        cliente cli = ctrl.consultarCliente(idclienteModif);
        Type tipo = new TypeToken<String[]>() {}.getType();
        String[] datos=gson.fromJson(lector, tipo);
        
        cli.setNombre(datos[0]);
        cli.setApellido(datos[1]);
        cli.setTelefono(Long.parseLong(datos[2]));
        
        try{
            ctrl.modificarCliente(cli);
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": true}");
            
            /*
            HttpSession misesion = request.getSession();
            misesion.setAttribute("ClienteModif", cli);
            response.sendRedirect("ClienteModif.jsp");
            */
        }
        catch(Error e){
            response.setContentType("application/json");
            response.getWriter().write("{\"success\": false}");
        }
        
        
        
        /*
        String nom = request.getParameter("nombre");
        String ape = request.getParameter("apellido");
        Long tel = Long.parseLong(request.getParameter("telefono"));
        
        cliente cli = (cliente) request.getSession().getAttribute("clienteModificar");
        
        cli.setNombre(nom);
        cli.setApellido(ape);
        cli.setTelefono(tel);
        
        ctrl.modificarCliente(cli);
        
        response.sendRedirect("index.jsp");
        */
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
