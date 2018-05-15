package auction.service;

import auction.dao.UserDAOJPAImpl;
import auction.domain.User;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionTest {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");

    @Test
    public void TestConnection(){

    }

}
