/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fakegenerator;

import com.fakegenerator.negocio.OrdenesVentasNegocio;

/**
 *
 * @author jbust
 */
public class mainClass {

    public static void main(String[] args) {

        
        /*
            GENERADOR DE DATOS FALSOS, PARA EL PROYECTO STORE-WEB-SITE
            
            Url-Repositorio: https://github.com/kendeclise/FakeGenerator
            Url-Repositorio-Store-Web-Site: https://github.com/kendeclise/Store-website-java
        
            generarDatosFalsos(int numeroClientesPorGenerar, String fechaRangoInicio, String fechaRangoFin, int numOrdenesAdicionales, int productosPorCompraMaximo,
                                int maxCantidadCompradaEnCadaProducto)
            
            Datos de los parámetros de ingreso
        
            numeroClientesPorGenerar: Es el número de usuarios que deseamos generar aleatoriamente en nuestra BD.
            fechaRangoInicio: Es el rango inicial de fecha que deseamos usar para generar las ventas(OrdenesPago) que serán generados aleatoriamente en nuestra BD.
            fechaRangoFin: Es el rango final de fecha que deseamos usar para generar las ventas(OrdenesPago) que serán generados aleatoriamente en nuestra BD.
            numOrdenesAdicionales: Son las ordenes adicionales que se crearán aleatoriamente en la BD muy aparte a las que se crean(Por defecto siempre se crean el mismo
                                   número de los clientes ingresados(1 orden por cada cliente), en el caso de las ordenes adicionales salen aleatoriamente para cualquier 
                                   cliente de nuestra tabla Clientes.
            productosPorCompraMaximo: Es el número máximo de productos que se generarán en cada compra(OrdenPago) ya que se generaran aleatoriamente entre 1 y el 
                                      número de productosPorCompraMaximo.
            maxCantidadCompradaEnCadaProducto: Es el número máximo de cantidad adquirida de cada producto en una compra que se generará aleatoriamente que será entre 1 y el
                                               número de maxCantidadCompradaEnCadaProducto.
        */
        OrdenesVentasNegocio ovn = new OrdenesVentasNegocio();
        ovn.generarDatosFalsos(40, "01-01-2016", "01-01-2017", 200, 10, 20);
        //ovn.generarDatosFalsos(10, "01-01-2015", "01-01-2017", 0, 2, 2);
    }
}
