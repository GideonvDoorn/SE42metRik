package auction.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String description;

//    private Category() {
//        description = "undefined";
//    }

    public Category(String description) {
        this.description = description;
    }

    public Category(){

    }
    public String getDiscription() {
        return description;
    }
}
