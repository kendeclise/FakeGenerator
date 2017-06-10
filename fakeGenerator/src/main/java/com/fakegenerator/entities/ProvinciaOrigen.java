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
public class ProvinciaOrigen {

    private int id;
    private String nombre;
    private int id_depa;

    public ProvinciaOrigen() {
    }

    public ProvinciaOrigen(int id, String nombre, int id_depa) {
        this.id = id;
        this.nombre = nombre;
        this.id_depa = id_depa;
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

    public int getId_depa() {
        return id_depa;
    }

    public void setId_depa(int id_depa) {
        this.id_depa = id_depa;
    }

    @Override
    public String toString() {
        return "ProvinciaOrigen{" + "id=" + id + ", nombre=" + nombre + ", id_depa=" + id_depa + '}';
    }
    
    

    
}
