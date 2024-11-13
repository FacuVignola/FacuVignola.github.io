/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Servlet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FaQ
 */
@WebServlet(name = "svLista", urlPatterns = {"/svLista"})
public class svLista extends HttpServlet {
private static final long serialVersionUID = 1L;
    
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
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/practicasdb?serverTimezone=UTC", "root", "");
            
            String sql ="SELECT c.nombre, c.apellido, c.telefono, d.tipo, d.modelo, d.descripcion, p.estado "+
                         "FROM cliente c "+
                         "JOIN dispositivo d ON c.id = d.dueno_id "+
                         "JOIN pedido p ON d.ID = p.ID_DISPOSITIVO_ID";
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            List<Map<String, Object>> listadoTablas = new ArrayList<>();
            
            while(rs.next()){
                Map<String, Object> listadoMap = new HashMap<>();
                listadoMap.put("nombre", rs.getString("nombre"));
                listadoMap.put("apellido", rs.getString("apellido"));
                listadoMap.put("telefono", rs.getLong("telefono"));
                listadoMap.put("tipo", rs.getString("tipo"));
                listadoMap.put("modelo", rs.getString("modelo"));
                listadoMap.put("descripcion", rs.getString("descripcion"));
                listadoMap.put("estado", rs.getString("estado"));
                listadoTablas.add(listadoMap);
            }
            
            Gson gson = new Gson();
            String json = gson.toJson(listadoTablas);
            
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
        }
        catch(Exception e){
            
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error en la base de datos\"}");
            e.printStackTrace();
            
        }
        finally{
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
        
             EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyectoPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Consulta JPQL para obtener los datos de cliente, dispositivo y pedido
            String consulta = "SELECT c.nombre, c.apellido, c.telefono, d.tipo, d.descripcion, p.estado"+
                              "FROM cliente c"+
                              "JOIN dispositivo d ON c.id = d.dueno_id"+
                              "JOIN pedido p ON d.ID = p.ID_DISPOSITIVO_ID";
            
            System.out.println(consulta);
            TypedQuery<Object[]> query = em.createQuery(consulta, Object[].class);
            List<Object[]> resultados = query.getResultList();
            
            
            List<Map<String, Object>> listaPedidos = new ArrayList();
            
            
            
            JsonArray listaPedidos = new JsonArray();
            for (Object[] resultado : resultados) {
                JsonObject listaJson = new JsonObject();
                listaJson.addProperty("nombre", (String) resultado[0]);
                listaJson.addProperty("apellido", (String) resultado[1]);
                listaJson.addProperty("telefono", (Long) resultado[2]);
                listaJson.addProperty("dispositivo", (String) resultado[3]);
                listaJson.addProperty("descripcion", (String) resultado[4]);
                listaJson.addProperty("estado", (String) resultado[5]);
                listaPedidos.add(listaJson);
            }
            
            // Enviar el JsonArray como respuesta
            response.setContentType("application/json");
            response.getWriter().write(listaPedidos.toString());
        } finally {
            em.close();
            emf.close();
        }
        */
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
