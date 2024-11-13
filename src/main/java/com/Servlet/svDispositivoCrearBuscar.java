/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.logica.controladoraLogica;
import com.logica.dispositivo;
import com.logica.cliente;
import com.logica.pedido;
import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author usr
 */
@WebServlet(name = "svDispositivoCrearBuscar", urlPatterns = {"/svDispositivoCrearBuscar"})
public class svDispositivoCrearBuscar extends HttpServlet {
        controladoraLogica ctrl = new controladoraLogica();

    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/practicasdb?serverTimezone=UTC", "root", "");
            
            String sql = "SELECT d.id, d.tipo, d.modelo, d.descripcion, c.id AS cliente_id, c.nombre, c.apellido, p.ID AS pedido_id " +
                         "FROM dispositivo d " +
                         "JOIN cliente c ON d.dueno_id = c.id "+
                         "JOIN pedido p ON d.id = p.ID_DISPOSITIVO_ID";
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            List<Map<String, Object>> listaDispositivos = new ArrayList<>();
            
            while (rs.next()) {
                Map<String, Object> dispositivoMap = new HashMap<>();
                dispositivoMap.put("id", rs.getInt("id"));
                dispositivoMap.put("tipo", rs.getString("tipo"));
                dispositivoMap.put("modelo", rs.getString("modelo"));
                dispositivoMap.put("descripcion", rs.getString("descripcion"));
                dispositivoMap.put("cliente_id", rs.getInt("cliente_id"));
                dispositivoMap.put("nombre", rs.getString("nombre"));
                dispositivoMap.put("apellido", rs.getString("apellido"));
                dispositivoMap.put("pedido_id", rs.getString("pedido_id"));

                listaDispositivos.add(dispositivoMap);
            }
            
            Gson gson = new Gson();
            String json = gson.toJson(listaDispositivos);
            
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            
        } 
        catch(Exception e)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error en la base de datos\"}");
        }
        finally
        {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        /*
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        
        List<dispositivo> listaDispositivos = new ArrayList<>();
        
        listaDispositivos = ctrl.consultarListaDispositivos();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        out.print(gson.toJson(listaDispositivos));
        out.flush();
        */
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        
            BufferedReader lector = request.getReader();
            Gson gson = new Gson();
            dispositivo disp = new dispositivo();
            Type tipo = new TypeToken<String[]>() {}.getType();
            String[] datos=gson.fromJson(lector, tipo);
            
            disp.setTipo(datos[0]);
            disp.setModelo(datos[1]);
            disp.setDescripcion(datos[2]);
            int aux = Integer.parseInt(datos[3]);
            
            disp.setDueno(ctrl.consultarCliente(aux));
            
            ctrl.crearDispositivo(disp);
            
            pedido ped=new pedido();
            ped.setFecha(new Date());
            ped.setEstado("A Reparar");
            ped.setId_dispositivo(disp);
            
            ctrl.crearPedido(ped);
            
            //cliente cli=ctrl.consultarCliente(disp.getDueno().getId());
            
            
            
            
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
