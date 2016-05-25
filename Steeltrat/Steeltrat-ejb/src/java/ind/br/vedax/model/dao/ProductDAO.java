package ind.br.vedax.model.dao;

import ind.br.vedax.exceptions.DBException;
import ind.br.vedax.exceptions.DBExceptionEnum;
import ind.br.vedax.model.entities.Product;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author GabrielOutor
 */
@Stateless
@LocalBean
public class ProductDAO implements GenericDAO<Product>{
    
    private EntityManager em;
    
    @Override
    public void create(Product e) {
        try {
            em.persist(e);
        } catch (Exception ex) {
            throw new DBException(DBExceptionEnum.PERSIST_ERROR);
        }
    }

    @Override
    public List<Product> read() {
        List<Product> lista = em.createNamedQuery("Product.findAll", Product.class).getResultList();
        if (lista == null || lista.isEmpty()) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return lista;
    }

    @Override
    public void delete(Product e) {
        try {
            em.remove(e);
        } catch (Exception ex) {
            throw new DBException(DBExceptionEnum.REMOVE_ERROR);
        }
    }
    
    @Override
    public Product readById(Long id){
        Product e = em.find(Product.class, id);
        if (e == null) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return e;
    }
    
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    public Product readByDescription(String descriptionProduct){
        Query query = em.createNamedQuery("Product.findByDescriptionProduct", Product.class).setParameter("descriptionProduct", descriptionProduct);
        if(query.getResultList().isEmpty()){
            return null;
        }
        return (Product)query.getSingleResult();
    }
}
