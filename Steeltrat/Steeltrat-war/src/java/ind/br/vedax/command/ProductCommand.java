package ind.br.vedax.command;

import ind.br.vedax.enums.ReturnMsgEnum;
import ind.br.vedax.model.dao.ProductDAO;
import ind.br.vedax.model.entities.Product;
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

public class ProductCommand implements Command{
    ind.br.vedax.model.dao.ProductDAO productDAO = lookupProductDAOBean();
    
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
        productDAO.setEm(em);
        em.getTransaction().begin();
        
        /* INICIO LÓGICA */
        String action = request.getParameter("action");
        Product product = new Product();
        switch (action){
            case "insert":
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/product/insert.jsp";
                break;
            case "insert.confirm":
                /* VARIÁVEIS DO FORM */
                product.setDescriptionProduct(request.getParameter("description_product"));
                product.setPrice(Double.parseDouble(request.getParameter("price")));
                
                /* PERSITE O OBJETO NO BANCO */
                productDAO.create(product);
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("products", productDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.CREATE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/product/read.jsp";
                break;
            case "read":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("products", productDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/product/read.jsp";
                break;
            case "update":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("products", productDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/product/update.jsp";
                break;
            case "updateById":
                /* CRIA OBJETO */
                product = productDAO.readById(Long.parseLong(request.getParameter("products")));
                
                /* VARIÁVEIS DO FORM */
                product.setDescriptionProduct(request.getParameter("description_product"));
                product.setPrice(Double.parseDouble(request.getParameter("price")));
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("products", productDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.UPDATE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/product/read.jsp";
                break;
            case "delete":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("products", productDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/product/delete.jsp";
                break;
            case "delete.confirm":
                /* CRIA OBJETO */
                product = productDAO.readById(Long.parseLong(request.getParameter("products")));
                
                /* PERSITE O OBJETO NO BANCO */
                productDAO.delete(product);
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("products", productDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.DELETE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/product/read.jsp";
                break;
            default:
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.ERROR_MESSAGE.toString());
                break;
        }
    }

    @Override
    public String getReturnPage() {
        return returnPage;
    }

    private ProductDAO lookupProductDAOBean() {
        try {
            Context c = new InitialContext();
            return (ind.br.vedax.model.dao.ProductDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/ProductDAO!ind.br.vedax.model.dao.ProductDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
