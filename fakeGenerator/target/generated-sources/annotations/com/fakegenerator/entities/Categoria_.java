package com.fakegenerator.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Categoria.class)
public abstract class Categoria_ {

	public static volatile SingularAttribute<Categoria, String> nom_cate;
	public static volatile SingularAttribute<Categoria, String> descr_cate;
	public static volatile SetAttribute<Categoria, Producto> productos;
	public static volatile SingularAttribute<Categoria, Integer> id_cate;

}

