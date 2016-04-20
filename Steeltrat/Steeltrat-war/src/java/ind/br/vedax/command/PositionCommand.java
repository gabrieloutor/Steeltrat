package ind.br.vedax.command;

import ind.br.vedax.enums.ReturnMsgEnum;
import ind.br.vedax.model.dao.PositionSteeltratDAO;
import ind.br.vedax.model.entities.PositionSteeltrat;
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

public class PositionCommand implements Command{
    PositionSteeltratDAO positionSteeltratDAO = lookupPositionSteeltratDAOBean();
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
        positionSteeltratDAO.setEm(em);
        em.getTransaction().begin();
        
        /* INICIO LÓGICA */
        String action = request.getParameter("action");
        PositionSteeltrat position = new PositionSteeltrat();
        switch (action){
            case "insert":
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/position/insert.jsp";
                break;
            case "insert.confirm":
                /* VARIÁVEIS DO FORM */
                position.setNamePosition(request.getParameter("name_position"));
                
                /* PERSITE O OBJETO NO BANCO */
                positionSteeltratDAO.create(position);
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("positions", positionSteeltratDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.CREATE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/position/read.jsp";
                break;
            case "read":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("positions", positionSteeltratDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/position/read.jsp";
                break;
            case "update":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("positions", positionSteeltratDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/position/update.jsp";
                break;
            case "updateById":
                /* CRIA OBJETO */
                position = positionSteeltratDAO.readById(Long.parseLong(request.getParameter("positions")));
                
                /* PERSITE O OBJETO NO BANCO */
                position.setNamePosition(request.getParameter("name_position"));
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("positions", positionSteeltratDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.UPDATE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/position/read.jsp";
                break;
            case "delete":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("positions", positionSteeltratDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/position/delete.jsp";
                break;
            case "delete.confirm":
                /* CRIA OBJETO */
                position = positionSteeltratDAO.readById(Long.parseLong(request.getParameter("positions")));
                
                /* PERSITE O OBJETO NO BANCO */
                positionSteeltratDAO.delete(position);
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("positions", positionSteeltratDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.DELETE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/position/read.jsp";
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

    private PositionSteeltratDAO lookupPositionSteeltratDAOBean() {
        try {
            Context c = new InitialContext();
            return (PositionSteeltratDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/PositionSteeltratDAO!ind.br.vedax.model.dao.PositionSteeltratDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
