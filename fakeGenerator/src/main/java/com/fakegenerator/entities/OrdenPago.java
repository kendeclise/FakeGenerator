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
@Table(name = "ordenes_pago")
public class OrdenPago {

    @Id
    private String id_orden;

    @ManyToOne
    @JoinColumn(name = "dni_cliente")
    private Cliente cliente;//dni_cliente FK

    private String nombre_cliente;

    private String direcc_envio;

    @ManyToOne
    @JoinColumn(name = "distr_envio")
    private Distrito distrito; //distr_envio FK(id)

    private Date fecha_creada;

    private Date fec_entrega;

    private float descuento;

    private String num_tarjeta;

    @ManyToOne
    @JoinColumn(name = "dni_empleado")
    private Empleado empleado; //dni_empleado FK

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private EstadoOrdenPago estadoOrdenPago; //id_estado FK

//    @OneToMany(mappedBy = "ordenPago")
//    private List<DetalleOrden> detalleOrdenes;
//
//    public List<DetalleOrden> getDetalleOrdenes() {
//        return detalleOrdenes;
//    }
//
//    public void setDetalleOrdenes(List<DetalleOrden> detalleOrdenes) {
//        this.detalleOrdenes = detalleOrdenes;
//    }

    public OrdenPago() {
    }

    public String getId_orden() {
        return id_orden;
    }

    public void setId_orden(String id_orden) {
        this.id_orden = id_orden;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getDirecc_envio() {
        return direcc_envio;
    }

    public void setDirecc_envio(String direcc_envio) {
        this.direcc_envio = direcc_envio;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public Date getFecha_creada() {
        return fecha_creada;
    }

    public void setFecha_creada(Date fecha_creada) {
        this.fecha_creada = fecha_creada;
    }

  

    public Date getFec_entrega() {
        return fec_entrega;
    }

    public void setFec_entrega(Date fec_entrega) {
        this.fec_entrega = fec_entrega;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public String getNum_tarjeta() {
        return num_tarjeta;
    }

    public void setNum_tarjeta(String num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public EstadoOrdenPago getEstadoOrdenPago() {
        return estadoOrdenPago;
    }

    public void setEstadoOrdenPago(EstadoOrdenPago estadoOrdenPago) {
        this.estadoOrdenPago = estadoOrdenPago;
    }

    @Override
    public String toString() {
        return "OrdenPago{" + "id_orden=" + id_orden + ", cliente=" + cliente + ", nombre_cliente=" + nombre_cliente + ", direcc_envio=" + direcc_envio + ", distrito=" + distrito + ", fec_creada=" + fecha_creada + ", fec_entrega=" + fec_entrega + ", descuento=" + descuento + ", num_tarjeta=" + num_tarjeta + ", empleado=" + empleado + ", estadoOrdenPago=" + estadoOrdenPago + '}';
    }

}
