/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jbust
 */
@Entity
@Table(name = "provincias")
public class Provincia {

    @Id
    private int id_provi;

    @ManyToOne
    @JoinColumn(name = "id_depa")
    private Departamento departamento;
    private String nombre;
    
    @OneToMany(mappedBy = "provincia")
    private Set<Distrito> distritos;
    
 

    public Provincia() {
    }


    public int getId_provi() {
        return id_provi;
    }

    public void setId_provi(int id_provi) {
        this.id_provi = id_provi;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Distrito> getDistritos() {
        return distritos;
    }

    public void setDistritos(Set<Distrito> distritos) {
        this.distritos = distritos;
    }

    @Override
    public String toString() {
        return "Provincia{" + "id_provi=" + id_provi + ", departamento=" + departamento + ", nombre=" + nombre + '}';
    }

    
    
}
