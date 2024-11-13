/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logica;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author usr
 */
@Entity
public class dispositivo implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    @Basic
    private String tipo;
    private String modelo;
    private String descripcion;
    @ManyToOne
    private cliente dueno;
    

    public dispositivo (){}
    
    public dispositivo(String tipo, String modelo, String descripcion, cliente dueno) {
        this.tipo = tipo;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.dueno = dueno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public cliente getDueno() {
        return dueno;
    }

    public void setDueno(cliente dueno) {
        this.dueno = dueno;
    }
    
    
}
