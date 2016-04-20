package ind.br.vedax.exceptions;

/**
 *
 * @author GabrielOutor
 */
public class DBException extends RuntimeException{
    
    public DBException(DBExceptionEnum msg){
        super(msg.toString());
    }
    
}
