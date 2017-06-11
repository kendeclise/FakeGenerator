/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator;

import com.fakegenerator.entities.DetalleOrden;
import com.fakegenerator.entities.Distrito;
import com.fakegenerator.entities.EstadoOrdenPago;
import com.fakegenerator.entities.OrdenPago;
import com.fakegenerator.entities.Producto;
import com.fakegenerator.util.HibernateUtil;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jbust
 */
public class mainClass {

    public static void main(String[] args) {
        //Clase principal
        Session session = HibernateUtil.getSessionFactory().openSession();

//        Transaction tx = session.beginTransaction();
//
//        Producto unProducto = session.load(Producto.class, "PID.00001");
//        System.out.println(""+unProducto);
//
//        OrdenPago op = new OrdenPago();
//        op.setId_orden("PID.00212");
//        op.setNombre_cliente("Evangelina Chamorro");
//        op.setDirecc_envio("Jr. Calamar 312");
//        
//        Distrito d = session.load(Distrito.class, 123);
//        
//        op.setDistrito(d);
//        op.setFecha_creada(new Date());
//        op.setNum_tarjeta("21374412312");
//        
//        EstadoOrdenPago eo = session.load(EstadoOrdenPago.class,1);
//        
//        op.setEstadoOrdenPago(eo);
//        session.save(op);
//        
//        DetalleOrden dto = new DetalleOrden();
//        dto.setId_producto(unProducto.getId_producto());
//        dto.setId_orden_pago(op.getId_orden());
////        dto.setProducto(unProducto);
////        dto.setOrdenPago(op);
//        dto.setPrecio_compra(10.0f);
//        dto.setPrecio_venta(30.0f);
//        dto.setCantidad(10);
//        
//        session.save(dto);
//        tx.commit();

    }
}
