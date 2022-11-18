package es.severo.persistence.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Chapter.class)
public abstract class Chapter_ {

	public static volatile SingularAttribute<Chapter, Integer> pages;
	public static volatile SingularAttribute<Chapter, Book> book;
	public static volatile SingularAttribute<Chapter, String> titulo;
	public static volatile SingularAttribute<Chapter, Long> id;

	public static final String PAGES = "pages";
	public static final String BOOK = "book";
	public static final String TITULO = "titulo";
	public static final String ID = "id";

}

