package auction.domain;

import nl.fontys.util.Money;
import org.eclipse.persistence.jpa.config.Cascade;

import javax.persistence.*;


@Entity

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
    @ManyToOne(cascade = CascadeType.PERSIST)
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

    public boolean equals(Object o) {
        //TODO
        return false;
    }

    public int hashCode() {
        //TODO
        return 0;
    }
}
