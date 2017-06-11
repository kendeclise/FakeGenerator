package com.fakegenerator.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Marca.class)
public abstract class Marca_ {

	public static volatile SingularAttribute<Marca, String> ubicacion_dir;
	public static volatile SingularAttribute<Marca, String> nom_marca;
	public static volatile SingularAttribute<Marca, Integer> id_marca;
	public static volatile SingularAttribute<Marca, String> descr_marca;
	public static volatile SetAttribute<Marca, Producto> productos;

}

