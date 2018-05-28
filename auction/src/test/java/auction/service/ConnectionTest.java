package auction.service;

import auction.dao.ItemDAOJPAImpl;
import auction.dao.UserDAOJPAImpl;
import auction.domain.Account;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;
import nl.fontys.util.DatabaseCleaner;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectionTest {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
    private EntityManager em;
    private EntityManager em1;

    @Before
    public void SetUp(){
        em = emf.createEntityManager();   em1 = emf.createEntityManager();

        DatabaseCleaner dbc = new DatabaseCleaner(em);
        try {
            dbc.clean();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseCleaner dbc2 = new DatabaseCleaner(em1);
        try {
            dbc2.clean();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        em = emf.createEntityManager();
        em1 = emf.createEntityManager();
    }

    @Test
    public void TestConnection(){
        em = emf.createEntityManager();

        User user = new User("mail");
        em.getTransaction().begin();

        em.persist(user);
        assertNull(user.getId());
        em.getTransaction().commit();

        assertTrue(user.getId() > 0L);


    }

    @Test
    public void test2() {
        em = emf.createEntityManager();

        Long expected = 200L;
        Account account = new Account(112L);
        Account merged;
        account.setId(332L);
        em.getTransaction().begin();

        merged = em.merge(account);
        em.getTransaction().commit();
        merged.setBalance(expected);

        em.close();

        assertEquals(expected, merged.getBalance());
        assertEquals (account, merged);
    }
    @Test
    public void test3() {
        em = emf.createEntityManager();
        DatabaseCleaner dbc = new DatabaseCleaner(em);
        try {
            dbc.clean();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        em = emf.createEntityManager();

        Long expected = 100L;
        Account account = new Account(111L);
        account.setId(331L);
        Account merged;
        em.getTransaction().begin();

        merged = em.merge(account);
        assertEquals(merged.getId(), account.getId());
        em.getTransaction().commit();
        assertTrue(merged.getId() != account.getId());
        assertFalse(em.contains(merged));
        assertFalse(em.contains(account));

        em.close();

        assertSame(merged, account);
        assertEquals(merged, account);
    }

    @Test
    public void test4() {

        em = emf.createEntityManager();
        DatabaseCleaner dbc = new DatabaseCleaner(em);
        try {
            dbc.clean();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        em = emf.createEntityManager();

        Account account1 = new Account();
        Account account2;
        em.persist(account1);
        em.getTransaction().begin();
        account1.setAccountNr(234L);
        em.getTransaction().commit();
        account2 = em.find(Account.class, account1.getId());
        em.persist(account2);
        account1.setAccountNr(345L);
        assertTrue(em.contains(account2));
        assertNotNull(account1.getId());
        em.close();
        assertSame(account1, account2);
        assertNotNull(account1.getId());
    }
    @Test
    public void test5() {
        Account acc = new Account(1L);
        Account acc2 = new Account(2L);

        em.getTransaction().begin();



        acc2 = em.merge(acc);
        assertTrue(em.contains(acc2));

        em.getTransaction().commit();

        System.out.println("--- " + acc.getId());
        System.out.println("--- " + acc2.getId());

    }

    @Test
    public void test6() {
        em = emf.createEntityManager();
        DatabaseCleaner dbc = new DatabaseCleaner(em);
        try {
            dbc.clean();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test7() {
        EntityManager em1 = emf.createEntityManager();

        Long accountNr = 111L;
        Account account, account2;
        Long balance = 1L;
        account = new Account(accountNr);
        account.setBalance(balance);

        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();

        em1.getTransaction().begin();
        account2 = em1.find(Account.class, account.getId());
        em1.remove(account2);

        assertEquals((Long) 1L, account.getBalance());
        assertEquals((Long) 1L, account2.getBalance());
        assertNotEquals(0, TestUtil.getNrOfAccountRecordsInDB());
        em1.getTransaction().commit();
        assertEquals((Long) 1L, account.getBalance());
        assertEquals((Long) 1L, account2.getBalance());
        assertEquals(1, TestUtil.getNrOfAccountRecordsInDB());

    }


    @Test
    public void test8() {
        Account account = new Account();
        Account account1;
        Account account2 = new Account();

        em.persist(account2);
        em.getTransaction().begin();
        em.persist(account);

        account.setAccountNr(5L);
        account.setAccountNr(3L);

        account1 = em.merge(account);

        assertTrue(em.contains(account));

        em.getTransaction().commit();

        assertTrue(em.contains(account1));
        assertNotNull(account.getId());
        assertNull(account2.getId());

    }

    @Test
    public void test0() {
        EntityManager em1 = emf.createEntityManager();


        em.getTransaction().begin();
        Long accountNr = 111L;
        Account account, account2;
        account = new Account(accountNr);
        em.persist(account);
        account.setBalance(1000L);
        em.getTransaction().commit();
        em.close();
        account2 = new Account(accountNr + 1);
        account2.setBalance(2000L);
        em1.getTransaction().begin();
        em1.merge(account2);
        em1.getTransaction().commit();
        int nrRecordsInDatabase = TestUtil.getNrOfAccountRecordsInDB();
        assertEquals(2, nrRecordsInDatabase);
        assertNotSame(account, account2);
        assertFalse(account.equals(account2));

    }


}
