package util;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestClass {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");


    @Test
    public void Test(){
        EntityManager em = emf.createEntityManager();
    }
}
