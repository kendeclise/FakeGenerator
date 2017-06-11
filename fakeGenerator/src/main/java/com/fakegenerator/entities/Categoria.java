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
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cate;
    private String nom_cate;
    private String descr_cate;
    
    @OneToMany(mappedBy = "categoria")//hace referencia a la variable relacionada en la otra tabla
    private Set<Producto> productos;

    public Categoria() {
    }

    public Categoria(String nom_cate, String descr_cate) {
        this.nom_cate = nom_cate;
        this.descr_cate = descr_cate;
    }
    
    

    public Categoria(int id_cate, String nom_cate, String descr_cate) {
        this.id_cate = id_cate;
        this.nom_cate = nom_cate;
        this.descr_cate = descr_cate;
    }

    public int getId_cate() {
        return id_cate;
    }

    public void setId_cate(int id_cate) {
        this.id_cate = id_cate;
    }

    public String getNom_cate() {
        return nom_cate;
    }

    public void setNom_cate(String nom_cate) {
        this.nom_cate = nom_cate;
    }

    public String getDescr_cate() {
        return descr_cate;
    }

    public void setDescr_cate(String descr_cate) {
        this.descr_cate = descr_cate;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Categoria{" + "id_cate=" + id_cate + ", nom_cate=" + nom_cate + ", descr_cate=" + descr_cate + '}';
    }

    

    
    
   

}
