package ind.br.vedax.enums;
/**
 *
 * @author GabrielOutor
 */
public enum LogEnum {
    CREATE_MESSAGE{
        @Override
        public String toString(){
            return " inseriu dados no sistema!";
        }
    },
    
    UPDATE_MESSAGE{
        @Override
        public String toString(){
            return " atualizou dados no sistema!";
        }
    },
    
    DELETE_MESSAGE{
        @Override
        public String toString(){
            return " deletou dados no sistema!";
        }
    },
    
    LOGOUT_MESSAGE{
        @Override
        public String toString(){
            return " logou com sucesso!";
        }
    },
    
    LOGIN_MESSAGE{
        @Override
        public String toString(){
            return " acessou o sistema!";
        }
    },
    
    ERROR_MESSAGE{
        @Override
        public String toString(){
            return "Sistema gerou erro com o usuário: ";
        }
    },
    
    INPUT_ERROR_MESSAGE{
        @Override
        public String toString(){
            return "Erro na digitação dos dados por: ";
        }
    },
    
    CONNECT_ERROR_MESSAGE{
        @Override
        public String toString(){
            return "Falha na conexão ao banco de dados!";
        }
    }
}
