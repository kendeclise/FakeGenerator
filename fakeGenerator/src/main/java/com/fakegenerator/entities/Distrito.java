/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "distritos")
public class Distrito {
    @Id
    private int id_distri;
    
    @ManyToOne
    @JoinColumn(name = "id_provi")
    private Provincia provincia;
    
    private String nombre;
    
    @OneToMany(mappedBy = "distrito")//hace referencia a la variable relacionada en la otra tabla
    private Set<Cliente> clientes;
    
    @OneToMany(mappedBy = "distrito")//hace referencia a la variable relacionada en la otra tabla
    private Set<OrdenPago> ordenesPago;

    public Distrito() {
    }

   

    public int getId_distri() {
        return id_distri;
    }

    public void setId_distri(int id_distri) {
        this.id_distri = id_distri;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Set<OrdenPago> getOrdenesPago() {
        return ordenesPago;
    }

    public void setOrdenesPago(Set<OrdenPago> ordenesPago) {
        this.ordenesPago = ordenesPago;
    }
    
    

    @Override
    public String toString() {
        return "Distrito{" + "id_distri=" + id_distri + ", provincia=" + provincia + ", nombre=" + nombre + '}';
    }
    
    
    
    
}

