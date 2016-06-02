package ind.br.vedax.command;

import ind.br.vedax.api.entities.Place;
import ind.br.vedax.api.webservice.ConnectionManager;
import ind.br.vedax.api.webservice.JSONMapsParser;
import ind.br.vedax.enums.LogEnum;
import ind.br.vedax.enums.ReturnMsgEnum;
import ind.br.vedax.jms.ProducerBean;
import ind.br.vedax.model.dao.AddressDAO;
import ind.br.vedax.model.dao.ClientDAO;
import ind.br.vedax.model.entities.Client;
import ind.br.vedax.model.entities.Employee;
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

public class ClientCommand implements Command {
    ProducerBean producerBean = lookupProducerBeanBean();

    AddressDAO addressDAO = lookupAddressDAOBean();
    ClientDAO clientDAO = lookupClientDAOBean();

    private final String forLog = "Nome de Cliente";
    private EntityManagerFactory emf;
    private EntityManager em;
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
        try {
            emf = Persistence.createEntityManagerFactory("steeltrat_pu");
            em = emf.createEntityManager();
            addressDAO.setEm(em);
            clientDAO.setEm(em);
            em.getTransaction().begin();
        } catch (Exception ex) {
            /* LOG DO SISTEMA */
            producerBean.sendMessage(LogEnum.CONNECT_ERROR_MESSAGE.toString());

            /* "SETA" ATRIBUTOS */
            request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());

            /* REDIRECIONA PARA PÁGINA DESEJADA */
            returnPage = "index.jsp";
            return;
        }

        /* INICIO LÓGICA */
        String action = request.getParameter("action");
        Client client = new Client();
        switch (action) {
            case "insert":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("addresses", addressDAO.read());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/client/insert.jsp";
                break;
            case "insert.confirm":
                try {
                    /* VARIÁVEIS DO FORM */
                    client.setNameClient(request.getParameter("name_client"));
                    client.setTelephone(Long.parseLong(request.getParameter("telephone_client")));
                    client.setIdAddress(addressDAO.readById(Long.parseLong(request.getParameter("addresses"))));
                    
                    if ((clientDAO.readByName(client.getNameClient())) != null) {
                        /* "SETA" ATRIBUTOS */
                        request.getSession().setAttribute("returnMsgError", forLog + ReturnMsgEnum.GENERIC_ERROR_MESSAGE);

                        /* REDIRECIONA PARA PÁGINA DESEJADA */
                        returnPage = "WEB-INF/jsp/client/insert.jsp";
                        break;
                    }
                    
                    /* PERSITE O OBJETO NO BANCO */
                    clientDAO.create(client);

                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.CREATE_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("clients", clientDAO.read());
                    request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.CREATE_MESSAGE.toString());

                } catch (Exception ex) {
                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* REDIRECIONA PARA PÁGINA DESEJADA */
                    returnPage = "WEB-INF/jsp/home.jsp";
                    break;
                }

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/client/read.jsp";
                break;
            case "read":
                try {
                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("clients", clientDAO.read());

                } catch (Exception ex) {
                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* REDIRECIONA PARA PÁGINA DESEJADA */
                    returnPage = "WEB-INF/jsp/home.jsp";
                    break;
                }

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/client/read.jsp";
                break;
            case "update":
                try {
                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("addresses", addressDAO.read());
                    request.getSession().setAttribute("clients", clientDAO.read());

                } catch (Exception ex) {
                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* REDIRECIONA PARA PÁGINA DESEJADA */
                    returnPage = "WEB-INF/jsp/home.jsp";
                    break;
                }

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/client/update.jsp";
                break;
            case "updateById":
                try {
                    /* CRIA OBJETO */
                    client = clientDAO.readById(Long.parseLong(request.getParameter("clients")));
                    
                    if (!client.getNameClient().equals(request.getParameter("name_client")) && (clientDAO.readByName(request.getParameter("name_client"))) != null) {
                        /* "SETA" ATRIBUTOS */
                        request.getSession().setAttribute("returnMsgError", forLog + ReturnMsgEnum.GENERIC_ERROR_MESSAGE);

                        /* REDIRECIONA PARA PÁGINA DESEJADA */
                        returnPage = "WEB-INF/jsp/client/update.jsp";
                        break;
                    }
                    
                    /* VARIÁVEIS DO FORM */
                    client.setNameClient(request.getParameter("name_client"));
                    client.setTelephone(Long.parseLong(request.getParameter("telephone_client")));
                    client.setIdAddress(addressDAO.readById(Long.parseLong(request.getParameter("addresses"))));

                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.UPDATE_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("clients", clientDAO.read());
                    request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.UPDATE_MESSAGE.toString());

                } catch (Exception ex) {
                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* REDIRECIONA PARA PÁGINA DESEJADA */
                    returnPage = "WEB-INF/jsp/home.jsp";
                    break;
                }

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/client/read.jsp";
                break;
            case "delete":
                try {
                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("clients", clientDAO.read());

                } catch (Exception ex) {
                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* REDIRECIONA PARA PÁGINA DESEJADA */
                    returnPage = "WEB-INF/jsp/home.jsp";
                    break;
                }
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/client/delete.jsp";
                break;
            case "delete.confirm":
                try {
                    /* CRIA OBJETO */
                    client = clientDAO.readById(Long.parseLong(request.getParameter("clients")));

                    /* PERSITE O OBJETO NO BANCO */
                    clientDAO.delete(client);
                    
                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.DELETE_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("clients", clientDAO.read());
                    request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.DELETE_MESSAGE.toString());

                } catch (Exception ex) {
                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.GENERIC_DELETE_MESSAGE.toString());

                    /* REDIRECIONA PARA PÁGINA DESEJADA */
                    returnPage = "WEB-INF/jsp/client/delete.jsp";
                    return;
                }

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/client/read.jsp";
                break;
            case "route":
                /* VARIÁVEL DA TABELA */
                String zipcode = request.getParameter("zipcode");

                /* CRIA CONTEXTO PARA API's */
                String contentStart = ConnectionManager.readContent("https://maps.googleapis.com/maps/api/geocode/json?address=06413-900&components=country:BR&key=AIzaSyB7USt8JY_YSX1IL-g0W_Utax1PzXlxHzA");
                Place start = JSONMapsParser.parseFeed(contentStart);

                String contentEnd = ConnectionManager.readContent("https://maps.googleapis.com/maps/api/geocode/json?address=" + zipcode + "&components=country:BR&key=AIzaSyB7USt8JY_YSX1IL-g0W_Utax1PzXlxHzA");
                Place end = JSONMapsParser.parseFeed(contentEnd);

                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("start", start);
                request.getSession().setAttribute("end", end);

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
        try {
            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception ex) {
            /* LOG DO SISTEMA */
            producerBean.sendMessage(LogEnum.CONNECT_ERROR_MESSAGE.toString());

            /* "SETA" ATRIBUTOS */
            request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());

            /* REDIRECIONA PARA PÁGINA DESEJADA */
            returnPage = "index.jsp";
        }
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

    private ProducerBean lookupProducerBeanBean() {
        try {
            Context c = new InitialContext();
            return (ProducerBean) c.lookup("java:global/Steeltrat/Steeltrat-ejb/ProducerBean!ind.br.vedax.jms.ProducerBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
