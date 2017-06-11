package com.fakegenerator.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Producto.class)
public abstract class Producto_ {

	public static volatile SingularAttribute<Producto, Marca> marca;
	public static volatile SingularAttribute<Producto, Date> fec_agregado;
	public static volatile SingularAttribute<Producto, Float> precio_compra;
	public static volatile SingularAttribute<Producto, Categoria> categoria;
	public static volatile SingularAttribute<Producto, Float> precio_venta;
	public static volatile SingularAttribute<Producto, String> id_producto;
	public static volatile SingularAttribute<Producto, Integer> stock;
	public static volatile SingularAttribute<Producto, String> nombre;
	public static volatile SingularAttribute<Producto, Boolean> publicado;

}

