package ind.br.vedax.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author 1147106
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(ind.br.vedax.service.ClientService.class);
        resources.add(ind.br.vedax.service.AddressService.class);
        return super.getClasses(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
