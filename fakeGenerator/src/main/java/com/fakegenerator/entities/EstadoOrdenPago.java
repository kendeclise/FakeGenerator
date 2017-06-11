/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jbust
 */
@Entity
@Table(name = "estado_orden_pago")
public class EstadoOrdenPago {

    @Id
    private int id;
    private String nombre;
    private String descr;

    @OneToMany(mappedBy = "estadoOrdenPago")//hace referencia a la variable relacionada en la otra tabla
    private Set<OrdenPago> ordenesPago;
    
    public EstadoOrdenPago() {
    }

    public EstadoOrdenPago(int id, String nombre, String descr) {
        this.id = id;
        this.nombre = nombre;
        this.descr = descr;
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

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Set<OrdenPago> getOrdenesPago() {
        return ordenesPago;
    }

    public void setOrdenesPago(Set<OrdenPago> ordenesPago) {
        this.ordenesPago = ordenesPago;
    }

    
    
    @Override
    public String toString() {
        return "EstadoOrdenPago{" + "id=" + id + ", nombre=" + nombre + ", descr=" + descr + '}';
    }

}
