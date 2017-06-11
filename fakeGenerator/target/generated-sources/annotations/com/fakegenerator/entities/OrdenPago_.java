package com.fakegenerator.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrdenPago.class)
public abstract class OrdenPago_ {

	public static volatile SingularAttribute<OrdenPago, Cliente> cliente;
	public static volatile SingularAttribute<OrdenPago, Distrito> distrito;
	public static volatile SingularAttribute<OrdenPago, EstadoOrdenPago> estadoOrdenPago;
	public static volatile SingularAttribute<OrdenPago, Empleado> empleado;
	public static volatile SingularAttribute<OrdenPago, Float> descuento;
	public static volatile SingularAttribute<OrdenPago, Date> fecha_creada;
	public static volatile SingularAttribute<OrdenPago, Date> fec_entrega;
	public static volatile SingularAttribute<OrdenPago, String> num_tarjeta;
	public static volatile SingularAttribute<OrdenPago, String> id_orden;
	public static volatile SingularAttribute<OrdenPago, String> direcc_envio;
	public static volatile SingularAttribute<OrdenPago, String> nombre_cliente;

}

