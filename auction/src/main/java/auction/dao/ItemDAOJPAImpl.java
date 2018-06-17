package auction.dao;

import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;

import javax.persistence.*;
import java.util.List;

public class ItemDAOJPAImpl implements ItemDAO
{
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
    private EntityManager em;

    public ItemDAOJPAImpl(){
        em = emf.createEntityManager();
    }

    @Override
    public int count() {
        Query countQuery = em.createNamedQuery("Item.getCount", Item.class);

        long count = (long)countQuery.getSingleResult();

        return  (int)count;
    }

    @Override
    public void create(Item item) {
        Query findById = em.createNamedQuery("Item.findById", Item.class);
        findById.setParameter("id", item.getId());

        if (!findById.getResultList().isEmpty()) {
            throw new EntityExistsException();
        }

        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
    }

    @Override
    public void edit(Item item) {
        Query findById = em.createNamedQuery("Item.findById", Item.class);
        findById.setParameter("id", item.getId());

        if (findById.getResultList().isEmpty()) {
            throw new IllegalArgumentException();
        }

        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
    }

    @Override
    public Item find(Long id) {
        try{
            Query findById = em.createNamedQuery("Item.findById", Item.class);
            findById.setParameter("id", id);

            return (Item)findById.getSingleResult();
        }
        catch(NoResultException ex){
            return null;
        }
    }

    @Override
    public List<Item> findAll() {
        Query findAll = em.createNamedQuery("Item.getAll", Item.class);

        return findAll.getResultList();
    }

    @Override
    public List<Item> findByDescription(String description) {
        try{
            Query findByDescr = em.createNamedQuery("Item.findByDescr", Item.class);
            findByDescr.setParameter("descr", description);

            return findByDescr.getResultList();
        }
        catch(NoResultException ex){
            return null;
        }
    }

    @Override
    public void remove(Item item) {
        Query findByItem = em.createNamedQuery("Item.findById", Item.class);
        findByItem.setParameter("id", item.getId());

        if (findByItem.getResultList().isEmpty()) {
            throw new IllegalArgumentException();
        }

        em.getTransaction().begin();
        em.remove(item);
        em.getTransaction().commit();
    }

    public Category getCategoryById(int id) {
        try{
            Query findById = em.createQuery("SELECT a FROM Category AS a  WHERE a.id = :id");
            findById.setParameter("id", id);

            return (Category)findById.getSingleResult();
        }
        catch(NoResultException ex){
            return null;
        }
    }
}
