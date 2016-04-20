package ind.br.vedax.model.dao;

import ind.br.vedax.model.entities.Employee;
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
public class EmployeeDAO implements GenericDAO<Employee>{
    
    private EntityManager em;
    
    @Override
    public void create(Employee e) {
        em.persist(e);
    }

    @Override
    public List<Employee> read() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    @Override
    public void delete(Employee e) {
        em.remove(e);
    }
    
    @Override
    public Employee readById(Long id){
        return em.find(Employee.class, id);
    }
    
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    public Employee readByIdUser(Long id){
        return em.createQuery("SELECT e FROM Employee e WHERE e.idUser.idUser=:id", Employee.class).setParameter("id", id).getSingleResult();
    }
}
