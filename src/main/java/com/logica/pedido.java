/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logica;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author usr
 */
@Entity
public class pedido implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic
    private String estado;
    @OneToOne
    private dispositivo id_dispositivo;
    
    
    public pedido (){}

    public pedido(Date fecha, String estado, dispositivo id_dispositivo) {
        this.fecha = fecha;
        this.estado = estado;
        this.id_dispositivo = id_dispositivo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public dispositivo getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(dispositivo id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }
    
    
    
}
