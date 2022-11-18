package es.severo.persistence.entity;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Book.class)
public abstract class Book_ {

	public static volatile ListAttribute<Book, Chapter> chapters;
	public static volatile SingularAttribute<Book, String> author;
	public static volatile SingularAttribute<Book, String> isbn;
	public static volatile SingularAttribute<Book, Long> id;
	public static volatile SingularAttribute<Book, String> title;

	public static final String CHAPTERS = "chapters";
	public static final String AUTHOR = "author";
	public static final String ISBN = "isbn";
	public static final String ID = "id";
	public static final String TITLE = "title";

}

