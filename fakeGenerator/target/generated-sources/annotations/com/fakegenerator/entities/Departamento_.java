package com.fakegenerator.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Departamento.class)
public abstract class Departamento_ {

	public static volatile SetAttribute<Departamento, Provincia> provincias;
	public static volatile SingularAttribute<Departamento, Integer> id_depa;
	public static volatile SingularAttribute<Departamento, String> nombre;

}

