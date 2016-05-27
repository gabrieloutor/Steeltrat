package ind.br.vedax.enums;
/**
 *
 * @author GabrielOutor
 */
public enum ReturnMsgEnum {
    CREATE_MESSAGE{
        @Override
        public String toString(){
            return "Dados inseridos com sucesso!";
        }
    },
    
    READ_MESSAGE{
        @Override
        public String toString(){
            return "Dados lidos com sucesso!";
        }
    },
    
    UPDATE_MESSAGE{
        @Override
        public String toString(){
            return "Dados atualizados com sucesso!";
        }
    },
    
    DELETE_MESSAGE{
        @Override
        public String toString(){
            return "Dados deletados com sucesso!";
        }
    },
    
    LOGOUT_MESSAGE{
        @Override
        public String toString(){
            return "Logado com sucesso!";
        }
    },
    
    ERROR_PASSWORD_MESSAGE{
        @Override
        public String toString(){
            return "Usuário e/ou senha incorretos!";
        }
    },
    
    ERROR_MESSAGE{
        @Override
        public String toString(){
            return "Algo deu errado! Contate o Administrador do Sistema";
        }
    },
    
    INPUT_ERROR_MESSAGE{
        @Override
        public String toString(){
            return "Erro na digitação dos dados";
        }
    },
    
    CPF_ERROR_MESSAGE{
        @Override
        public String toString(){
            return "Funcionário com esse C.P.F. já existente";
        }
    },
    
    EMPLOYEE_DELETE_ERROR_MESSAGE{
        @Override
        public String toString(){
            return "Funcionário com esse C.P.F. já existente";
        }
    },
    
    USER_ERROR_MESSAGE{
        @Override
        public String toString(){
            return "Username já existente";
        }
    },
    
    CONNECT_ERROR_MESSAGE{
        @Override
        public String toString(){
            return "Falha na conexão ao banco de dados! Tente novamente mais tarde.";
        }
    }
}
