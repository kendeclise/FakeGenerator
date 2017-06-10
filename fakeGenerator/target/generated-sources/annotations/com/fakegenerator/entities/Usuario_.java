package com.fakegenerator.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> password;
	public static volatile ListAttribute<Usuario, Rol> roles;
	public static volatile SingularAttribute<Usuario, Persona> personas;
	public static volatile SingularAttribute<Usuario, Boolean> enabled;
	public static volatile SingularAttribute<Usuario, String> username;

}

