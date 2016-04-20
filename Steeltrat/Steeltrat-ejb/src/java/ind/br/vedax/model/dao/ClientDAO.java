package ind.br.vedax.model.dao;

import ind.br.vedax.exceptions.DBException;
import ind.br.vedax.exceptions.DBExceptionEnum;
import ind.br.vedax.model.entities.Client;
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
public class ClientDAO implements GenericDAO<Client>{
    
    private EntityManager em;
    
    @Override
    public void create(Client e) {
        try {
            em.persist(e);
        } catch (Exception ex) {
            throw new DBException(DBExceptionEnum.PERSIST_ERROR);
        }
    }

    @Override
    public List<Client> read() {
        List<Client> lista = em.createNamedQuery("Client.findAll", Client.class).getResultList();
        if (lista == null || lista.isEmpty()) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return lista;
    }

    @Override
    public void delete(Client e) {
        try {
            em.remove(e);
        } catch (Exception ex) {
            throw new DBException(DBExceptionEnum.REMOVE_ERROR);
        }
    }
    
    @Override
    public Client readById(Long id){
        Client e = em.find(Client.class, id);
        if (e == null) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return e;
    }
    
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
