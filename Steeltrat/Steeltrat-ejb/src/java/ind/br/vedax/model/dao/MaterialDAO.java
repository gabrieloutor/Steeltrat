package ind.br.vedax.model.dao;

import ind.br.vedax.model.entities.Material;
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
public class MaterialDAO implements GenericDAO<Material>{
    
    private EntityManager em;
    
    @Override
    public void create(Material e) {
        em.persist(e);
    }

    @Override
    public List<Material> read() {
        return em.createNamedQuery("Material.findAll", Material.class).getResultList();
    }

    @Override
    public void delete(Material e) {
        em.remove(e);
    }
    
    @Override
    public Material readById(Long id){
        return em.find(Material.class, id);
    }
    
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
