package auction.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Furniture extends Item {

    private String material;


    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Furniture() {

    }
    public Furniture(User seller, Category category, String description,String material) {

        super(seller, category, description);
        this.material = material;
    }


}
