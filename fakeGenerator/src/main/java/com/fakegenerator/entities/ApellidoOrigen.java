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
public class ApellidoOrigen {

    private int id;
    private String apellido;

    public ApellidoOrigen() {
    }

    public ApellidoOrigen(int id, String apellido) {
        this.id = id;
        this.apellido = apellido;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "ApellidoOrigen{" + "id=" + id + ", apellido=" + apellido + '}';
    }

}
