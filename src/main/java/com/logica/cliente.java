/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author usr
 */
@Entity
public class cliente implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    @Basic
    private String nombre;
    private String apellido;
    private int telefono;
    @OneToMany (mappedBy="dueño")
    private List<dispositivo> listaDispositivos;
    
    

    public cliente (){}

    public cliente(String nombre, String apellido, int telefono, List<dispositivo> listaDispositivos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.listaDispositivos = listaDispositivos;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public List<dispositivo> getListaDispositivos() {
        return listaDispositivos;
    }

    public void setListaDispositivos(List<dispositivo> listaDispositivos) {
        this.listaDispositivos = listaDispositivos;
    }
    
    
}
