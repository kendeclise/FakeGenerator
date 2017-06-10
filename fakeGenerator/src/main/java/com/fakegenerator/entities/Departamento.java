/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jbust
 */
@Entity
@Table(name = "departamentos")
public class Departamento {

    @Id
    private int id_depa;
    private String nombre;
    @OneToMany(mappedBy = "departamento")
    private Set<Provincia> provincias;

    public Departamento() {
    }

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public int getId_depa() {
        return id_depa;
    }

    public void setId_depa(int id_depa) {
        this.id_depa = id_depa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(Set<Provincia> provincias) {
        this.provincias = provincias;
    }

    @Override
    public String toString() {
        return "Departamento{" + "id_depa=" + id_depa + ", nombre=" + nombre + '}';
    }
    
    
    

}
