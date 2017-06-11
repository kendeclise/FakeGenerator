package com.fakegenerator.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ extends com.fakegenerator.entities.Persona_ {

	public static volatile SetAttribute<Cliente, OrdenPago> ordenesPago;
	public static volatile SingularAttribute<Cliente, Distrito> distrito;
	public static volatile SingularAttribute<Cliente, String> notas;
	public static volatile SingularAttribute<Cliente, String> direccion;

}

