package ind.br.vedax.model.dao;

import ind.br.vedax.model.entities.Standard;
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
public class StandardDAO implements GenericDAO<Standard>{
    
    private EntityManager em;
    
    @Override
    public void create(Standard e) {
        em.persist(e);
    }

    @Override
    public List<Standard> read() {
        return em.createNamedQuery("Standard.findAll", Standard.class).getResultList();
    }

    @Override
    public void delete(Standard e) {
        em.remove(e);
    }
    
    @Override
    public Standard readById(Long id){
        return em.find(Standard.class, id);
    }
    
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
