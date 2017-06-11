/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author jbust
 */
@Entity
@Table(name = "detalle_ordenes")
@IdClass(DetalleOrdenId.class)
public class DetalleOrden {
    @Id
    private String id_orden_pago;
    @Id
    private String id_producto;
    
    @ManyToOne
//    @PrimaryKeyJoinColumn(name = "id_orden_pago", referencedColumnName = "id_orden")
    @JoinColumn(name = "id_orden_pago", updatable = false, insertable = false, referencedColumnName = "id_orden")
    private OrdenPago ordenPago;
    
    
    @ManyToOne
//    @PrimaryKeyJoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @JoinColumn(name = "id_producto", updatable = false, insertable = false, referencedColumnName = "id_producto")
    private Producto producto;
     
     private int cantidad;
     private float precio_compra;
     private float precio_venta;

    public DetalleOrden() {
    }

    public OrdenPago getOrdenPago() {
        return ordenPago;
    }

    public void setOrdenPago(OrdenPago ordenPago) {
        this.ordenPago = ordenPago;
        this.id_orden_pago = ordenPago.getId_orden();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        this.id_producto = producto.getId_producto();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    @Override
    public String toString() {
        return "DetalleOrden{" + "ordenPago=" + ordenPago + ", producto=" + producto + ", cantidad=" + cantidad + ", precio_compra=" + precio_compra + ", precio_venta=" + precio_venta + '}';
    }

    public String getId_orden_pago() {
        return id_orden_pago;
    }

    public void setId_orden_pago(String id_orden_pago) {
        this.id_orden_pago = id_orden_pago;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }
    
    
     
     
    
    
}
