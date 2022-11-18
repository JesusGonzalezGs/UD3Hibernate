package es.severo;

import es.severo.persistence.entity.Tramite;
import es.severo.persistence.entity.Tramite_;
import es.severo.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Tramite tramite = new Tramite();
            tramite.setFecha(LocalDateTime.now());
            tramite.setTipo("Pago");
            session.persist(tramite);

            //HQL
            Query<Tramite> query = session.createQuery("from Tramite where tipo = :tipoTram", Tramite.class).setParameter("tipoTram", "Credito");

            List<Tramite> tramites = query.getResultList();
            tramites.forEach( i -> System.out.println(i));

            System.out.println("--------------------------------------------------");

            //data binding
            Query<Tramite> query2 = session.createQuery("from Tramite where tipo = ?1", Tramite.class).setParameter(1, "Credito");
            Tramite tramite1 = query2.setMaxResults(1).getSingleResult();
            System.out.println(tramite1);

            //getResultList() --> lista, 0 a + de 1 resultado
            //getSingleResult() --> exactament 1 resultado
            //getresultStream --> usa cursores en la db

            try(Stream<Tramite> tramiteStream = session.createQuery("from Tramite where tipo = :tipoTram", Tramite.class).
                    setParameter("tipoTram", "Pago").getResultStream()){
                List<Tramite> t = tramiteStream.skip(2). limit(1).collect(Collectors.toList());
                t.forEach(i -> System.out.println(i));
            }

            System.out.println("--------------------------------------------------");

            //Scrollbar

            try(ScrollableResults<Tramite> scrollableResults = session.createQuery("from Tramite where tipo = :tipoTram", Tramite.class)
                    .setParameter("tipoTram", "Credito").scroll()){

                while (scrollableResults.next()){
                    Tramite t2 = scrollableResults.get();
                    System.out.println(t2);
                }

                //Criteria
                System.out.println("-----------------------------------------------");

                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Tramite> criteria = builder.createQuery(Tramite.class);
                Root<Tramite> root = criteria.from(Tramite.class);
                criteria.select(root).where(builder.equal(root.get(Tramite_.tipo), "Credito"));

                Tramite tramite2 = session.createQuery(criteria).setMaxResults(1).getSingleResult();
                System.out.println(tramite2);

            }

            session.getTransaction().commit();
        }catch (Exception e){
            if (session !=null && session.getTransaction()!=null){
                session.getTransaction().rollback();
            }
        }
        finally {
            if (session!=null)
                session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}
