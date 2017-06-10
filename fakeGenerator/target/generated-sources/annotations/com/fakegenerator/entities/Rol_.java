package com.fakegenerator.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rol.class)
public abstract class Rol_ {

	public static volatile SingularAttribute<Rol, Integer> id_rol;
	public static volatile ListAttribute<Rol, Usuario> usuarios;
	public static volatile SingularAttribute<Rol, String> rol;

}

