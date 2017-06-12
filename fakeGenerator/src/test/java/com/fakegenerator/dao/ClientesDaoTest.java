/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.dao;

import com.fakegenerator.entities.ApellidoOrigen;
import com.fakegenerator.entities.Cliente;
import com.fakegenerator.entities.DireccionOrigen;
import com.fakegenerator.entities.NombreHombreOrigen;
import com.fakegenerator.entities.NombreMujerOrigen;
import com.fakegenerator.entities.TelefonoOrigen;
import com.fakegenerator.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jbust
 */
public class ClientesDaoTest {

    public ClientesDaoTest() {
    }

//    @Test
    public void testListaApellidosOrigen() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClientesDao dao = new ClientesDao(session);

        List<ApellidoOrigen> listaApellidos = null;

        listaApellidos = dao.listaApellidosOrigen();
        System.out.println("testListaApellidosOrigen");
        System.out.println("size:" + listaApellidos.size());
        System.out.println(listaApellidos);

        dao.cerrarSession();
        assertNotNull(listaApellidos);
    }

//    @Test
    public void testListaNombresHombreOrigen() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClientesDao dao = new ClientesDao(session);

        List<NombreHombreOrigen> listaNombresHombre = null;

        listaNombresHombre = dao.listaNombresHombreOrigen();
        System.out.println("testListaNombresHombreOrigen");
        System.out.println("size:" + listaNombresHombre.size());
        System.out.println(listaNombresHombre);

        dao.cerrarSession();
        assertNotNull(listaNombresHombre);
    }

//    @Test
    public void testListaNombresMujerOrigen() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClientesDao dao = new ClientesDao(session);

        List<NombreMujerOrigen> listaNombresMujer = null;

        listaNombresMujer = dao.listaNombresMujerOrigen();
        System.out.println("testListaNombresMujerOrigen");
        System.out.println("size:" + listaNombresMujer.size());
        System.out.println(listaNombresMujer);

        dao.cerrarSession();
        assertNotNull(listaNombresMujer);
    }

//    @Test
    public void testListaDireccionesOrigen() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClientesDao dao = new ClientesDao(session);

        List<DireccionOrigen> listaDirecciones = null;

        listaDirecciones = dao.listaDireccionesOrigen();
        System.out.println("testListaDireccionesOrigen");
        System.out.println("size:" + listaDirecciones.size());
        System.out.println(listaDirecciones);

        dao.cerrarSession();
        assertNotNull(listaDirecciones);
    }

    //@Test
    public void testListaTelefonosOrigen() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClientesDao dao = new ClientesDao(session);

        List<TelefonoOrigen> listaTelefonos = null;

        listaTelefonos = dao.listaTelefonosOrigen();
        System.out.println("testListaTelefonosOrigen");
        System.out.println("size:" + listaTelefonos.size());
        System.out.println(listaTelefonos);

        dao.cerrarSession();
        assertNotNull(listaTelefonos);
    }

    //@Test
    public void testGenerarPersonaHombre() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClientesDao dao = new ClientesDao(session);

        String nombreGenerado = "";
        List<NombreHombreOrigen> listaNombreHombres = dao.listaNombresHombreOrigen();

        System.out.println("testGenerarPersonaHombre");
//        nombreGenerado = dao.generarPersonaHombre(listaNombreHombres);              
//        System.out.println("Nombre generado:"+ nombreGenerado);

        for (int i = 0; i < 20; i++) {
            nombreGenerado = dao.generarPersonaHombre(listaNombreHombres);
            System.out.println("Nombre generado:" + nombreGenerado);
        }

        dao.cerrarSession();
        assertNotNull(nombreGenerado);
    }

    //@Test
    public void testGenerarPersonaMujer() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClientesDao dao = new ClientesDao(session);

        String nombreGenerado = "";
        List<NombreMujerOrigen> listaNombreMujeres = dao.listaNombresMujerOrigen();

        System.out.println("testGenerarPersonaMujer");
//        nombreGenerado = dao.generarPersonaHombre(listaNombreHombres);              
//        System.out.println("Nombre generado:"+ nombreGenerado);

        for (int i = 0; i < 20; i++) {
            nombreGenerado = dao.generarPersonaMujer(listaNombreMujeres);
            System.out.println("Nombre generado:" + nombreGenerado);
        }

        dao.cerrarSession();
        assertNotNull(nombreGenerado);
    }

//    @Test
    public void testGenerarApellidosPersona() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClientesDao dao = new ClientesDao(session);

        String[] apellidosGenerados = new String[2];
        List<ApellidoOrigen> listaApellidos = dao.listaApellidosOrigen();

        System.out.println("testGenerarApellidosPersona");

        for (int i = 0; i < 20; i++) {
            apellidosGenerados = dao.generarApellidosPersona(listaApellidos);
            System.out.println("Apellidos generados:" + apellidosGenerados[0] + " " + apellidosGenerados[1]);
        }

        dao.cerrarSession();
        assertNotNull(apellidosGenerados);
    }

    //@Test
    public void testGenerarDniAleatorio() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClientesDao dao = new ClientesDao(session);

        String dniGenerado = "";
        List<String> listaDnis = new ArrayList<>();

        System.out.println("testGenerarDniAleatorio");

        for (int i = 0; i < 20; i++) {
            dniGenerado = dao.generarDniAleatorio(listaDnis);
            System.out.println("Dni generado:" + dniGenerado);
        }

        dao.cerrarSession();
        assertNotNull(dniGenerado);
    }

    //@Test
    public void testCargarClientesFicticios() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClientesDao dao = new ClientesDao(session);

        System.out.println("testCargarClientesFicticios");
        dao.cargarClientesFicticios(20);

        dao.cerrarSession();
        assertEquals(1, 1);
    }
    
    @Test
    public void testListaClientes(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClientesDao dao = new ClientesDao(session);

        System.out.println("testListaClientes");
        List<Cliente> clientes = dao.listaClientes();
        System.out.println("Size: "+clientes.size());
        System.out.println(clientes.get(0).getApe_pat());
        System.out.println(clientes);

        dao.cerrarSession();
        assertNotNull(clientes);
    }

}
