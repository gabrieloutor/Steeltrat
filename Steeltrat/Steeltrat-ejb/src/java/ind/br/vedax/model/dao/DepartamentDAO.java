package ind.br.vedax.model.dao;

import ind.br.vedax.exceptions.DBException;
import ind.br.vedax.exceptions.DBExceptionEnum;
import ind.br.vedax.model.entities.Departament;
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
public class DepartamentDAO implements GenericDAO<Departament>{
    
    private EntityManager em;
    
    @Override
    public void create(Departament e) {
        try {
            em.persist(e);
        } catch (Exception ex) {
            throw new DBException(DBExceptionEnum.PERSIST_ERROR);
        }
    }

    @Override
    public List<Departament> read() {
        List<Departament> lista = em.createNamedQuery("Departament.findAll", Departament.class).getResultList();
        if (lista == null || lista.isEmpty()) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return lista;
    }

    @Override
    public void delete(Departament e) {
        try {
            em.remove(e);
        } catch (Exception ex) {
            throw new DBException(DBExceptionEnum.REMOVE_ERROR);
        }
    }
    
    @Override
    public Departament readById(Long id){
        Departament e = em.find(Departament.class, id);
        if (e == null) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return e;
    }
    
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    public Departament readByName(String nameDepartament){
        Query query = em.createNamedQuery("Departament.findByNameDepartament", Departament.class).setParameter("nameDepartament", nameDepartament);
        if(query.getResultList().isEmpty()){
            return null;
        }
        return (Departament)query.getSingleResult();
    }
}
