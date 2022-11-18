package es.severo.persistence.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> numero;
	public static volatile SingularAttribute<Address, String> street;
	public static volatile SingularAttribute<Address, Long> id;

	public static final String NUMERO = "numero";
	public static final String STREET = "street";
	public static final String ID = "id";

}

