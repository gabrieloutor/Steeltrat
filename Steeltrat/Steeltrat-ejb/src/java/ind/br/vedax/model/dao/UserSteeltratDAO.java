package ind.br.vedax.model.dao;

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
        em.persist(e);
    }

    @Override
    public List<UserSteeltrat> read() {
        return em.createNamedQuery("UserSteeltrat.findAll", UserSteeltrat.class).getResultList();
    }

    @Override
    public void delete(UserSteeltrat e) {
        em.remove(e);
    }
    
    @Override
    public UserSteeltrat readById(Long id){
        return em.find(UserSteeltrat.class, id);
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
