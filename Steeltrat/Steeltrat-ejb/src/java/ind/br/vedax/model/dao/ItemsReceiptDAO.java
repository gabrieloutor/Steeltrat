package ind.br.vedax.model.dao;

import ind.br.vedax.exceptions.DBException;
import ind.br.vedax.exceptions.DBExceptionEnum;
import ind.br.vedax.model.entities.ItemsReceipt;
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
        try {
            em.persist(e);
        } catch (Exception ex) {
            throw new DBException(DBExceptionEnum.PERSIST_ERROR);
        }
    }

    @Override
    public List<ItemsReceipt> read() {
        List<ItemsReceipt> lista = em.createNamedQuery("ItemsReceipt.findAll", ItemsReceipt.class).getResultList();
        if (lista == null || lista.isEmpty()) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return lista;
    }

    @Override
    public void delete(ItemsReceipt e) {
        try {
            em.remove(e);
        } catch (Exception ex) {
            throw new DBException(DBExceptionEnum.REMOVE_ERROR);
        }
    }
    
    @Override
    public ItemsReceipt readById(Long id){
        ItemsReceipt e = em.find(ItemsReceipt.class, id);
        if (e == null) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return e;
    }
    
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    public Long lastNumber(Long idReceipt){
        Long e = Long.parseLong(em.createNamedQuery("ItemsReceipt.findLastNumberByIdItemReceipt", ItemsReceipt.class).setParameter("idReceipt", idReceipt).getSingleResult().toString()+1);
        if (e == null) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return e;
    }
}
