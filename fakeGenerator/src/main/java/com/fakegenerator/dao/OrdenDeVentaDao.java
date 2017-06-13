/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.dao;

import com.fakegenerator.entities.Cliente;
import com.fakegenerator.entities.DetalleOrden;
import com.fakegenerator.entities.Distrito;
import com.fakegenerator.entities.Empleado;
import com.fakegenerator.entities.EstadoOrdenPago;
import com.fakegenerator.entities.OrdenPago;
import com.fakegenerator.entities.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jbust
 */
public class OrdenDeVentaDao {

    private Session session;

    public OrdenDeVentaDao(Session session) {
        this.session = session;
    }

    public List<String> cargarOrdenesPagoFake(List<String> listaDniClientes, Date fecInicioRango, Date fecFinRango, int numeroAdicionalDeOrdenes) {

        List<String> listaIdsOrdenesGeneradas = new ArrayList<>();

        int numeroDiasRango = (int) getDateDiff(fecInicioRango, fecFinRango);
        List<String> listaDniEmpleados = obtenerListaDnisEmpleadosRolEmpleado();
        int recorrido = listaDniClientes.size() + numeroAdicionalDeOrdenes;
        ClientesDao cdao = new ClientesDao(session);

        //variables adicionales
        String idOrdenPago;
        int numRand, diaRandom = 0, dRand, eRand, fRand;
        String direccion_envio;
        int idDistrito;
        Distrito distr_envio;
        Date fecha_creada, fec_entrega;
        float descuento;
        long numTarjeta;
        int id_estado;
        String nombre_cliente;
        EstadoOrdenPago estadoOrdenPago;
        Empleado empleado;

        //Estructura de un insert de orden de pago
        //id_orden, dni_cliente(null), nombre_cliente, direcc_envio, distr_envio, fecha_creada, fec_entrega(null), descuento(null), num_tarjeta, dni_empleado(null), id_estado
        //Estados -> 1 (sin pagar) :  fec_entrega siempre debe ser null, empleado debe ser siempre null
        //2 (Procesando): fec_entrega = null, empleado = null,
        //3 (Enviando): fec_entrega = null , el empleado si debe estar asignado
        //4 (Finalizado) : fec_entrega y empleado deben estar asignados             *  *****************
        //5 (Cancelado) :  fec_entrega y empleado deben estar asignados       **************
        //Recordar que si el estado está finalizado(4)
        for (int i = 0; i < recorrido; i++) {

            OrdenPago op = new OrdenPago();
//            idOrdenPago = obtieneCodigoOrdenCorrelativo();
            idOrdenPago = "fko." + (i + 1);
            op.setId_orden(idOrdenPago);

            //Fecha creada
            diaRandom = randomInt(1, numeroDiasRango - 1);
            fecha_creada = obtenerFechaAgregandoDias(fecInicioRango, diaRandom);
            op.setFecha_creada(fecha_creada);

            //Generando descuentos ( aplicaron algún cupón ) posibilidad -> 20%
            numRand = randomInt(1, 5);
            if (numRand == 1) {
                //Nuestro descuento sera un valor aleatorio entre 3% , 5% y 10%
                dRand = randomInt(1, 3);

                switch (dRand) {
                    case 1:
                        descuento = 0.03f;
                        break;
                    case 2:
                        descuento = 0.05f;
                        break;
                    default:
                        descuento = 0.1f;
                        break;
                }

                op.setDescuento(descuento);

            }

            //Generamos el número de tarjeta
            numTarjeta = randomLong(4485548216002737L, 4999999999999999L);
            op.setNum_tarjeta(cambiarFormatoTarjeta(numTarjeta));

            //Generando Estados(4y5) son compras ya hechas tiempo atraz, se supone 5% d prob que los estados sean 5(cancelados)
            eRand = randomInt(1, 20);
            if (eRand == 1) {
                id_estado = 5;
                estadoOrdenPago = obtieneEstadoPorId(id_estado);
                op.setEstadoOrdenPago(estadoOrdenPago);

            } else {
                id_estado = 4;
                estadoOrdenPago = obtieneEstadoPorId(id_estado);
                op.setEstadoOrdenPago(estadoOrdenPago);

                //Fecha de entrega aleatoria (fecha deberia rondar entre 3 a 30)
                fRand = randomInt(3, 30);
                fec_entrega = obtenerFechaAgregandoDias(fecha_creada, fRand);
                op.setFec_entrega(fec_entrega);

            }

            //Empleado
            int s = randomInt(0, listaDniEmpleados.size() - 1);
            empleado = obtieneEmpleadoPorDni(listaDniEmpleados.get(s));
            op.setEmpleado(empleado);

            if (i < listaDniClientes.size()) {
                Cliente c = obtieneClientePorDni(listaDniClientes.get(i));
                op.setCliente(c);
                //33% de posibilidad que el cliente que envie su producto comprado a otra direccion y otro receptor
                numRand = randomInt(0, 2);

                if (numRand == 0) {//Sino captura la direccion registrada que tiene el usuario(si no posee direccion vuelve a usar otra dirección)
                    direccion_envio = cdao.generarDireccion(cdao.listaDireccionesOrigen());
                    idDistrito = randomInt(1, 1831);//distrito al azar
                    distr_envio = cdao.obtenerUnDistritoPorId(idDistrito);

                    nombre_cliente = cdao.generarPersonaHombre(cdao.listaNombresHombreOrigen()) + " " + cdao.generarApellidosPersona(cdao.listaApellidosOrigen())[0] + " " + cdao.generarApellidosPersona(cdao.listaApellidosOrigen())[1];
                    op.setNombre_cliente(nombre_cliente);
                    op.setDirecc_envio(direccion_envio);
                    op.setDistrito(distr_envio);

                } else {//La dirección es la misma que tiene establecida como principal

                    if (c.getDistrito() != null) {
                        direccion_envio = c.getDireccion();
                        distr_envio = c.getDistrito();
                        nombre_cliente = c.getNombres() + " " + c.getApe_pat() + " " + c.getApe_mat();

                        op.setNombre_cliente(nombre_cliente);
                        op.setDirecc_envio(direccion_envio);
                        op.setDistrito(distr_envio);
                    } else {
                        direccion_envio = cdao.generarDireccion(cdao.listaDireccionesOrigen());
                        idDistrito = randomInt(1, 1831);//distrito al azar
                        distr_envio = cdao.obtenerUnDistritoPorId(idDistrito);

                        nombre_cliente = cdao.generarPersonaHombre(cdao.listaNombresHombreOrigen()) + " " + cdao.generarApellidosPersona(cdao.listaApellidosOrigen())[0] + " " + cdao.generarApellidosPersona(cdao.listaApellidosOrigen())[1];
                        op.setNombre_cliente(nombre_cliente);
                        op.setDirecc_envio(direccion_envio);
                        op.setDistrito(distr_envio);
                    }

                }

            } else {

                //Chance de que el usuario no sea registrado(33.3%)
                numRand = randomInt(1, 3);

                if (numRand == 1) {
                    direccion_envio = cdao.generarDireccion(cdao.listaDireccionesOrigen());
                    idDistrito = randomInt(1, 1831);//distrito al azar
                    distr_envio = cdao.obtenerUnDistritoPorId(idDistrito);

                    nombre_cliente = cdao.generarPersonaHombre(cdao.listaNombresHombreOrigen()) + " " + cdao.generarApellidosPersona(cdao.listaApellidosOrigen())[0] + " " + cdao.generarApellidosPersona(cdao.listaApellidosOrigen())[1];
                    op.setNombre_cliente(nombre_cliente);

                    op.setDirecc_envio(direccion_envio);
                    op.setDistrito(distr_envio);

                } else {//El usuario si esta registrado

                    int cliRandom = randomInt(0, listaDniClientes.size() - 1);
                    Cliente c = obtieneClientePorDni(listaDniClientes.get(cliRandom));
                    op.setCliente(c);
                    //33% de posibilidad que el cliente que envie su producto comprado a otra direccion y otro receptor
                    numRand = randomInt(0, 2);

                    if (numRand == 0) {//Sino captura la direccion registrada que tiene el usuario(si no posee direccion vuelve a usar otra dirección)
                        direccion_envio = cdao.generarDireccion(cdao.listaDireccionesOrigen());
                        idDistrito = randomInt(1, 1831);//distrito al azar
                        distr_envio = cdao.obtenerUnDistritoPorId(idDistrito);

                        nombre_cliente = cdao.generarPersonaHombre(cdao.listaNombresHombreOrigen()) + " " + cdao.generarApellidosPersona(cdao.listaApellidosOrigen())[0] + " " + cdao.generarApellidosPersona(cdao.listaApellidosOrigen())[1];
                        op.setNombre_cliente(nombre_cliente);
                        op.setDirecc_envio(direccion_envio);
                        op.setDistrito(distr_envio);

                    } else {//La dirección es la misma que tiene establecida como principal

                        if (c.getDistrito() != null) {
                            direccion_envio = c.getDireccion();
                            distr_envio = c.getDistrito();
                            nombre_cliente = c.getNombres() + " " + c.getApe_pat() + " " + c.getApe_mat();

                            op.setNombre_cliente(nombre_cliente);
                            op.setDirecc_envio(direccion_envio);
                            op.setDistrito(distr_envio);
                        } else {
                            direccion_envio = cdao.generarDireccion(cdao.listaDireccionesOrigen());
                            idDistrito = randomInt(1, 1831);//distrito al azar
                            distr_envio = cdao.obtenerUnDistritoPorId(idDistrito);

                            nombre_cliente = cdao.generarPersonaHombre(cdao.listaNombresHombreOrigen()) + " " + cdao.generarApellidosPersona(cdao.listaApellidosOrigen())[0] + " " + cdao.generarApellidosPersona(cdao.listaApellidosOrigen())[1];
                            op.setNombre_cliente(nombre_cliente);
                            op.setDirecc_envio(direccion_envio);
                            op.setDistrito(distr_envio);
                        }

                    }

                }

            }
            //Registro la orden de pago
            registrarOrdenPago(op);
        }

        //Ordeno por fecha y asigno su verdad id (actualizo)
        for (String id : obtenerListaOrdenesFakesOrderByFechaAsc()) {
            String nuevoId = obtieneCodigoOrdenCorrelativo();

            //Actualizo su id :
            modificaIdOrdenPago(id, nuevoId);
            listaIdsOrdenesGeneradas.add(nuevoId);
            

        }
        
        

        return listaIdsOrdenesGeneradas;

    }

    
    public List<DetalleOrden> cargarDetalleOrdenesFake(List<String> listaIdsOrdenesPago, int productosPorOrdenMaximo, int maxCantidadPorProducto) {

        //Variables locales        
        List<DetalleOrden> detalleOrdenesGenerados = new ArrayList<>();
        int numProductosAzar, productoAzar, cantidad;
        OrdenPago ordenPago;
        Producto unProducto;
        float descuento, precio_compra, precio_venta;

        for (String id_orden : listaIdsOrdenesPago) {
            numProductosAzar = randomInt(1, productosPorOrdenMaximo);
            ordenPago = obtieneOrdenPagoPorId(id_orden);

            List<Producto> productos = obtenerListaProductos();
            
            for (int i = 0; i < numProductosAzar; i++) {
                
                DetalleOrden detalle_orden = new DetalleOrden();
                ordenPago = obtieneOrdenPagoPorId(id_orden);
                detalle_orden.setOrdenPago(ordenPago);

                //genera un producto al azar
                productoAzar = randomInt(0, productos.size() - 1);
                unProducto = productos.get(productoAzar);
                
                detalle_orden.setProducto(unProducto);
                
                descuento = ordenPago.getDescuento();
                
                precio_compra = unProducto.getPrecio_compra();
                precio_venta = unProducto.getPrecio_venta() - unProducto.getPrecio_venta()*descuento;
                
                detalle_orden.setPrecio_compra(precio_compra);
                detalle_orden.setPrecio_venta(precio_venta);
                
                cantidad = randomInt(1, maxCantidadPorProducto);
                
                detalle_orden.setCantidad(cantidad);
                
                
                //registra El Producto
                registrarDetalleOrden(detalle_orden);
                detalleOrdenesGenerados.add(detalle_orden);
                productos.remove(productoAzar);

            }
        }
        
        return detalleOrdenesGenerados;
    }
    
    public List<String> obtenerListaDnisEmpleadosRolEmpleado() {
        List<String> listaDnis = null;

        String sql = "select p.dni from personas p inner join empleados e on p.dni = e.dni inner join usuarios u on p.username = u.username inner join roles_usuario ru on u.username = ru.username where ru.id_rol = 3";
        try {
            Connection c = new bd().obtieneConexionMysql();

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            listaDnis = new ArrayList<>();

            while (rs.next()) {

                listaDnis.add(rs.getString(1));
            }

            rs.close();
            pst.close();
            c.close();

        } catch (Exception e) {
            System.out.println("Error obtenerListaDnisEmpleados " + e);
        }

        return listaDnis;
    }

    public List<String> obtenerListaOrdenesFakesOrderByFechaAsc() {
        List<String> ordenes = null;

        String sql = "select ordenes_pago.id_orden from ordenes_pago where ordenes_pago.id_orden like 'fko.%' order by fecha_creada asc";
        try {
            Connection c = new bd().obtieneConexionMysql();

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            ordenes = new ArrayList<>();

            while (rs.next()) {

                ordenes.add(rs.getString(1));
            }

            rs.close();
            pst.close();
            c.close();

        } catch (Exception e) {
            System.out.println("Error obtenerListaOrdenesFakesOrderByFechaAsc " + e);
        }

        return ordenes;
    }

    public List<String> listOrdenesPago() {
        List<String> ordenes = null;

        String sql = "select * from ordenes_pago";
        try {
            Connection c = new bd().obtieneConexionMysql();

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            ordenes = new ArrayList<>();

            while (rs.next()) {

                ordenes.add(rs.getString(1));
            }

            rs.close();
            pst.close();
            c.close();

        } catch (Exception e) {
            System.out.println("Error listOrdenesPago " + e);
        }

        return ordenes;
    }

    public List<String> obtenerListaDnisClientes() {
        List<String> listaDnis = null;

        String sql = "select dni  from clientes";
        try {
            Connection c = new bd().obtieneConexionMysql();

            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            listaDnis = new ArrayList<>();

            while (rs.next()) {

                listaDnis.add(rs.getString(1));
            }

            rs.close();
            pst.close();
            c.close();

        } catch (Exception e) {
            System.out.println("Error obtenerListaDnisEmpleados " + e);
        }

        return listaDnis;
    }

    public List<Producto> obtenerListaProductos() {
        List<Producto> productos = null;

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
        Root<Producto> root = criteria.from(Producto.class);
        //Añadiendo lo que queremos que contenga nuestro query de categoría
        criteria.select(root);
        productos = session.createQuery(criteria).getResultList();

        return productos;
    }

    public long randomLong(long min, long max) {
        try {
            Random random = new Random();
            long result = min + (long) (random.nextDouble() * (max - min));
            return result;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return 0L;
    }

    public Cliente obtieneClientePorDni(String dni) {
        Cliente c = null;

        c = session.load(Cliente.class, dni);

        return c;
    }

    public Empleado obtieneEmpleadoPorDni(String dni) {
        Empleado c = null;

        c = session.load(Empleado.class, dni);

        return c;
    }

    public EstadoOrdenPago obtieneEstadoPorId(int id) {
        EstadoOrdenPago e = null;

        e = session.load(EstadoOrdenPago.class, id);

        return e;
    }

    public OrdenPago obtieneOrdenPagoPorId(String idOrden) {
        OrdenPago op = null;

        op = session.load(OrdenPago.class, idOrden);

        return op;
    }
    
    public Producto obtieneProductoPorId(String id){
        Producto p = null;
        
        p = session.load(Producto.class,id);
        
        return p;
    }

    public String obtieneCodigoOrdenCorrelativo() {

        String codigo = "";

        String sql = "select id_orden_pago()";

        try {

            Connection co = new bd().obtieneConexionMysql();

            PreparedStatement pst = co.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                codigo = rs.getString(1);
            }
            pst.close();
            rs.close();
            co.close();
        } catch (Exception e) {
            System.out.println("error de obtieneCodigoOrdenCorrelativo " + e);
        }

        return codigo;

    }

    public boolean registrarOrdenPago(OrdenPago unaOrdenPago) {
        boolean resultado = false;
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.save(unaOrdenPago);

            tx.commit();
            resultado = true;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error en el método [registrarOrdenPago]: " + e);
        }

        return resultado;
    }
    
    public boolean registrarDetalleOrden(DetalleOrden unaDetalleOrden) {
        boolean resultado = false;
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.save(unaDetalleOrden);

            tx.commit();
            resultado = true;

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error en el método [registrarDetalleOrden]: " + e);
        }

        return resultado;
    }

    public boolean modificaIdOrdenPago(String idActual, String idNuevo) {
        boolean resultado = false;

        String sql = "update ordenes_pago set id_orden=? where id_orden=?";
        try {
            Connection c = new bd().obtieneConexionMysql();

            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, idNuevo);
            pst.setString(2, idActual);
            pst.executeUpdate();

            pst.close();
            c.close();

        } catch (Exception e) {
            System.out.println("Error modificaIdOrdenPago " + e);
        }

        return resultado;
    }

    public int randomInt(int min, int max) {
        try {
            Random random = new Random();
            int result = min + (int) (random.nextDouble() * (max - min));
            return result;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return 0;
    }

    public static String getFechaFormateadaHoras(java.util.Date fechaDate) {

        Timestamp fecha = new Timestamp(fechaDate.getTime());

        SimpleDateFormat formateador = new SimpleDateFormat("_dd-MM-yyyy_HH-mm-ss");

        return (formateador.format(fecha.getTime()));

    }

    public static String getFechaFormateada(Date fecha) {

        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");

        return (formateador.format(fecha.getTime()));

    }

    public long getDateDiff(Date dateOne, Date dateTwo) {
        long timeOne = dateOne.getTime();
        long timeTwo = dateTwo.getTime();
        long oneDay = 1000 * 60 * 60 * 24;
        long delta = (timeTwo - timeOne) / oneDay;

        if (delta > 0) {
            return delta;
        } else {
            delta *= -1;
            return delta;
        }
    }

    public Date obtenerFechaAgregandoDias(Date fechaActual, int diasPorAgregar) {
        Date fechaNueva = null;

        Calendar c = Calendar.getInstance();
        c.setTime(fechaActual);
        c.add(Calendar.DATE, diasPorAgregar);

        fechaNueva = c.getTime();

        return fechaNueva;
    }

    public String cambiarFormatoTarjeta(Long numero) {
        String tarjeta = "";

        //4485 5482 1600 2737  0,4 4,8 8,12 12,16
        tarjeta = "" + numero;
        tarjeta = tarjeta.substring(0, 4) + "-" + tarjeta.substring(4, 8) + "-" + tarjeta.substring(8, 12) + "-" + tarjeta.substring(12, 16);
        return tarjeta;
    }

    public void cerrarSession() {
        session.close();
    }
}
