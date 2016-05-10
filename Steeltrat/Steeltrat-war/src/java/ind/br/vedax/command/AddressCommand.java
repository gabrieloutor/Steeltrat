package ind.br.vedax.command;

import ind.br.vedax.enums.ReturnMsgEnum;
import ind.br.vedax.model.dao.AddressDAO;
import ind.br.vedax.model.entities.Address;
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

public class AddressCommand implements Command {

    AddressDAO addressDAO = lookupAddressDAOBean();

    private EntityManagerFactory emf;
    private EntityManager em;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String returnPage = "index.jsp";

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
        /* ABRE CONEXÃO */
        try {
            emf = Persistence.createEntityManagerFactory("steeltrat_pu");
            em = emf.createEntityManager();
            addressDAO.setEm(em);
            em.getTransaction().begin();
        } catch (Exception ex) {
            request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());
            return;
        }
        /* INICIO LÓGICA */
        String action = request.getParameter("action");
        Address address = new Address();
        switch (action) {
            case "insert":
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/address/insert.jsp";
                break;
            case "insert.confirm":
                /* VARIÁVEIS DO FORM */
                address.setZipcode(request.getParameter("zipcode"));
                address.setNumberAddress(Integer.parseInt(request.getParameter("number_address")));

                /* PERSITE O OBJETO NO BANCO */
                addressDAO.create(address);

                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("addresses", addressDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.CREATE_MESSAGE.toString());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/address/read.jsp";
                break;
            case "read":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("addresses", addressDAO.read());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/address/read.jsp";
                break;
            case "update":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("addresses", addressDAO.read());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/address/update.jsp";
                break;
            case "updateById":
                /* CRIA OBJETO */
                address = addressDAO.readById(Long.parseLong(request.getParameter("addresses")));

                /* VARIÁVEIS DO FORM */
                address.setZipcode(request.getParameter("zipcode"));
                address.setNumberAddress(Integer.parseInt(request.getParameter("number_address")));

                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("addresses", addressDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.UPDATE_MESSAGE.toString());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/address/read.jsp";
                break;
            case "delete":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("addresses", addressDAO.read());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/address/delete.jsp";
                break;
            case "delete.confirm":
                /* CRIA OBJETO */
                address = addressDAO.readById(Long.parseLong(request.getParameter("addresses")));

                /* PERSITE O OBJETO NO BANCO */
                addressDAO.delete(address);

                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("addresses", addressDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.DELETE_MESSAGE.toString());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/address/read.jsp";
                break;
            default:
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.ERROR_MESSAGE.toString());
                break;
        }
        /* FECHA LÓGICA */

        /* FECHA CONEXÃO */
        try {
            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception ex) {
            request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());
            returnPage = "index.jsp";
        }
    }

    @Override
    public String getReturnPage() {
        return returnPage;
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
