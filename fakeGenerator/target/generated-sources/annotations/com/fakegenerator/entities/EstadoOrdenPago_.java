package com.fakegenerator.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EstadoOrdenPago.class)
public abstract class EstadoOrdenPago_ {

	public static volatile SetAttribute<EstadoOrdenPago, OrdenPago> ordenesPago;
	public static volatile SingularAttribute<EstadoOrdenPago, String> descr;
	public static volatile SingularAttribute<EstadoOrdenPago, Integer> id;
	public static volatile SingularAttribute<EstadoOrdenPago, String> nombre;

}

