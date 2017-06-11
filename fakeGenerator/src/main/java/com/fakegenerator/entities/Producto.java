/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "productos")
public class Producto {

    @Id
    private String id_producto;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;//id_categoria FK

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca; //id_marca FK

    private float precio_compra;

    private float precio_venta;

    private int stock;

    private Date fec_agregado;

    private boolean publicado;
    
//    @OneToMany(mappedBy = "producto")
//    private List<DetalleOrden> detalleOrdenes;

//    public List<DetalleOrden> getDetalleOrdenes() {
//        return detalleOrdenes;
//    }
//
//    public void setDetalleOrdenes(List<DetalleOrden> detalleOrdenes) {
//        this.detalleOrdenes = detalleOrdenes;
//    }
//   
    

    public Producto() {
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public float getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(float precio_compra) {
        this.precio_compra = precio_compra;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getFec_agregado() {
        return fec_agregado;
    }

    public void setFec_agregado(Date fec_agregado) {
        this.fec_agregado = fec_agregado;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

  

    @Override
    public String toString() {
        return "Producto{" + "id_producto=" + id_producto + ", nombre=" + nombre + ", categoria=" + categoria + ", marca=" + marca + ", precio_compra=" + precio_compra + ", precio_venta=" + precio_venta + ", stock=" + stock + ", fec_agregado=" + fec_agregado + ", publicado=" + publicado + '}';
    }

}
