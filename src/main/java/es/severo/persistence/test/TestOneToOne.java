package es.severo.persistence.test;

import es.severo.persistence.entity.Presupuesto;
import es.severo.persistence.entity.Tramite;
import es.severo.persistence.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

public class TestOneToOne {

    public static void main(String[] args) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            try {
                session.beginTransaction();
                /* Tramite tramite = new Tramite();
                tramite.setTipo("Aval");
                tramite.setFecha(LocalDateTime.now());
                session.persist(tramite);

                Presupuesto p = new Presupuesto();
                p.setLugarPresupuesto("Elche");
                p.setTramite(tramite);
                session.persist(p);


                session.flush();

                session.getTransaction().commit();

                 */

                //consultar tramite al que pertenece un presupuesto
                //cargar el presupuesto

                /*Presupuesto pp = session.load(Presupuesto.class, 1L);
                pp.setLugarPresupuesto("Alicante");
                session.merge(pp);
                System.out.println(pp);


                 */
                //Consultar rodos los tramites

                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Presupuesto> criteria = builder.createQuery(Presupuesto.class);
                Root<Presupuesto> root = criteria.from(Presupuesto.class);
                criteria.select(root);
                List<Presupuesto> presupuestos = session.createQuery(criteria)
                        .getResultList();
                presupuestos.forEach(System.out::println);

                /*Presupuesto pp = session.find(Presupuesto.class, 3L);

                session.remove(pp);
                 */

                //Quiero que al borrar el tramite se borre el presupuesto

                /*Tramite t = session.find(Tramite.class, 2L);
                session.remove(t);

                 */
                session.getTransaction().commit();
            }catch (RuntimeException ex){
                if (session.getTransaction()!=null){
                    session.getTransaction().rollback();
                    throw ex;
                }
            }
        }
    }
}
