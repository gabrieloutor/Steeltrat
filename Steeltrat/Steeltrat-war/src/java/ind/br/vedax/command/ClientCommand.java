package ind.br.vedax.command;

import ind.br.vedax.enums.ReturnMsgEnum;
import ind.br.vedax.exceptions.DBException;
import ind.br.vedax.model.dao.AddressDAO;
import ind.br.vedax.model.dao.ClientDAO;
import ind.br.vedax.model.entities.Client;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientCommand implements Command{
    AddressDAO addressDAO = lookupAddressDAOBean();
    ClientDAO clientDAO = lookupClientDAOBean();
    
    public HttpServletRequest request;
    public HttpServletResponse response;
    public String returnPage = "index.jsp";
    
    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        /* ABRE CONEXÃO */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("steeltrat_pu");
        EntityManager em = emf.createEntityManager();
        addressDAO.setEm(em);
        clientDAO.setEm(em);
        em.getTransaction().begin();
        
        /* INICIO LÓGICA */
        String action = request.getParameter("action");
        Client client = new Client();
        switch (action){
            case "insert":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("addresses", addressDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/client/insert.jsp";
                break;
            case "insert.confirm":
                /* VARIÁVEIS DO FORM */
                client.setNameClient(request.getParameter("name_client"));
                client.setTelephone(Long.parseLong(request.getParameter("telephone_client")));
                client.setIdAddress(addressDAO.readById(Long.parseLong(request.getParameter("addresses"))));
                
                /* PERSITE O OBJETO NO BANCO */
                try{
                    clientDAO.create(client);
                    request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.CREATE_MESSAGE.toString());
                }catch(Exception ex){
                    request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.ERROR_MESSAGE.toString());
                }
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("clients", clientDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/client/read.jsp";
                break;
            case "read":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("clients", clientDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/client/read.jsp";
                break;
            case "update":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("addresses", addressDAO.read());
                request.getSession().setAttribute("clients", clientDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/client/update.jsp";
                break;
            case "updateById":
                /* CRIA OBJETO */
                client = clientDAO.readById(Long.parseLong(request.getParameter("clients")));
                
                /* VARIÁVEIS DO FORM */
                client.setNameClient(request.getParameter("name_client"));
                client.setTelephone(Long.parseLong(request.getParameter("telephone_client")));
                client.setIdAddress(addressDAO.readById(Long.parseLong(request.getParameter("addresses"))));
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("clients", clientDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.UPDATE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/client/read.jsp";
                break;
            case "delete":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("clients", clientDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/client/delete.jsp";
                break;
            case "delete.confirm":
                /* CRIA OBJETO */
                client = clientDAO.readById(Long.parseLong(request.getParameter("clients")));
                
                /* PERSITE O OBJETO NO BANCO */
                clientDAO.delete(client);
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("clients", clientDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.DELETE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/client/read.jsp";
                break;
            default:
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.ERROR_MESSAGE.toString());
                break;
        }
        /* FECHA LÓGICA */
        
        /* FECHA CONEXÃO */
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Override
    public String getReturnPage() {
        return returnPage;
    }

    private ClientDAO lookupClientDAOBean() {
        try {
            Context c = new InitialContext();
            return (ClientDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/ClientDAO!ind.br.vedax.model.dao.ClientDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private AddressDAO lookupAddressDAOBean() {
        try {
            Context c = new InitialContext();
            return (AddressDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/AddressDAO!ind.br.vedax.model.dao.AddressDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}