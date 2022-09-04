package io.codewith.gxstar;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.Test;

class  HelloWorldJPATest {


    @Test
    void storeLoadMessage() {
        final EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("ch02.ex01");

        try {
            final EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Message message = new Message();
            message.setText("Hello World!");

            em.persist(message);

            em.getTransaction().commit();

            em.getTransaction().begin();

            List<Message> messages = em.createQuery("SELECT m FROM Message m").getResultList();

            messages.get(messages.size() - 1).setText("Hello World From JPA!");
            em.getTransaction().commit();

            assertAll(
                    () -> assertEquals(1, messages.size()),
                    () -> assertEquals("Hello World From JPA!", messages.get(0).getText())
            );

            em.close();


        } finally {
            emf.close();
        }
    }

}