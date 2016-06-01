package ind.br.vedax.service;

import ind.br.vedax.model.dao.AddressDAO;
import ind.br.vedax.model.entities.Address;
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
@Path("address")
public class AddressService {
    AddressDAO addressDAO = lookupAddressDAOBean();
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    @GET
    @Produces({"application/xml"})
    public List<Address> findAll(){
        emf = Persistence.createEntityManagerFactory("steeltrat_pu");
        em = emf.createEntityManager();
        addressDAO.setEm(em);
        em.getTransaction().begin();
        
        List<Address> addresses = addressDAO.read();
        
        em.getTransaction().commit();
        em.close();
        emf.close();
        
        return addresses;
    } 
    
    @GET
    @Produces({"application/xml","application/json"})
    @Path("{id}")
    public Address findById(@PathParam("id") long id){
        emf = Persistence.createEntityManagerFactory("steeltrat_pu");
        em = emf.createEntityManager();
        addressDAO.setEm(em);
        em.getTransaction().begin();
        
        Address a = addressDAO.readById(id);
        
        em.getTransaction().commit();
        em.close();
        emf.close();
        
        return a;
    }
    
    private AddressDAO lookupAddressDAOBean() {
        try {
            Context c = new InitialContext();
            return (AddressDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/AddressDAO!ind.br.vedax.model.dao.AddressDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
