package auction.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Painting extends Item {

    private String title;
    private String painter;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPainter() {
        return painter;
    }

    public void setPainter(String painter) {
        this.painter = painter;
    }

    public Painting(User seller, Category category, String description,String title, String painter) {

        super(seller, category, description);
        this.title = title;
        this.painter = painter;
    }
    public Painting() {

    }
}
