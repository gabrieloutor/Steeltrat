package ind.br.vedax.command;

import ind.br.vedax.enums.ReturnMsgEnum;
import ind.br.vedax.model.dao.ClientDAO;
import ind.br.vedax.model.dao.ReceiptDAO;
import ind.br.vedax.model.entities.Receipt;
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

public class ReceiptCommand implements Command{
    ReceiptDAO receiptDAO = lookupReceiptDAOBean();
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
        receiptDAO.setEm(em);
        clientDAO.setEm(em);
        em.getTransaction().begin();
        
        /* INICIO LÓGICA */
        String action = request.getParameter("action");
        Receipt receipt = new Receipt();
        switch (action){
            case "insert":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("clients", clientDAO.read());
                request.getSession().setAttribute("number_receipt", receiptDAO.lastNumber());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/receipt/insert.jsp";
                break;
            case "insert.confirm":
                /* VARIÁVEIS DO FORM */
                receipt.setIdClient(clientDAO.readById(Long.parseLong(request.getParameter("clients"))));
                
                /* PERSITE O OBJETO NO BANCO */
                receiptDAO.create(receipt);
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.CREATE_MESSAGE.toString());
                request.getSession().setAttribute("receipts", receiptDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/receipt/read.jsp";
                break;
            case "read":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("receipts", receiptDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/receipt/read.jsp";
                break;
            case "update":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("clients", clientDAO.read());
                request.getSession().setAttribute("receipts", receiptDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/receipt/update.jsp";
                break;
            case "updateById":
                /* CRIA OBJETO */
                receipt = receiptDAO.readById(Long.parseLong(request.getParameter("receipts")));
                
                /* VARIÁVEIS DO FORM */
                receipt.setIdClient(clientDAO.readById(Long.parseLong(request.getParameter("clients"))));
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("receipts", receiptDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.UPDATE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/receipt/read.jsp";
                break;
            case "delete":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("clients", clientDAO.read());
                request.getSession().setAttribute("receipts", receiptDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/receipt/delete.jsp";
                break;
            case "delete.confirm":
                /* CRIA OBJETO */
                receipt = receiptDAO.readById(Long.parseLong(request.getParameter("receipts")));
                
                /* PERSITE O OBJETO NO BANCO */
                receiptDAO.delete(receipt);
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("receipts", receiptDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.DELETE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/receipt/read.jsp";
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

    private ReceiptDAO lookupReceiptDAOBean() {
        try {
            Context c = new InitialContext();
            return (ReceiptDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/ReceiptDAO!ind.br.vedax.model.dao.ReceiptDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
