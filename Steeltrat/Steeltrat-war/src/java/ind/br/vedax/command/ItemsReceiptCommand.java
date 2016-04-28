package ind.br.vedax.command;

import ind.br.vedax.enums.ReturnMsgEnum;
import ind.br.vedax.model.dao.EmployeeDAO;
import ind.br.vedax.model.dao.ItemsReceiptDAO;
import ind.br.vedax.model.dao.MaterialDAO;
import ind.br.vedax.model.dao.ProductDAO;
import ind.br.vedax.model.dao.ReceiptDAO;
import ind.br.vedax.model.dao.StandardDAO;
import ind.br.vedax.model.entities.ItemsReceipt;
import ind.br.vedax.util.DateUtil;
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

public class ItemsReceiptCommand implements Command{
    ItemsReceiptDAO itemsReceiptDAO = lookupItemsReceiptDAOBean();
    ProductDAO productDAO = lookupProductDAOBean();
    MaterialDAO materialDAO = lookupMaterialDAOBean();
    StandardDAO standardDAO = lookupStandardDAOBean();
    EmployeeDAO employeeDAO = lookupEmployeeDAOBean();
    ReceiptDAO receiptDAO = lookupReceiptDAOBean();
    
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
        itemsReceiptDAO.setEm(em);
        productDAO.setEm(em);
        materialDAO.setEm(em);
        standardDAO.setEm(em);
        employeeDAO.setEm(em);
        receiptDAO.setEm(em);
        em.getTransaction().begin();

        /* INICIO LÓGICA */
        String action = request.getParameter("action");
        ItemsReceipt itemReceipt = new ItemsReceipt();
        switch (action){
            case "insert":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("receipts", receiptDAO.read());
                request.getSession().setAttribute("products", productDAO.read());
                request.getSession().setAttribute("materials", materialDAO.read());
                request.getSession().setAttribute("standards", standardDAO.read());
                request.getSession().setAttribute("employees", employeeDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/itemReceipt/insert.jsp";
                break;
            case "insert.confirm":
                /* VARIÁVEIS DO FORM */
                itemReceipt.setIdReceipt(receiptDAO.readById(Long.parseLong(request.getParameter("receipts"))));
                itemReceipt.setNfClient(request.getParameter("nf_client"));
                itemReceipt.setOrderClient(request.getParameter("order_client"));
                itemReceipt.setAmountPiece(Integer.parseInt(request.getParameter("amount_piece")));
                itemReceipt.setAmountSpecimen(Integer.parseInt(request.getParameter("amount_specimen")));
                itemReceipt.setWeight(Double.parseDouble(request.getParameter("weight")));
                itemReceipt.setNumberTransport(Integer.parseInt(request.getParameter("number_transport")));
                itemReceipt.setIdProduct(productDAO.readById(Long.parseLong(request.getParameter("products"))));
                itemReceipt.setIdMaterial(materialDAO.readById(Long.parseLong(request.getParameter("materials"))));
                itemReceipt.setIdStandard(standardDAO.readById(Long.parseLong(request.getParameter("standards"))));
                itemReceipt.setIdEmployee(employeeDAO.readById(Long.parseLong(request.getParameter("employees"))));
                itemReceipt.setMarkItemReceipt(request.getParameter("mark_item_receipt"));
                itemReceipt.setDateItemReceipt(DateUtil.string2Date(request.getParameter("date_item_receipt")));
                
                /* PERSITE O OBJETO NO BANCO */
                itemsReceiptDAO.create(itemReceipt);
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.CREATE_MESSAGE.toString());
                request.getSession().setAttribute("itemReceipts", itemsReceiptDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/itemReceipt/read.jsp";
                break;
            case "read":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("itemReceipts", itemsReceiptDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/itemReceipt/read.jsp";
                break;
            case "update":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("itemReceipts", itemsReceiptDAO.read());
                request.getSession().setAttribute("receipts", receiptDAO.read());
                request.getSession().setAttribute("products", productDAO.read());
                request.getSession().setAttribute("materials", materialDAO.read());
                request.getSession().setAttribute("standards", standardDAO.read());
                request.getSession().setAttribute("employees", employeeDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/itemReceipt/update.jsp";
                break;
            case "updateById":
                /* CRIA OBJETO */
                itemReceipt = itemsReceiptDAO.readById(Long.parseLong(request.getParameter("itemReceipts")));
                
                /* VARIÁVEIS DO FORM */
                itemReceipt.setIdReceipt(receiptDAO.readById(Long.parseLong(request.getParameter("receipts"))));
                itemReceipt.setNfClient(request.getParameter("nf_client"));
                itemReceipt.setOrderClient(request.getParameter("order_client"));
                itemReceipt.setAmountPiece(Integer.parseInt(request.getParameter("amount_piece")));
                itemReceipt.setAmountSpecimen(Integer.parseInt(request.getParameter("amount_specimen")));
                itemReceipt.setWeight(Double.parseDouble(request.getParameter("weight")));
                itemReceipt.setNumberTransport(Integer.parseInt(request.getParameter("number_transport")));
                itemReceipt.setIdProduct(productDAO.readById(Long.parseLong(request.getParameter("products"))));
                itemReceipt.setIdMaterial(materialDAO.readById(Long.parseLong(request.getParameter("materials"))));
                itemReceipt.setIdStandard(standardDAO.readById(Long.parseLong(request.getParameter("standards"))));
                itemReceipt.setIdEmployee(employeeDAO.readById(Long.parseLong(request.getParameter("employees"))));
                itemReceipt.setMarkItemReceipt(request.getParameter("mark_item_receipt"));
                itemReceipt.setDateItemReceipt(DateUtil.string2Date(request.getParameter("date_item_receipt")));
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.UPDATE_MESSAGE.toString());
                request.getSession().setAttribute("itemReceipts", itemsReceiptDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/itemReceipt/read.jsp";
                break;
            case "delete":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("itemReceipts", itemsReceiptDAO.read());
                request.getSession().setAttribute("receipts", receiptDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/itemReceipt/delete.jsp";
                break;
            case "delete.confirm":
                /* CRIA OBJETO */
                itemReceipt = itemsReceiptDAO.readById(Long.parseLong(request.getParameter("itemReceipts")));
                
                /* PERSITE O OBJETO NO BANCO */
                itemsReceiptDAO.delete(itemReceipt);
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("itemReceipts", itemsReceiptDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.DELETE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/itemReceipt/read.jsp";
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

    private ReceiptDAO lookupReceiptDAOBean() {
        try {
            Context c = new InitialContext();
            return (ind.br.vedax.model.dao.ReceiptDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/ReceiptDAO!ind.br.vedax.model.dao.ReceiptDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EmployeeDAO lookupEmployeeDAOBean() {
        try {
            Context c = new InitialContext();
            return (EmployeeDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/EmployeeDAO!ind.br.vedax.model.dao.EmployeeDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private StandardDAO lookupStandardDAOBean() {
        try {
            Context c = new InitialContext();
            return (StandardDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/StandardDAO!ind.br.vedax.model.dao.StandardDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private MaterialDAO lookupMaterialDAOBean() {
        try {
            Context c = new InitialContext();
            return (MaterialDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/MaterialDAO!ind.br.vedax.model.dao.MaterialDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ProductDAO lookupProductDAOBean() {
        try {
            Context c = new InitialContext();
            return (ProductDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/ProductDAO!ind.br.vedax.model.dao.ProductDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ItemsReceiptDAO lookupItemsReceiptDAOBean() {
        try {
            Context c = new InitialContext();
            return (ItemsReceiptDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/ItemsReceiptDAO!ind.br.vedax.model.dao.ItemsReceiptDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}