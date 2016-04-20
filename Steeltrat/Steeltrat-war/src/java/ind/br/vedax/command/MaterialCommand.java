package ind.br.vedax.command;

import ind.br.vedax.enums.ReturnMsgEnum;
import ind.br.vedax.model.dao.MaterialDAO;
import ind.br.vedax.model.entities.Material;
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

public class MaterialCommand implements Command {
    MaterialDAO materialDAO = lookupMaterialDAOBean();

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
        materialDAO.setEm(em);
        em.getTransaction().begin();

        /* INICIO LÓGICA */
        String action = request.getParameter("action");
        Material material = new Material();
        switch (action) {
            case "insert":
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/material/insert.jsp";
                break;
            case "insert.confirm":
                /* VARIÁVEIS DO FORM */
                material.setNameMaterial(request.getParameter("name_material"));
                material.setMarkMaterial(request.getParameter("mark_material"));
                
                /* PERSITE O OBJETO NO BANCO */
                materialDAO.create(material);
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("materials", materialDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.CREATE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/material/read.jsp";
                break;
            case "read":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("materials", materialDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/material/read.jsp";
                break;
            case "update":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("materials", materialDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/material/update.jsp";
                break;
            case "updateById":
                /* CRIA OBJETO */
                material = materialDAO.readById(Long.parseLong(request.getParameter("materials")));
                
                /* VARIÁVEIS DO FORM */
                material.setNameMaterial(request.getParameter("name_material"));
                material.setMarkMaterial(request.getParameter("mark_material"));
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("materials", materialDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.UPDATE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/material/read.jsp";
                break;
            case "delete":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("materials", materialDAO.read());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/material/delete.jsp";
                break;
            case "delete.confirm":
                /* CRIA OBJETO */
                material = materialDAO.readById(Long.parseLong(request.getParameter("materials")));
                
                /* PERSITE O OBJETO NO BANCO */
                materialDAO.delete(material);
                
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("materials", materialDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.DELETE_MESSAGE.toString());
                
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/material/read.jsp";
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

    private MaterialDAO lookupMaterialDAOBean() {
        try {
            Context c = new InitialContext();
            return (ind.br.vedax.model.dao.MaterialDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/MaterialDAO!ind.br.vedax.model.dao.MaterialDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
