/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.entities;

/**
 *
 * @author jbust
 */
public class DistritoOrigen {

    private int id;
    private String nombre;
    private int id_prov;

    public DistritoOrigen() {
    }

    public DistritoOrigen(int id, String nombre, int id_prov) {
        this.id = id;
        this.nombre = nombre;
        this.id_prov = id_prov;
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

    public int getId_prov() {
        return id_prov;
    }

    public void setId_prov(int id_prov) {
        this.id_prov = id_prov;
    }

    @Override
    public String toString() {
        return "DistritoOrigen{" + "id=" + id + ", nombre=" + nombre + ", id_prov=" + id_prov + '}';
    }

    
}
