package com.fakegenerator.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ extends com.fakegenerator.entities.Persona_ {

	public static volatile SingularAttribute<Cliente, Distrito> distrito;
	public static volatile SingularAttribute<Cliente, String> notas;
	public static volatile SingularAttribute<Cliente, String> direccion;
	public static volatile SingularAttribute<Cliente, String> telefono;
	public static volatile SingularAttribute<Cliente, String> email;

}

