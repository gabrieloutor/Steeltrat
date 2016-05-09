package ind.br.vedax.command;

import ind.br.vedax.enums.ReturnMsgEnum;
import ind.br.vedax.model.dao.StandardDAO;
import ind.br.vedax.model.entities.Standard;
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

public class StandardCommand implements Command {

    StandardDAO standardDAO = lookupStandardDAOBean();
    
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
            standardDAO.setEm(em);
            em.getTransaction().begin();
        } catch (Exception ex) {
            request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());
            returnPage = "index.jsp";
            return;
        }
        /* INICIO LÓGICA */
        String action = request.getParameter("action");
        Standard standard = new Standard();
        switch (action) {
            case "insert":
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/standard/insert.jsp";
                break;
            case "insert.confirm":
                /* VARIÁVEIS DO FORM */
                standard.setNameStandard(request.getParameter("name_standard"));
                standard.setMarkStandard(request.getParameter("mark_standard"));

                /* PERSITE O OBJETO NO BANCO */
                standardDAO.create(standard);

                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("standards", standardDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.CREATE_MESSAGE.toString());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/standard/read.jsp";
                break;
            case "read":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("standards", standardDAO.read());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/standard/read.jsp";
                break;
            case "update":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("standards", standardDAO.read());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/standard/update.jsp";
                break;
            case "updateById":
                /* CRIA OBJETO */
                standard = standardDAO.readById(Long.parseLong(request.getParameter("standards")));

                /* VARIÁVEIS DO FORM */
                standard.setNameStandard(request.getParameter("name_standard"));
                standard.setMarkStandard(request.getParameter("mark_standard"));

                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("standards", standardDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.UPDATE_MESSAGE.toString());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/standard/read.jsp";
                break;
            case "delete":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("standards", standardDAO.read());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/standard/delete.jsp";
                break;
            case "delete.confirm":
                /* CRIA OBJETO */
                standard = standardDAO.readById(Long.parseLong(request.getParameter("standards")));

                /* PERSITE O OBJETO NO BANCO */
                standardDAO.delete(standard);

                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("standards", standardDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.DELETE_MESSAGE.toString());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/standard/read.jsp";
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

    private StandardDAO lookupStandardDAOBean() {
        try {
            Context c = new InitialContext();
            return (StandardDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/StandardDAO!ind.br.vedax.model.dao.StandardDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
