package com.fakegenerator.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Provincia.class)
public abstract class Provincia_ {

	public static volatile SetAttribute<Provincia, Distrito> distritos;
	public static volatile SingularAttribute<Provincia, Departamento> departamento;
	public static volatile SingularAttribute<Provincia, Integer> id_provi;
	public static volatile SingularAttribute<Provincia, String> nombre;

}

