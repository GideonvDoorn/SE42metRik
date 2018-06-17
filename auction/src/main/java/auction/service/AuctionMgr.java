package auction.service;

import auction.dao.ItemDAOJPAImpl;
import auction.domain.Category;
import nl.fontys.util.Money;
import auction.domain.Bid;
import auction.domain.Item;
import auction.domain.User;
import java.util.ArrayList;
import java.util.List;

public class AuctionMgr  {

    ItemDAOJPAImpl DAO;

    public AuctionMgr(){
        DAO = new ItemDAOJPAImpl();
    }

   /**
     * @param id
     * @return het item met deze id; als dit item niet bekend is wordt er null
     *         geretourneerd
     */
    public Item getItem(Long id) {

        return DAO.find(id);
    }

  
   /**
     * @param description
     * @return een lijst met items met @desciption. Eventueel lege lijst.
     */
    public List<Item> findItemByDescription(String description) {

        ArrayList<Item> list = new ArrayList<Item>(DAO.findByDescription(description));

        return list;
    }

    /**
     * @param item
     * @param buyer
     * @param amount
     * @return het nieuwe bod ter hoogte van amount op item door buyer, tenzij
     *         amount niet hoger was dan het laatste bod, dan null
     */
    public Bid newBid(Item item, User buyer, Money amount) {
        Bid newBid = item.newBid(buyer, amount);

        DAO.edit(item);

        return newBid;
    }


    public Category getCategory(int id) {

        return DAO.getCategoryById(id);
    }
}
