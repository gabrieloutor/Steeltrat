package ind.br.vedax.model.dao;

import ind.br.vedax.exceptions.DBException;
import ind.br.vedax.exceptions.DBExceptionEnum;
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
        try {
            em.persist(e);
        } catch (Exception ex) {
            throw new DBException(DBExceptionEnum.PERSIST_ERROR);
        }
    }

    @Override
    public List<Employee> read() {
        List<Employee> lista = em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
        if (lista == null || lista.isEmpty()) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return lista;
    }

    @Override
    public void delete(Employee e) {
        try {
            em.remove(e);
        } catch (Exception ex) {
            throw new DBException(DBExceptionEnum.REMOVE_ERROR);
        }
    }
    
    @Override
    public Employee readById(Long id){
        Employee e = em.find(Employee.class, id);
        if (e == null) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return e;
    }
    
    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    public Employee readByIdUser(Long id){
        Employee e = em.createQuery("SELECT e FROM Employee e WHERE e.idUser.idUser=:id", Employee.class).setParameter("id", id).getSingleResult();
        if (e == null) {
            throw new DBException(DBExceptionEnum.FIND_ERROR);
        }
        return e;
    }
}
