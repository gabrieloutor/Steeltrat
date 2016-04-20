package ind.br.vedax.model.dao;

import ind.br.vedax.model.entities.PositionSteeltrat;
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
public class PositionSteeltratDAO implements GenericDAO<PositionSteeltrat>{
    
    private EntityManager em;
    
    @Override
    public void create(PositionSteeltrat e) {
        em.persist(e);
    }

    @Override
    public List<PositionSteeltrat> read() {
        return em.createNamedQuery("PositionSteeltrat.findAll", PositionSteeltrat.class).getResultList();
    }

    @Override
    public void delete(PositionSteeltrat e) {
        em.remove(e);
    }
    
    @Override
    public PositionSteeltrat readById(Long id){
        return em.find(PositionSteeltrat.class, id);
    }
    
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
