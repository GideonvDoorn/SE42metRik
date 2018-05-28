package Assignments;

import bank.domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class Assignment_01 {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");
    private EntityManager em;

    @BeforeEach
    void setUp() {

        em = emf.createEntityManager();
        DatabaseCleaner dbc = new DatabaseCleaner(em);
        try{
            dbc.clean();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    void testtest(){
        Account account = new Account(111L);
        em.getTransaction().begin();

        em.persist(account);
        assertNull(account.getId());
        em.getTransaction().commit();

        assertTrue(account.getId() > 0L);

    }

}