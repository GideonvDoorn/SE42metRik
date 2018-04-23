package Assignments;

import org.junit.jupiter.api.BeforeEach;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class Assignment_01 {
    @BeforeEach
    void setUp() {

        DatabaseCleaner dbc = new DatabaseCleaner(/* here is entit ymanag er */);
        try{
            dbc.clean();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

}