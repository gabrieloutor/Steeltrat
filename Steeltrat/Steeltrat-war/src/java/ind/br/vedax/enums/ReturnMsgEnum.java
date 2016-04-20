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
    }
}
