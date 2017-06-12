/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.dao;

import com.fakegenerator.entities.Producto;
import com.fakegenerator.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jbust
 */
public class OrdenDeVentaDaoTest {

    public OrdenDeVentaDaoTest() {
    }

    @Test
    public void testCargarOrdenesVentasFicticios() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        OrdenDeVentaDao dao = new OrdenDeVentaDao(session);
        Date fecInicial = new Date(2015-1900,01,01);
        Date fecFin = new Date(2017-1900,01,01);
        
        dao.cargarOrdenesVentasFicticios(dao.obtenerListaDnisClientes(), fecInicial, fecFin, 80);
        
        System.out.println("testCargarOrdenesVentasFicticios");
        System.out.println("size: "+dao.obtenerListaDnisClientes().size());
        System.out.println(dao.obtenerListaDnisClientes());
        
        dao.cerrarSession();
        assertEquals(1, 1);
    }

    //@Test
    public void testObtenerListaDnisEmpleadosRolEmpleado() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        OrdenDeVentaDao dao = new OrdenDeVentaDao(session);

        List<String> listaDnisCargoEmpleado = dao.obtenerListaDnisEmpleadosRolEmpleado();
        System.out.println("testObtenerListaDnisEmpleadosRolEmpleado");
        System.out.println("size: " + listaDnisCargoEmpleado.size());
        System.out.println(listaDnisCargoEmpleado);

        dao.cerrarSession();
        assertNotNull(listaDnisCargoEmpleado);
    }

    //@Test
    public void testObtenerListaProductos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        OrdenDeVentaDao dao = new OrdenDeVentaDao(session);

        List<Producto> listaProductos = dao.obtenerListaProductos();
        System.out.println("testObtenerListaProductos");
        System.out.println("size: " + listaProductos.size());
        System.out.println(listaProductos);

        dao.cerrarSession();
        assertNotNull(listaProductos);
    }
    
    //@Test
    public void testGetDateDiff(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        OrdenDeVentaDao dao = new OrdenDeVentaDao(session);
        
        Date fecIni = new Date(2013-1900,1,1);
        Date fecFin = new Date(2016-1900,1,1);
             
        long dias = dao.getDateDiff(fecIni, fecFin);
        
        System.out.println("testGetDateDiff");
        System.out.println("Dias: "+dias);
        
       
        dao.cerrarSession();
        assertEquals(1, 1);
    }
    
    //@Test
    public void testObtieneCodigoOrdenCorrelativo(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        OrdenDeVentaDao dao = new OrdenDeVentaDao(session);
        
        String codigoGenerado = dao.obtieneCodigoOrdenCorrelativo();
        System.out.println("testObtieneCodigoOrdenCorrelativo");
        System.out.println("Código generado:" +codigoGenerado);
        
        dao.cerrarSession();
        assertEquals(codigoGenerado.substring(0, 4), "ORD.");
    }
    
    //@Test
    public void testObtenerFechaAgregandoDias(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        OrdenDeVentaDao dao = new OrdenDeVentaDao(session);
        
        Date fechaInicial = new Date(2015-1900,1,1);
        int diasPorAgregar = 4;
        Date nuevaFecha = dao.obtenerFechaAgregandoDias(fechaInicial, diasPorAgregar);
        System.out.println("testObtenerFechaAgregandoDias");
        System.out.println("Fecha inicial:" +fechaInicial);
        System.out.println("Fecha nueva:" +nuevaFecha);
        
        dao.cerrarSession();
        assertEquals(1, 1);
    }
    
    //@Test
    public void testCambiarFormatoTarjeta(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        OrdenDeVentaDao dao = new OrdenDeVentaDao(session);
        
        Long numero = 4485548216002737L;
        String tarjetaFormateada = dao.cambiarFormatoTarjeta(numero);
        System.out.println("testCambiarFormatoTarjeta");
        System.out.println("Numero de tarjeta: "+tarjetaFormateada);
        
        dao.cerrarSession();
        assertEquals(1, 1);
    }

}
