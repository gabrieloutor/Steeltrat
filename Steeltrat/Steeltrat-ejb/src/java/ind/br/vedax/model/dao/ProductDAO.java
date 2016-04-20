package ind.br.vedax.model.dao;

import ind.br.vedax.model.entities.Product;
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
public class ProductDAO implements GenericDAO<Product>{
    
    private EntityManager em;
    
    @Override
    public void create(Product e) {
        em.persist(e);
    }

    @Override
    public List<Product> read() {
        return em.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    @Override
    public void delete(Product e) {
        em.remove(e);
    }
    
    @Override
    public Product readById(Long id){
        return em.find(Product.class, id);
    }
    
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
