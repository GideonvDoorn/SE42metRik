package auction.service;

import auction.dao.ItemDAOJPAImpl;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;

public class SellerMgr {

    ItemDAOJPAImpl DAO;

    public SellerMgr(){
        DAO = new ItemDAOJPAImpl();
    }


    /**
     * @param seller
     * @param cat
     * @param description
     * @return het item aangeboden door seller, behorende tot de categorie cat
     *         en met de beschrijving description
     */
    public Item offerItem(User seller, Category cat, String description) {

        Item newItem = new Item(seller, cat, description);

        DAO.create(newItem);

        return newItem;
    }
    
     /**
     * @param item
     * @return true als er nog niet geboden is op het item. Het item word verwijderd.
     *         false als er al geboden was op het item.
     */
     public boolean revokeItem(Item item) {
         if(item.getHighestBid() == null)
         {
             DAO.remove(item);
             return true;
         }
         return false;
     }
}
