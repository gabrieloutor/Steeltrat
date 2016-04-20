package ind.br.vedax.command;

import ind.br.vedax.enums.ReturnMsgEnum;
import ind.br.vedax.model.entities.Departament;
import ind.br.vedax.model.dao.DepartamentDAO;
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

public class DepartamentCommand implements Command{
    DepartamentDAO departamentDAO = lookupDepartamentDAOBean();
    
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
        departamentDAO.setEm(em);
        em.getTransaction().begin();
        
        /* INICIO LÓGICA */
        String action = request.getParameter("action");
        Departament departament = new Departament();
        switch (action){
            case "insert":
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/departament/insert.jsp";
                break;
            case "insert.confirm":
                /* VARIÁVEIS DO FORM */
                departament.setNameDepartament(request.getParameter("name_departament"));
                
                /* PERSITE O OBJETO NO BANCO */
                departamentDAO.create(departament);
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("departaments", departamentDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.CREATE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/departament/read.jsp";
                break;
            case "read":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("departaments", departamentDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/departament/read.jsp";
                break;
            case "update":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("departaments", departamentDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/departament/update.jsp";
                break;
            case "updateById":
                /* CRIA OBJETO */
                departament = departamentDAO.readById(Long.parseLong(request.getParameter("departaments")));
                
                /* VARIÁVEIS DO FORM */
                departament.setNameDepartament(request.getParameter("name_departament"));
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("departaments", departamentDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.UPDATE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/departament/read.jsp";
                break;
            case "delete":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("departaments", departamentDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/departament/delete.jsp";
                break;
            case "delete.confirm":
                /* CRIA OBJETO */
                departament = departamentDAO.readById(Long.parseLong(request.getParameter("departaments")));
                
                /* PERSITE O OBJETO NO BANCO */
                departamentDAO.delete(departament);
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("departaments", departamentDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.DELETE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/departament/read.jsp";
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

    private ind.br.vedax.model.dao.DepartamentDAO lookupDepartamentDAOBean() {
        try {
            Context c = new InitialContext();
            return (DepartamentDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/DepartamentDAO!ind.br.vedax.model.dao.DepartamentDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
