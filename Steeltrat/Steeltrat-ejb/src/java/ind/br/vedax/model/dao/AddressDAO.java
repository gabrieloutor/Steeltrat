package ind.br.vedax.model.dao;

import ind.br.vedax.exceptions.DBException;
import ind.br.vedax.exceptions.DBExceptionEnum;
import ind.br.vedax.model.entities.Address;
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
public class AddressDAO implements GenericDAO<Address>{
    
    private EntityManager em;
    
    @Override
    public void create(Address e) {
        try {
            em.persist(e);
        } catch (Exception ex) {
            throw new DBException(DBExceptionEnum.PERSIST_ERROR);
        }
    }

    @Override
    public List<Address> read() {
        List<Address> lista = em.createNamedQuery("Address.findAll", Address.class).getResultList();
        if (lista == null || lista.isEmpty()) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return lista;
    }

    @Override
    public void delete(Address e) {
        try {
            em.remove(e);
        } catch (Exception ex) {
            throw new DBException(DBExceptionEnum.REMOVE_ERROR);
        }
    }
    
    @Override
    public Address readById(Long id){
        Address e = em.find(Address.class, id);
        if (e == null) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return e;
    }
    
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    public Address readByAddress(String zipcode, Integer numberAddress){
        Query query = em.createNamedQuery("Address.findByZipcodeAndNumberAddress", Address.class).setParameter("zipcode", zipcode).setParameter("numberAddress", numberAddress);
        if(query.getResultList().isEmpty()){
            return null;
        }
        return (Address)query.getSingleResult();
    }
}
