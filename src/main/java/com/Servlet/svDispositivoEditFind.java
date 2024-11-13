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
import com.logica.dispositivo;
import com.logica.pedido;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FaQ
 */
@WebServlet(name = "svDispositivoEditFind", urlPatterns = {"/svDispositivoEditFind"})
public class svDispositivoEditFind extends HttpServlet {
    controladoraLogica ctrl = new controladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        }
    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int idDisp = Integer.parseInt(request.getParameter("id"));
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs= null;
        
        String auxid = Integer.toString(idDisp);
        
        
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/practicasdb?serverTimezone=UTC", "root", "");
            
            String sql = "SELECT c.id AS cliente_id, c.nombre, c.apellido, d.id AS disp_id, d.tipo, d.modelo, d.descripcion, p.id AS pedido_id, p.estado "+
                         "FROM dispositivo d "+
                         "JOIN cliente c ON d.dueno_id = c.id "+
                         "JOIN pedido p ON d.id = p.id_dispositivo_id "+
                         "WHERE d.id = "+ auxid;
            
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            List<Map<String, Object>> listaDatos = new ArrayList<>();
            
            while(rs.next()){
                Map<String,Object> datosMap = new HashMap<>();
                datosMap.put("tipo", rs.getString("tipo"));
                datosMap.put("modelo", rs.getString("modelo"));
                datosMap.put("descripcion", rs.getString("descripcion"));
                datosMap.put("estado", rs.getString("estado"));
                datosMap.put("nombre", rs.getString("nombre"));
                datosMap.put("apellido", rs.getString("apellido"));
                datosMap.put("cliente_id", rs.getInt("cliente_id"));
                datosMap.put("disp_id", rs.getInt("disp_id"));
                datosMap.put("pedido_id", rs.getInt("pedido_id"));
                
                listaDatos.add(datosMap);
            }
            
            String json= new Gson().toJson(listaDatos);
            response.getWriter().write(json);
            
        }
        catch (Exception e)
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error en la base de datos\"}");
            e.printStackTrace();
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
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        BufferedReader lector = request.getReader();
        int idDispoModif = Integer.parseInt(request.getParameter("id"));
        Gson gson= new Gson();
        dispositivo dispo =ctrl.consultarDispositivo(idDispoModif);
        Type tipo = new TypeToken<String[]>() {}.getType();
        String[] datos=gson.fromJson(lector, tipo);
        
        dispo.setTipo(datos[0]);
        dispo.setModelo(datos[1]);
        dispo.setDescripcion(datos[2]);
        cliente cli=ctrl.consultarCliente(Integer.parseInt(datos[3]));
        dispo.setDueno(cli);
        pedido ped=ctrl.consultarPedido(Integer.parseInt(datos[4]));
        ped.setEstado(datos[5]);
        
        
        try{
            ctrl.modificarDispositivo(dispo);
            ctrl.modificarPedido(ped);
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
        
        
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
