package auction.dao;

import auction.domain.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.*;

public class UserDAOJPAImpl implements UserDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("auctionPU");
private EntityManager em;

    private HashMap<String, User> users;

    public UserDAOJPAImpl() {
        users = new HashMap<String, User>();
        em = emf.createEntityManager();
    }

    @Override
    public int count() {

        Query q = em.createNamedQuery("User.getCount", User.class);

        long l = (long)q.getSingleResult();
        return (int)l;
    }

    @Override
    public void create(User user) {
        Query findByEmail = em.createNamedQuery("User.findByEmail", User.class);
        findByEmail.setParameter("email", user.getEmail());

        if (!findByEmail.getResultList().isEmpty()) {
            throw new EntityExistsException();
        }

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void edit(User user) {
        Query findById = em.createNamedQuery("User.findById", User.class);
        findById.setParameter("id", user.getId());

        if (findById.getResultList().isEmpty()) {
            throw new IllegalArgumentException();
        }

        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();


    }


    @Override
    public List<User> findAll() {
        Query findAll = em.createNamedQuery("User.getAll", User.class);

        return findAll.getResultList();
    }

    @Override
    public User findByEmail(String email) {
        try{
            Query findByEmail = em.createNamedQuery("User.findByEmail", User.class);
            findByEmail.setParameter("email", email);

            return (User)findByEmail.getSingleResult();
        }
        catch(NoResultException ex){
            return null;
        }

    }

    @Override
    public void remove(User user) {
        Query findById = em.createNamedQuery("User.findById", User.class);
        findById.setParameter("id", user.getId());

        if (findById.getResultList().isEmpty()) {
            throw new IllegalArgumentException();
        }

        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }
}
