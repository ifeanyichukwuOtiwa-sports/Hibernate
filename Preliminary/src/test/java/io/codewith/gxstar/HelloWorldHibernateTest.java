package io.codewith.gxstar;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Test;

import jakarta.persistence.criteria.CriteriaQuery;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 04/09/2022
 */

class HelloWorldHibernateTest {


    private static SessionFactory createSessionFactory() {
        final Configuration configuration = new Configuration();
        configuration.configure().addAnnotatedClass(Message.class);
        ServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                        .build();
            return configuration.buildSessionFactory(serviceRegistry);

    }

    @Test
    void storeLoadMessage() {
        try (SessionFactory sessionFactory = createSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            Message message = new Message();
            message.setText("Hello World From Hibernate");
            session.persist(message);

            session.getTransaction().commit();


            session.beginTransaction();


            CriteriaQuery<Message> criteriaQuery = session.getCriteriaBuilder().createQuery(Message.class);
            criteriaQuery.from(Message.class);


            List<Message> messages = session.createQuery(criteriaQuery).getResultList();

            session.getTransaction().commit();


            assertAll(
                    () -> assertEquals(1, messages.size()),
                    () -> assertEquals("Hello World From Hibernate", messages.get(0).getText())
            );
        }
    }
}
