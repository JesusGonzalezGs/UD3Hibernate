package es.severo.persistence.test;

import es.severo.persistence.entity.Book;
import es.severo.persistence.entity.Book_;
import es.severo.persistence.entity.Chapter;
import es.severo.persistence.entity.Chapter_;
import es.severo.persistence.util.HibernateUtil;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class testOneToMany {
    public static void main(String[] args) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            try{
                session.beginTransaction();

                /*Book book = new Book("ewjkhj888", "Fire", "Mamalona");
                Book book2 = new Book("uwu12345", "Jotun", "Baldur");

                Chapter c = new Chapter("Introduccion", 24);
                c.setBook(book);
                Chapter c2 = new Chapter("Muerte", 3);
                c2.setBook(book);

                Chapter c3 = new Chapter("Introduccion2", 45);
                c3.setBook(book2);
                Chapter c4 = new Chapter("Muerte47", 9);
                c4.setBook(book2);


                session.persist(book);
                session.persist(book2);
                session.persist(c);
                session.persist(c2);
                session.persist(c3);
                session.persist(c4); */
                /*
                Book book = session.find(Book.class, 1L);
                book.getChapters().forEach(System.out::println);

                session.remove(book);

                 */

                //Chapter c = session.find(Chapter.class, 3L);
                //System.out.println(c.getBook());

                //consultar todos los libros de la tabla capitulos

                /*CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
                Root<Book> root = criteria.from(Book.class);

                Join<Book,Chapter> join = root.join(Book_.chapters);
                criteria.select(root);

                List<Book> books = session.createQuery(criteria).getResultList();
                books.forEach(System.out::println);

                 */

                //consultar todos los capitulos de un libro dado

                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Chapter> criteria = builder.createQuery(Chapter.class);
                Root<Chapter> root = criteria.from(Chapter.class);
                Join<Chapter,Book> join = root.join(Chapter_.book);

                criteria
                        .select(root)
                                .where(
                                        builder.equal(join, session.find(Book.class, 3L))
                                );
                List<Chapter> chapters = session.createQuery(criteria).getResultList();
                chapters.forEach(System.out::println);
                session.getTransaction().commit();


            }catch (RuntimeException e){
                if (session.getTransaction() != null)
                    session.getTransaction().rollback();
                throw e;
            }
        }
    }
}
