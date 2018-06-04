package auction.domain;

import nl.fontys.util.Money;
import org.eclipse.persistence.jpa.config.Cascade;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Item.getCount", query = "SELECT COUNT(a) FROM Item AS a"),
        @NamedQuery(name = "Item.findById", query = "SELECT a FROM Item AS a WHERE a.id = :id"),
        @NamedQuery(name = "Item.findByDescr", query = "SELECT a FROM Item AS a WHERE a.description = :descr"),
        @NamedQuery(name = "Item.getAll", query = "SELECT a FROM Item AS a")

})

public class Item implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private User seller;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

    private String description;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Bid highest;

    public Item(User seller, Category category, String description) {
        this.seller = seller;
        this.category = category;
        this.description = description;
        seller.addItem(this);


    }

    public Item(){

    }

    public Long getId() {
        return id;
    }

    public User getSeller() {
        return seller;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descr){
        description = descr;
    }

    public Bid getHighestBid() {
        return highest;
    }

    public Bid newBid(User buyer, Money amount) {
        if (highest != null && highest.getAmount().compareTo(amount) >= 0) {
            return null;
        }
        highest = new Bid(buyer, amount);
        return highest;
    }

    public int compareTo(Object arg0) {
        //TODO
        return -1;
    }

    public boolean equals(Object obj) {

        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }

        Item i = (Item)obj;

        return this.id.equals(i.id)  && this.category.equals(i.category)&& this.description.equals(i.description)&& (this.highest == i.highest || this.highest.equals(i.highest));
    }

    public int hashCode() {
        return Objects.hash(id, category, description, highest);
    }
}
