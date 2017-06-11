/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author jbust
 */
public class DetalleOrdenId implements Serializable {

    private String id_orden_pago;
    private String id_producto;

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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetalleOrdenId other = (DetalleOrdenId) obj;
        if (!Objects.equals(this.id_orden_pago, other.id_orden_pago)) {
            return false;
        }
        if (!Objects.equals(this.id_producto, other.id_producto)) {
            return false;
        }
        return true;
    }

}
