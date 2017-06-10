/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.dao;

import com.fakegenerator.entities.DepartamentoOrigen;
import com.fakegenerator.entities.DistritoOrigen;
import com.fakegenerator.entities.ProvinciaOrigen;
import com.fakegenerator.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jbust
 */
public class UbigeoDaoTest {
    
    public UbigeoDaoTest() {
    }

    @Test
    public void testListaDepartamentosOrigen() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UbigeoDao dao = new UbigeoDao(session);
        
        List<DepartamentoOrigen> listaDepartamentos = null;
        
        listaDepartamentos = dao.listaDepartamentosOrigen();
        System.out.println("testListaDepartamentosOrigen");
        System.out.println("size:"+listaDepartamentos.size());
        //System.out.println(listaDepartamentos);
        
        dao.cerrarSession();
        assertNotNull(listaDepartamentos);
    }

    @Test
    public void testListaProvinciasOrigen() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UbigeoDao dao = new UbigeoDao(session);
        
        List<ProvinciaOrigen> listaProvincias = null;
        
        listaProvincias = dao.listaProvinciasOrigen();
        System.out.println("testListaProvinciasOrigen");
        System.out.println("size:"+listaProvincias.size());
        //System.out.println(listaProvincias);
        
        dao.cerrarSession();
        assertNotNull(listaProvincias);
    }
    
    @Test
    public void testListaDDistritosOrigen() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UbigeoDao dao = new UbigeoDao(session);
        
        List<DistritoOrigen> listaDistritos = null;
        
        listaDistritos = dao.listaDistritosOrigen();
        System.out.println("testListaDDistritosOrigen");
        System.out.println("size:"+listaDistritos.size());
        //System.out.println(listaDistritos);
        
        dao.cerrarSession();
        assertNotNull(listaDistritos);
    }
    
    
    //@Test
    public void testCargarDatosUbigeoBdDestino() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UbigeoDao dao = new UbigeoDao(session);
        
        
        dao.cargarDatosUbigeoBdDestino(true);
        
        dao.cerrarSession();
        assertEquals(1, 1);
    }
    
}
