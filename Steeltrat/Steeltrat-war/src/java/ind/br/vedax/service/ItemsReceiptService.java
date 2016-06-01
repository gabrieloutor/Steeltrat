package ind.br.vedax.service;

import ind.br.vedax.model.dao.ItemsReceiptDAO;
import ind.br.vedax.model.entities.ItemsReceipt;
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
@Path("itemsreceipt")
public class ItemsReceiptService {
    ItemsReceiptDAO itemsReceiptDAO = lookupItemsReceiptDAOBean();
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    @GET
    @Produces({"application/xml"})
    public List<ItemsReceipt> findAll(){
        emf = Persistence.createEntityManagerFactory("steeltrat_pu");
        em = emf.createEntityManager();
        itemsReceiptDAO.setEm(em);
        em.getTransaction().begin();
        
        List<ItemsReceipt> addresses = itemsReceiptDAO.read();
        
        em.getTransaction().commit();
        em.close();
        emf.close();
        
        return addresses;
    } 
    
    @GET
    @Produces({"application/xml","application/json"})
    @Path("{id}")
    public ItemsReceipt findById(@PathParam("id") long id){
        emf = Persistence.createEntityManagerFactory("steeltrat_pu");
        em = emf.createEntityManager();
        itemsReceiptDAO.setEm(em);
        em.getTransaction().begin();
        
        ItemsReceipt a = itemsReceiptDAO.readById(id);
        
        em.getTransaction().commit();
        em.close();
        emf.close();
        
        return a;
    }
    
    private ItemsReceiptDAO lookupItemsReceiptDAOBean() {
        try {
            Context c = new InitialContext();
            return (ItemsReceiptDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/ItemsReceiptDAO!ind.br.vedax.model.dao.ItemsReceiptDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
