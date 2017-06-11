package com.fakegenerator.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DetalleOrden.class)
public abstract class DetalleOrden_ {

	public static volatile SingularAttribute<DetalleOrden, String> id_orden_pago;
	public static volatile SingularAttribute<DetalleOrden, Float> precio_compra;
	public static volatile SingularAttribute<DetalleOrden, Float> precio_venta;
	public static volatile SingularAttribute<DetalleOrden, String> id_producto;
	public static volatile SingularAttribute<DetalleOrden, Producto> producto;
	public static volatile SingularAttribute<DetalleOrden, Integer> cantidad;
	public static volatile SingularAttribute<DetalleOrden, OrdenPago> ordenPago;

}

