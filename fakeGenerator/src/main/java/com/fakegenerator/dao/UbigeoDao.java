/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.dao;

import com.fakegenerator.entities.Departamento;
import com.fakegenerator.entities.DepartamentoOrigen;
import com.fakegenerator.entities.Distrito;
import com.fakegenerator.entities.DistritoOrigen;
import com.fakegenerator.entities.Provincia;
import com.fakegenerator.entities.ProvinciaOrigen;
import com.fakegenerator.util.conexionSQL2014;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jbust
 */
public class UbigeoDao {

    private Session session;

    public UbigeoDao(Session session) {
        this.session = session;
    }

    public void cargarDatosUbigeoBdDestino(boolean valor) {
        //Listas
        List<DepartamentoOrigen> listaDepartamentos = listaDepartamentosOrigen();
        List<ProvinciaOrigen> listaProvincias = listaProvinciasOrigen();
        List<DistritoOrigen> listaDistritos = listaDistritosOrigen();

        eliminarDatosUbigeo(valor);

        //Cargando datos de departamentos
        for (DepartamentoOrigen dpo : listaDepartamentos) {
            Departamento unDepartamento = new Departamento();
            unDepartamento.setId_depa(dpo.getId());
            unDepartamento.setNombre(dpo.getNombre());

            registrarDepartamento(unDepartamento);
        }

        //Cargando datos de provincias
        for (ProvinciaOrigen pvo : listaProvincias) {
            Provincia unaProvincia = new Provincia();
            unaProvincia.setId_provi(pvo.getId());
            unaProvincia.setNombre(pvo.getNombre());
            Departamento unDepartamento = obtenerUnDepartamentoPorId(pvo.getId_depa());
            unaProvincia.setDepartamento(unDepartamento);

            registrarProvincia(unaProvincia);
        }

        //Cargando datos de distritos
        for (DistritoOrigen dio : listaDistritos) {
            Distrito unDistrito = new Distrito();
            unDistrito.setId_distri(dio.getId());
            unDistrito.setNombre(dio.getNombre());
            Provincia unaProvincia = obtenerUnaProvinciaPorId(dio.getId_prov());
            unDistrito.setProvincia(unaProvincia);

            registrarDistrito(unDistrito);
        }

    }

    public List<DepartamentoOrigen> listaDepartamentosOrigen() {

        List<DepartamentoOrigen> lista = null;

        conexionSQL2014 c = conexionSQL2014.getInstance();
        c.setUrl(null);
        c.setUsuario(null);
        c.setClave(null);

        String sql = "select * from departamentos";
        try {
            Connection co = c.getConnection();
            PreparedStatement pst = co.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            lista = new ArrayList<>();

            while (rs.next()) {
                lista.add(new DepartamentoOrigen(rs.getInt("id"), rs.getString("nombre")));
            }

            rs.close();
            pst.close();
            co.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return lista;

    }

    public List<ProvinciaOrigen> listaProvinciasOrigen() {

        List<ProvinciaOrigen> lista = null;

        conexionSQL2014 c = conexionSQL2014.getInstance();
        c.setUrl(null);
        c.setUsuario(null);
        c.setClave(null);

        String sql = "select * from provincias";
        try {
            Connection co = c.getConnection();
            PreparedStatement pst = co.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            lista = new ArrayList<>();

            while (rs.next()) {
                lista.add(new ProvinciaOrigen(rs.getInt("id"), rs.getString("nombre"), rs.getInt("id_depa")));
            }

            rs.close();
            pst.close();
            co.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return lista;

    }

    public List<DistritoOrigen> listaDistritosOrigen() {

        List<DistritoOrigen> lista = null;

        conexionSQL2014 c = conexionSQL2014.getInstance();
        c.setUrl(null);
        c.setUsuario(null);
        c.setClave(null);

        String sql = "select * from distritos";
        try {
            Connection co = c.getConnection();
            PreparedStatement pst = co.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            lista = new ArrayList<>();

            while (rs.next()) {
                lista.add(new DistritoOrigen(rs.getInt("id"), rs.getString("nombre"), rs.getInt("id_prov")));
            }

            rs.close();
            pst.close();
            co.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return lista;

    }

    public boolean registrarDepartamento(Departamento unDepartamento) {
        boolean resultado = false;
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.save(unDepartamento);

            tx.commit();
            resultado = true;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error en el método [registrarDepartamento]: " + e);
        }

        return resultado;
    }

    public boolean registrarProvincia(Provincia unaProvincia) {
        boolean resultado = false;
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.save(unaProvincia);

            tx.commit();
            resultado = true;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error en el método [registrarProvincia]: " + e);
        }

        return resultado;
    }

    public boolean registrarDistrito(Distrito unDistrito) {
        boolean resultado = false;
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.save(unDistrito);

            tx.commit();
            resultado = true;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error en el método [registrarDistrito]: " + e);
        }

        return resultado;
    }

    public Departamento obtenerUnDepartamentoPorId(int id) {
        Departamento unDepartamento = null;

        try {

            unDepartamento = session.load(Departamento.class, id);

        } catch (Exception e) {

            System.out.println("Error en el método [obtenerUnDepartamentoPorId]: " + e);
        }

        return unDepartamento;
    }

    public Provincia obtenerUnaProvinciaPorId(int id) {
        Provincia unaProvincia = null;

        try {

            unaProvincia = session.load(Provincia.class, id);

        } catch (Exception e) {

            System.out.println("Error en el método [obtenerUnaProvinciaPorId]: " + e);
        }

        return unaProvincia;
    }

    public void eliminarDatosUbigeo(boolean b) {
        if (b) {

            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                Query query = session.createQuery("DELETE  from Distrito");
                query.executeUpdate();

                tx.commit();

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                System.out.println("Error en el método [eliminarDatosUbigeo], borrando distritos: " + e);
            }

            tx = null;

            try {
                tx = session.beginTransaction();
                Query query = session.createQuery("DELETE  from Provincia");
                query.executeUpdate();

                tx.commit();

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                System.out.println("Error en el método [eliminarDatosUbigeo], borrando provincias: " + e);
            }

            tx = null;

            try {
                tx = session.beginTransaction();
                Query query = session.createQuery("DELETE  from Departamento");
                query.executeUpdate();

                tx.commit();

            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                System.out.println("Error en el método [eliminarDatosUbigeo], borrando distritos: " + e);
            }

        }

    }

    public void cerrarSession() {
        session.close();
    }

}
