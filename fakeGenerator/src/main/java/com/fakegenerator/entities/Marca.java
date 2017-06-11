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
@Table(name = "marcas")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_marca;
    
    private String nom_marca;
    private String descr_marca;
    private String ubicacion_dir;
    
    @OneToMany(mappedBy = "marca")//hace referencia a la variable relacionada en la otra tabla
    private Set<Producto> productos;

    public Marca() {
    }

    public Marca(String nom_marca, String descr_marca, String ubicacion_dir) {
        this.nom_marca = nom_marca;
        this.descr_marca = descr_marca;
        this.ubicacion_dir = ubicacion_dir;
    }
    
    

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getNom_marca() {
        return nom_marca;
    }

    public void setNom_marca(String nom_marca) {
        this.nom_marca = nom_marca;
    }

    public String getDescr_marca() {
        return descr_marca;
    }

    public void setDescr_marca(String descr_marca) {
        this.descr_marca = descr_marca;
    }

    public String getUbicacion_dir() {
        return ubicacion_dir;
    }

    public void setUbicacion_dir(String ubicacion_dir) {
        this.ubicacion_dir = ubicacion_dir;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Marca{" + "id_marca=" + id_marca + ", nom_marca=" + nom_marca + ", descr_marca=" + descr_marca + ", ubicacion_dir=" + ubicacion_dir + '}';
    }

   

    
    
    
    
}
