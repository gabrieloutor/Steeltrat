package ind.br.vedax.model.dao;

import ind.br.vedax.exceptions.DBException;
import ind.br.vedax.exceptions.DBExceptionEnum;
import ind.br.vedax.model.entities.UserSteeltrat;
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
public class UserSteeltratDAO implements GenericDAO<UserSteeltrat>{
    
    private EntityManager em;
    
    @Override
    public void create(UserSteeltrat e) {
        try {
            em.persist(e);
        } catch (Exception ex) {
            throw new DBException(DBExceptionEnum.PERSIST_ERROR);
        }
    }

    @Override
    public List<UserSteeltrat> read() {
        List<UserSteeltrat> lista = em.createNamedQuery("UserSteeltrat.findAll", UserSteeltrat.class).getResultList();
        if (lista == null || lista.isEmpty()) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return lista;
    }

    @Override
    public void delete(UserSteeltrat e) {
        try {
            em.remove(e);
        } catch (Exception ex) {
            throw new DBException(DBExceptionEnum.REMOVE_ERROR);
        }
    }
    
    @Override
    public UserSteeltrat readById(Long id){
        UserSteeltrat e = em.find(UserSteeltrat.class, id);
        if (e == null) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return e;
    }
    
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    public UserSteeltrat readForLogin(String username, String password){
        Query query = em.createNamedQuery("UserSteeltrat.findForLogin", UserSteeltrat.class).setParameter("username", username).setParameter("password", password);
        if(query.getResultList().isEmpty()){
            return null;
        }
        return (UserSteeltrat)query.getSingleResult();
    }
}
