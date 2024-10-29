/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logica;

import java.util.ArrayList;
import com.persistencia.controladoraPersistencia;
import java.util.List;

/**
 *
 * @author usr
 */
public class controladoraLogica {
    
        controladoraPersistencia ctrlPersistencia= new controladoraPersistencia();
        usuario usu=new usuario();
    
    public void crearCliente(cliente clienteNuevo){
        ctrlPersistencia.crearCliente(clienteNuevo);
    }
    public void modificarCliente(cliente clienteCambio){
        ctrlPersistencia.modificarCliente(clienteCambio);
    }
    public void eliminarCliente(int id){
        ctrlPersistencia.eliminarCliente(id);
    }
    public cliente consultarCliente(int id){
        return ctrlPersistencia.consultarCliente(id);
    }
    public List<cliente> consultarListaCliente(){
        return ctrlPersistencia.consultarListaCliente();
    }
    
     public void crearDispositivo(dispositivo device){
        ctrlPersistencia.crearDispositivo(device);
    }
    public void borrarDispositivo(int id){
        ctrlPersistencia.borrarDispositivo(id);
    }
    public void modificarDispositivo(dispositivo device){
        ctrlPersistencia.modificarDispositivo(device);
    }
    public dispositivo consultarDispositivo(int id){
        return ctrlPersistencia.consultarDispositivo(id);
    }
    public ArrayList<dispositivo> consultarListaDispositivos(){
        return ctrlPersistencia.consultarListaDispositivos();
    }
    
    public void crearPedido(pedido pedi){
        ctrlPersistencia.crearPedido(pedi);
    }
    
    public void eliminarPedido(int id){
        ctrlPersistencia.eliminarPedido(id);
    }
    
    public void modificarPedido(pedido pedi){
        ctrlPersistencia.modificarPedido(pedi);
    }
    
    public pedido consultarPedido(int id){
        return ctrlPersistencia.consultarPedido(id);
    }
    
    public ArrayList<pedido> consultarListaPedidos(){
        return ctrlPersistencia.consultarListaPedidos();
    }
    
    public void crearUsuario(usuario user){
        ctrlPersistencia.crearUsuario(user);
    }
    
    public void eliminarUsuario(int id){
        ctrlPersistencia.eliminarUsuario(id);
    }
    
    public void modificarUsuario(usuario user){
        ctrlPersistencia.modificarUsuario(user);
    }
    
    public usuario consultarUsuario(int id){
        return ctrlPersistencia.consultarUsuario(id);
    }
    
    public ArrayList<usuario> consultarListaUsuario(){
        return ctrlPersistencia.consultarListaUsuario();
    }

    public boolean validarIngreso(String username, String pass) {
        boolean ingreso=false;
        
        List<usuario> listaUser = new ArrayList<usuario>();
        listaUser = ctrlPersistencia.consultarListaUsuario();
        
        for(usuario user: listaUser){
            if(usu.getUser().equals(username)){
                if(usu.getContrasena().equals(pass)){
                    ingreso=true;
                }else{ingreso=false;}
            }
        }
        return ingreso;
    }
    
    
}
