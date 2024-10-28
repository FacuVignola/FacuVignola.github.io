/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistencia;

import com.logica.cliente;
import com.logica.pedido;
import com.logica.usuario;
import com.logica.dispositivo;
import com.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usr
 */
public class controladoraPersistencia {
    clienteJpaController clienteJpa = new clienteJpaController();
    dispositivoJpaController dispositivoJpa = new dispositivoJpaController();
    pedidoJpaController pedidoJpa = new pedidoJpaController();
    usuarioJpaController usuarioJpa = new usuarioJpaController();

    public void crearCliente(cliente clienteNuevo) {
        clienteJpa.create(clienteNuevo);
    }

    public void modificarCliente(cliente clienteCambio) {
        try {
            clienteJpa.edit(clienteCambio);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarCliente(int id) {
        try {
            clienteJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public cliente consultarCliente(int id) {
        return clienteJpa.findcliente(id);
    }

    public ArrayList<cliente> consultarListaCliente() {
        List<cliente> listacli= clienteJpa.findclienteEntities();
        ArrayList<cliente> listaCliente = new ArrayList<cliente> (listacli);
        return listaCliente;
    }
    
    public void crearDispositivo(dispositivo device) {
        dispositivoJpa.create(device);
    }

    public void borrarDispositivo(int id) {
        try {
            dispositivoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarDispositivo(dispositivo device) {
        try {
            dispositivoJpa.edit(device);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public dispositivo consultarDispositivo(int id) {
        return dispositivoJpa.finddispositivo(id);
    }

    public ArrayList<dispositivo> consultarListaDispositivos() {
        List<dispositivo> devices = dispositivoJpa.finddispositivoEntities();
        ArrayList<dispositivo> listaDispositivo = new ArrayList<dispositivo> (devices);
        return listaDispositivo;
    }
    
    public void crearPedido(pedido pedi) {
        pedidoJpa.create(pedi);
    }

    public void eliminarPedido(int id) {
        try {
            pedidoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarPedido(pedido pedi) {
        try {
            pedidoJpa.edit(pedi);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public pedido consultarPedido(int id) {
        return pedidoJpa.findpedido(id);
    }

    public ArrayList<pedido> consultarListaPedidos() {
        List<pedido> listaP = pedidoJpa.findpedidoEntities();
        ArrayList<pedido> listaPedidos = new ArrayList<pedido>(listaP);
        return listaPedidos;
    }

    public void crearUsuario(usuario user) {
        usuarioJpa.create(user);
    }

    public void eliminarUsuario(int id) {
        try {
            usuarioJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarUsuario(usuario user) {
        try {
            usuarioJpa.edit(user);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public usuario consultarUsuario(int id) {
        return usuarioJpa.findusuario(id);
    }

    public ArrayList<usuario> consultarListaUsuario() {
        List<usuario> listaUser = usuarioJpa.findusuarioEntities();
        ArrayList<usuario> listaUsuario = new ArrayList<usuario>(listaUser);
        return listaUsuario;
    }
    
}
