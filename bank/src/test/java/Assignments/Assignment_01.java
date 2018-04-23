package Assignments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class Assignment_01 {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");


    @BeforeEach
    void setUp() {

        EntityManager em = emf.createEntityManager();
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

    }

}