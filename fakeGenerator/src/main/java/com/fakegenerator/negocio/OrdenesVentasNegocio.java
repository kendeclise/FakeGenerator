/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator.negocio;

import com.fakegenerator.dao.ClientesDao;
import com.fakegenerator.dao.OrdenDeVentaDao;
import com.fakegenerator.entities.DetalleOrden;
import com.fakegenerator.util.HibernateUtil;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author jbust
 */
public class OrdenesVentasNegocio {

    public boolean generarDatosFalsos(int numeroClientesPorGenerar, String fechaRangoInicio, String fechaRangoFin, int numOrdenesAdicionales, int productosPorCompraMaximo, int maxCantidadCompradaEnCadaProducto) {

        boolean validacion = true;
        Session sessionx = HibernateUtil.getSessionFactory().openSession();
        int topMaxProductos = new OrdenDeVentaDao(sessionx).obtenerListaProductos().size()-1;
        sessionx.close();

        //comprobamos que el numero de clientes por generar sea mayor a 0 y menor a  2000
        if (numeroClientesPorGenerar > 2000) {
            validacion = false;
            System.out.println("Error, no se pudo generar los datos. Razón: \"El número de ClientesPorGenerar no puede superar los 2000\".");
        }
        if (numOrdenesAdicionales < 0) {
            validacion = false;
            System.out.println("Error, no se pudo generar los datos. Razón: \"El número de ClientesPorGenerar no puede ser menor a 0\".");
        }

        //comprobamos que el numero de ordenes sea mayor o igual a 0 , y un maximo de 4000
        if (numOrdenesAdicionales < 0) {
            validacion = false;
            System.out.println("Error, no se pudo generar los datos. Razón: \"El número ingresado de OrdenesAdicionales no puede ser negativo\".");
        }

        if (numOrdenesAdicionales > 4000) {
            validacion = false;
            System.out.println("Error, no se pudo generar los datos. Razón: \"El número ingresado de ordenesAdicionales no puede superar los 4000\".");
        }

        //comprobamos que el numero productosPorCompraMaximo sea mayor a 1 y maximo 30
        if (productosPorCompraMaximo <= 1) {
            validacion = false;
            System.out.println("Error, no se pudo generar los datos. Razón: \"El número ingresado de productosPorCompraMaximo debe ser mayor a 1\".");
        }

        if (productosPorCompraMaximo > topMaxProductos+1) {
            validacion = false;
            System.out.println("Error, no se pudo generar los datos. Razón: \"El número ingresado de productosPorCompraMaximo debe ser menor o igual a "+(topMaxProductos+1)+"\".");
        }

        //comprobamos que el número maxCantidadCompradaEnCadaProducto sea mayor a 1 y menor a 100
        if (maxCantidadCompradaEnCadaProducto <= 1) {
            validacion = false;
            System.out.println("Error, no se pudo generar los datos. Razón: \"El número ingresado de maxCantidadCompradaEnCadaProducto debe ser mayor a 1\".");
        }

        if (maxCantidadCompradaEnCadaProducto > 100) {
            validacion = false;
            System.out.println("Error, no se pudo generar los datos. Razón: \"El número ingresado de maxCantidadCompradaEnCadaProducto debe ser menor o igual 100\".");
        }

        Date fecInicioRango = null;
        Date fecFinRango = null;
        //comprobamos que los dates tengan el formato correcto
        try {
            fecInicioRango = string2Date(fechaRangoInicio);
            fecFinRango = string2Date(fechaRangoFin);

        } catch (Exception e) {
            //System.out.println("Error al ingresar la fechaRango, debido a que no usaste el formato \"dd-MM-yyyy\" \nInfo. adicional:\n " + e);
            System.out.println("Error, no se pudo generar los datos. Razón: \"El formato usado en las FechasRango debe ser 'dd-MM-yyyy'\".");
            validacion = false;
        }

        if (validacion == true) {
            //Iniciamos todo
            Session session = HibernateUtil.getSessionFactory().openSession();
            ClientesDao cdao = new ClientesDao(session);
            OrdenDeVentaDao ovdao = new OrdenDeVentaDao(session);

            //Generamos los clientes ingresados
            List<String> listaDnisClientesGenerados = cdao.cargarClientesFicticios(numeroClientesPorGenerar);

            //Generamos las ordenes de pago
            List<String> listaIdOrdenesPagoGeneradas = ovdao.cargarOrdenesPagoFake(listaDnisClientesGenerados, fecInicioRango, fecFinRango, numOrdenesAdicionales);

            List<DetalleOrden> listaDetalleOrdenesGenerados = ovdao.cargarDetalleOrdenesFake(listaIdOrdenesPagoGeneradas, productosPorCompraMaximo, maxCantidadCompradaEnCadaProducto);

            cdao.cerrarSession();
            ovdao.cerrarSession();

            //Descripcion
            System.out.println("*********************************************************************\n");
            System.out.println("Reporte de inserts");
            System.out.println("BD: \"testbd\"");
            System.out.println("Usuario: \"root\"");
            System.out.println("Contraseña: \"*****\"\n");
            System.out.println("Número de personas generadas: "+listaDnisClientesGenerados.size());
            System.out.println("Número de clientes generados: "+listaDnisClientesGenerados.size());
            System.out.println("Número de usuarios generados: "+listaDnisClientesGenerados.size());
            System.out.println("Número de ordenes de pago generadas: "+listaIdOrdenesPagoGeneradas.size());
            System.out.println("Número de detalle de ordenes generados: "+listaDetalleOrdenesGenerados.size());
            
            //Generando el archivo de deletes, el cual se ubica en ../recursos_generados/
            String fecActual = getFechaFormateadaHoras(new java.util.Date());

            generandoArchivoTextoCabecera("delete" + fecActual);
            generandoArchivoTextoDetalleOrden("delete" + fecActual, listaDetalleOrdenesGenerados);
            generandoArchivoTexto2("delete" + fecActual, "ordenes_pago", "id_orden", listaIdOrdenesPagoGeneradas);
            generandoArchivoTexto("delete" + fecActual, "clientes", "dni", listaDnisClientesGenerados);
            generandoArchivoTexto("delete" + fecActual, "personas", "dni", listaDnisClientesGenerados);
            generandoArchivoTexto("delete" + fecActual, "usuarios", "username", listaDnisClientesGenerados);
            System.out.println("Archivo generado: '~/recursos_generados/delete" + fecActual+".sql'");
            
            System.out.println("\n*********************************************************************");
            

        }

        return validacion;

    }

    private Date string2Date(String dateString) throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        return formatter.parse(dateString);
    }

    private  String getFechaFormateadaHoras(java.util.Date fechaDate) {

        Timestamp fecha = new Timestamp(fechaDate.getTime());

        SimpleDateFormat formateador = new SimpleDateFormat("_dd-MM-yyyy_HH-mm-ss");

        return (formateador.format(fecha.getTime()));

    }
    
    private void generandoArchivoTextoCabecera(String nombreArchivo) {
        //Variables de archivos
        FileWriter archivo = null;
        PrintWriter pw = null;

        try {
            archivo = new FileWriter("./recursos_generados/" + nombreArchivo + ".sql", true);
            pw = new PrintWriter(archivo);

           
                pw.println("use testdb;");
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != archivo) {
                    archivo.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void generandoArchivoTexto(String nombreArchivo, String nombreTabla, String nombreColumnaId, List<String> lista) {
        //Variables de archivos
        FileWriter archivo = null;
        PrintWriter pw = null;

        try {
            archivo = new FileWriter("./recursos_generados/" + nombreArchivo + ".sql", true);
            pw = new PrintWriter(archivo);

            for (String s : lista) {
                pw.println("delete from " + nombreTabla + " where " + nombreColumnaId + "= " + s + ";");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != archivo) {
                    archivo.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    private void generandoArchivoTexto2(String nombreArchivo, String nombreTabla, String nombreColumnaId, List<String> lista) {
        //Variables de archivos
        FileWriter archivo = null;
        PrintWriter pw = null;

        try {
            archivo = new FileWriter("./recursos_generados/" + nombreArchivo + ".sql", true);
            pw = new PrintWriter(archivo);

            for (String s : lista) {
                pw.println("delete from " + nombreTabla + " where " + nombreColumnaId + "= '" + s + "';");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != archivo) {
                    archivo.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void generandoArchivoTextoDetalleOrden(String nombreArchivo, List<DetalleOrden> lista) {
        //Variables de archivos
        FileWriter archivo = null;
        PrintWriter pw = null;

        try {
            archivo = new FileWriter("./recursos_generados/" + nombreArchivo + ".sql", true);
            pw = new PrintWriter(archivo);

            for (DetalleOrden dto : lista) {
                String sentenciaSql = "delete from detalle_ordenes where id_orden_pago = '" + dto.getId_orden_pago() + "' and id_producto = '" + dto.getId_producto() + "';";
                pw.println(sentenciaSql);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != archivo) {
                    archivo.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
