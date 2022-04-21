package utils;
import entities.Boat;
import entities.Owner;
import facades.UserFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tha
 */
public class Generate {


    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        UserFacade userFacade = UserFacade.getUserFacade(EMF_Creator.createEntityManagerFactory());

       /* try {
            Boat boat = new Boat("brand", "Honda", "Dada");
            Boat boat1 = new Boat("Honda", "baba","Mama" );
            Boat boat2 = new Boat("brand2", "Honda", "Nano");
            Boat boat3 = new Boat("Honda2", "baba","Mormor" );
            Boat boat4 = new Boat("brand3", "Honda", "Farfar");
            Boat boat5 = new Boat("Honda4", "baba","Mor" );
            Owner owner = new Owner("Nadia", "AA", "123");
            Owner owner1 = new Owner("Rabee", "AAq", "124");
            em.getTransaction().begin();
            em.persist(boat);
            em.persist(boat1);
            em.persist(boat2);
            em.persist(boat3);
            em.persist(boat4);
            em.persist(boat5);
            em.persist(owner);
            em.persist(owner1);
            owner.addBoat(boat);
            owner.addBoat(boat1);
            owner1.addBoat(boat2);
            owner1.addBoat(boat3);
            owner1.addBoat(boat4);
            owner1.addBoat(boat5);


            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }*/
        System.out.println(userFacade.getAllOwner());

    }
}

