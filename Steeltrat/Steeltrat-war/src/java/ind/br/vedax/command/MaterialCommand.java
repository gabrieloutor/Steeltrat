package ind.br.vedax.command;

import ind.br.vedax.enums.LogEnum;
import ind.br.vedax.enums.ReturnMsgEnum;
import ind.br.vedax.jms.ProducerBean;
import ind.br.vedax.model.dao.MaterialDAO;
import ind.br.vedax.model.entities.Employee;
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

    ProducerBean producerBean = lookupProducerBeanBean();

    MaterialDAO materialDAO = lookupMaterialDAOBean();

    private final String forLog = "Material";
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
            materialDAO.setEm(em);
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
        Material material = new Material();
        switch (action) {
            case "insert":
                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/material/insert.jsp";
                break;
            case "insert.confirm":
                try {
                    /* VARIÁVEIS DO FORM */
                    material.setNameMaterial(request.getParameter("name_material"));
                    material.setMarkMaterial(request.getParameter("mark_material"));

                    if ((materialDAO.readByName(material.getNameMaterial())) != null) {
                        /* "SETA" ATRIBUTOS */
                        request.getSession().setAttribute("returnMsgError", forLog + ReturnMsgEnum.GENERIC_ERROR_MESSAGE);

                        /* REDIRECIONA PARA PÁGINA DESEJADA */
                        returnPage = "WEB-INF/jsp/material/insert.jsp";
                        break;
                    }
                    
                    /* PERSITE O OBJETO NO BANCO */
                    materialDAO.create(material);

                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.CREATE_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("materials", materialDAO.read());
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
                returnPage = "WEB-INF/jsp/material/read.jsp";
                break;
            case "read":
                try {
                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("materials", materialDAO.read());

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
                returnPage = "WEB-INF/jsp/material/read.jsp";
                break;
            case "update":
                try {
                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("materials", materialDAO.read());

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
                returnPage = "WEB-INF/jsp/material/update.jsp";
                break;
            case "updateById":
                try {
                    /* CRIA OBJETO */
                    material = materialDAO.readById(Long.parseLong(request.getParameter("materials")));

                    /* VARIÁVEIS DO FORM */
                    material.setNameMaterial(request.getParameter("name_material"));
                    material.setMarkMaterial(request.getParameter("mark_material"));

                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.UPDATE_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("materials", materialDAO.read());
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
                returnPage = "WEB-INF/jsp/material/read.jsp";
                break;
            case "delete":
                try {
                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("materials", materialDAO.read());

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
                returnPage = "WEB-INF/jsp/material/delete.jsp";
                break;
            case "delete.confirm":
                try {
                    /* CRIA OBJETO */
                    material = materialDAO.readById(Long.parseLong(request.getParameter("materials")));

                    /* PERSITE O OBJETO NO BANCO */
                    materialDAO.delete(material);

                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.DELETE_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("materials", materialDAO.read());
                    request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.DELETE_MESSAGE.toString());

                } catch (Exception ex) {
                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.GENERIC_DELETE_MESSAGE.toString());

                    /* REDIRECIONA PARA PÁGINA DESEJADA */
                    returnPage = "WEB-INF/jsp/home.jsp";
                    break;
                }

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

    private MaterialDAO lookupMaterialDAOBean() {
        try {
            Context c = new InitialContext();
            return (ind.br.vedax.model.dao.MaterialDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/MaterialDAO!ind.br.vedax.model.dao.MaterialDAO");
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
