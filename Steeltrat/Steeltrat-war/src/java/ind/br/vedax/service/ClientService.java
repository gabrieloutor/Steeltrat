package ind.br.vedax.service;

import ind.br.vedax.model.dao.ClientDAO;
import ind.br.vedax.model.entities.Client;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author 1147106
 */
@Stateless
@Path("client")
public class ClientService {
    ClientDAO clientDAO = lookupClientDAOBean();
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    @GET
    @Produces({"application/xml"})
    public List<Client> findAll(){
        emf = Persistence.createEntityManagerFactory("steeltrat_pu");
        em = emf.createEntityManager();
        clientDAO.setEm(em);
        em.getTransaction().begin();
        
        List<Client> addresses = clientDAO.read();
        
        em.getTransaction().commit();
        em.close();
        emf.close();
        
        return addresses;
    } 
    
    @GET
    @Produces({"application/xml","application/json"})
    @Path("{id}")
    public Client findById(@PathParam("id") long id){
        emf = Persistence.createEntityManagerFactory("steeltrat_pu");
        em = emf.createEntityManager();
        clientDAO.setEm(em);
        em.getTransaction().begin();
        
        Client a = clientDAO.readById(id);
        
        em.getTransaction().commit();
        em.close();
        emf.close();
        
        return a;
    }
    
    private ClientDAO lookupClientDAOBean() {
        try {
            Context c = new InitialContext();
            return (ClientDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/ClientDAO!ind.br.vedax.model.dao.ClientDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
