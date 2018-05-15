package auction.domain;

import javax.persistence.Entity;
import javax.persistence.*;



@Entity

@NamedQueries({
        @NamedQuery(name = "User.getCount", query = "SELECT COUNT(a) FROM User AS a"),
        @NamedQuery(name = "User.findByEmail", query = "SELECT a FROM User AS a WHERE a.email = :email"),
        @NamedQuery(name = "User.findById", query = "SELECT a FROM User AS a WHERE a.id = :id"),
        @NamedQuery(name = "User.getAll", query = "SELECT a FROM User AS a")

})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    public User(String email) {
        this.email = email;

    }

    public User(){

    }

    public Long getId(){
        return this.id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
