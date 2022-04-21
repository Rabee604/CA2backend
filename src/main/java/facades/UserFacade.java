package facades;

import entities.Boat;
import entities.Owner;
import entities.User;
import security.errorhandling.AuthenticationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public List<Owner> getAllOwner() {
        EntityManager em = emf.createEntityManager();
        List<Owner> OwnerSet = new ArrayList<>();
        try {
            OwnerSet = em.createQuery("SELECT p FROM Owner p", Owner.class).getResultList();


            return OwnerSet;

        } finally {
            em.close();
        }

    }

    public Boat createBoat(String brand, String make, String name) {

        EntityManager em = emf.createEntityManager();

        Boat boat = new Boat(brand, make, name);

        try {
            em.getTransaction().begin();
            em.persist(boat);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return boat;

    }
}


