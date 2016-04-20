package ind.br.vedax.model.dao;

import ind.br.vedax.model.entities.ItemsReceipt;
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
public class ItemsReceiptDAO implements GenericDAO<ItemsReceipt>{
    
    private EntityManager em;
    
    @Override
    public void create(ItemsReceipt e) {
        em.persist(e);
    }

    @Override
    public List<ItemsReceipt> read() {
        return em.createNamedQuery("ItemsReceipt.findAll", ItemsReceipt.class).getResultList();
    }
    
    public Long lastNumber(Long idReceipt){
        return Long.parseLong(em.createNamedQuery("ItemsReceipt.findLastNumberByIdItemReceipt", ItemsReceipt.class).setParameter("idReceipt", idReceipt).getSingleResult().toString()+1);
    }
    
    @Override
    public void delete(ItemsReceipt e) {
        em.remove(e);
    }
    
    @Override
    public ItemsReceipt readById(Long id){
        return em.find(ItemsReceipt.class, id);
    }
    
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
