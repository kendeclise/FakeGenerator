package com.fakegenerator.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Distrito.class)
public abstract class Distrito_ {

	public static volatile SetAttribute<Distrito, OrdenPago> ordenesPago;
	public static volatile SingularAttribute<Distrito, Integer> id_distri;
	public static volatile SingularAttribute<Distrito, Provincia> provincia;
	public static volatile SetAttribute<Distrito, Cliente> clientes;
	public static volatile SingularAttribute<Distrito, String> nombre;

}

