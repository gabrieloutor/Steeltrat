package ind.br.vedax.model.dao;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author GabrielOutor
 */

public interface GenericDAO<E> {
    void create(E e);
    List<E> read();
    void delete(E e);
    E readById(Long id);
    
    void setEm(EntityManager em);
}
