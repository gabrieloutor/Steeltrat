package ind.br.vedax.exceptions;

/**
 *
 * @author GabrielOutor
 */
public enum DBExceptionEnum {
    PERSIST_ERROR{
        @Override
        public String toString(){
            return "Erro na inserção de dados!";
        }
    },
    
    UPDATE_ERROR{
        @Override
        public String toString(){
            return "Erro na atualização de dados!";
        }
    },
    
    REMOVE_ERROR{
        @Override
        public String toString(){
            return "Erro na remoção de dados!";
        }
    },
    
    FIND_ERROR{
        @Override
        public String toString(){
            return "Erro na busca dos dados!";
        }
    }
}
