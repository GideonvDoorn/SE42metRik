package auction.domain;

import com.sun.org.apache.xpath.internal.operations.Equals;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.*;


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

    @OneToMany(cascade = CascadeType.PERSIST,  mappedBy = "seller", orphanRemoval = true)
    private Set<Item> offeredItems = new HashSet<>();

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

    public Iterator getOfferedItems(){
        return offeredItems.iterator();
    }

    void addItem(Item itemToAdd){
        offeredItems.add(itemToAdd);
    }

    public int numberOfOfferedItems(){
        return offeredItems.size();
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }

        User u = (User)obj;



        return this.id.equals(u.id) && this.email.equals(u.email) && this.offeredItems.equals(u.offeredItems);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, offeredItems);
    }
}
