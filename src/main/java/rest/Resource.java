package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Boat;
import entities.Owner;
import entities.User;
import facades.UserFacade;
import utils.EMF_Creator;
import utils.ParallelJokes;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Path("info")
public class Resource {
    Gson gson = new Gson();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    UserFacade userFacade = UserFacade.getUserFacade(EMF_Creator.createEntityManagerFactory());
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {

        EntityManager em = EMF.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("select u from User u", entities.User.class);
            List<User> users = query.getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (user): " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin): " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("jokes")
    public String getJokes() {

        Gson gson = new Gson();

        List<String> jokeList = new ArrayList<>();

        try {
            jokeList = ParallelJokes.getJokes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gson.toJson(jokeList);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("owners")
    public List<String> getAllOwners() {

        Gson gson = new Gson();

        List<String> OwnerList = new ArrayList<>();
        for (Owner s : userFacade.getAllOwner()) {

            OwnerList.add(s.getName());
        }
        return OwnerList;

    }

    @POST
    @Path("/boat")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Boat createBoat(String content) {
        Boat p = gson.fromJson(content,Boat.class);
        return userFacade.createBoat(p.getBrand(),p.getMake(),p.getName());


    }
}