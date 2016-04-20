package ind.br.vedax.model.dao;

import ind.br.vedax.model.entities.Receipt;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author GabrielOutor
 */
@Stateless
@LocalBean
public class ReceiptDAO implements GenericDAO<Receipt>{
    
    private EntityManager em;
    
    @Override
    public void create(Receipt e) {
        em.persist(e);
    }

    @Override
    public List<Receipt> read() {
        return em.createNamedQuery("Receipt.findAll", Receipt.class).getResultList();
    }
    
    public Long lastNumber(){
        return Long.parseLong(em.createNamedQuery("Receipt.findLastNumberByIdReceipt", Receipt.class).getSingleResult().toString()+1);
    }
    
    @Override
    public void delete(Receipt e) {
        em.remove(e);
    }
    
    @Override
    public Receipt readById(Long id){
        return em.find(Receipt.class, id);
    }
    
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
