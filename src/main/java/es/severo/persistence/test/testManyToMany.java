package es.severo.persistence.test;

import es.severo.persistence.entity.Address;
import es.severo.persistence.entity.Person;
import es.severo.persistence.util.HibernateUtil;
import org.hibernate.Session;

public class testManyToMany {
    public static void main(String[] args) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            try{
                session.beginTransaction();


                Person person = new Person("JuanPablo");
                Person person2 = new Person("Yisus");
                Person person3= new Person("Paquito");

                Address address = new Address("Oriolanos Ausentes", "4A");
                Address address2 = new Address("Duko Tamones", "7");
                Address address3 = new Address("Sarandonga", "57");

                person.getAddresses().add(address2);
                person2.getAddresses().add(address);
                person3.getAddresses().add(address3);
                session.persist(person);
                session.persist(person2);
                session.persist(person3);


                session.getTransaction().commit();
            }catch (RuntimeException e){
                if (session.getTransaction() != null)
                    session.getTransaction().rollback();
                throw e;
            }
        }
    }
}
