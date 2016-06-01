package ind.br.vedax.command;

import ind.br.vedax.enums.LogEnum;
import ind.br.vedax.enums.ReturnMsgEnum;
import ind.br.vedax.jms.ProducerBean;
import ind.br.vedax.model.dao.UserSteeltratDAO;
import ind.br.vedax.model.dao.EmployeeDAO;
import ind.br.vedax.model.entities.UserSteeltrat;
import ind.br.vedax.model.entities.Employee;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCommand implements Command {
    ProducerBean producerBean = lookupProducerBeanBean();

    EmployeeDAO employeeDAO = lookupEmployeeDAOBean();
    UserSteeltratDAO userSteeltratDAO = lookupUserSteeltratDAOBean();

    private final String forLog = "Usuário";
    private EntityManagerFactory emf;
    private EntityManager em;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String returnPage = "index.jsp";
    private UserSteeltrat userLogin;
    private Employee employee;

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
            employeeDAO.setEm(em);
            userSteeltratDAO.setEm(em);
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
        UserSteeltrat user = new UserSteeltrat();
        switch (action) {
            case "login":
                try {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    if (authorize(username, password)) {
                        /* LOG DO SISTEMA */
                        producerBean.sendMessage(employee.getNameEmployee() + LogEnum.LOGIN_MESSAGE);
                        request.getSession().setAttribute("employee", employee);
                        username = "";
                        password = "";
                        if (request.getParameter("session") != null) {
                            username = request.getParameter("username");
                            password = request.getParameter("password");
                        }
                        Cookie cookie = new Cookie("user", username);
                        cookie.setMaxAge(60 * 60 * 24 * 7);
                        response.addCookie(cookie);

                        Cookie cookie2 = new Cookie("password", password);
                        cookie2.setMaxAge(60 * 60 * 24 * 7);
                        response.addCookie(cookie2);

                        returnPage = "WEB-INF/jsp/home.jsp";
                    } else {
                        request.getSession().invalidate();
                        request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.ERROR_PASSWORD_MESSAGE.toString());
                    }
                } catch (Exception ex) {
                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.ERROR_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* REDIRECIONA PARA PÁGINA DESEJADA */
                    returnPage = "index.jsp";
                }
                break;
            case "logout":
                /* LOG DO SISTEMA */
                producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.LOGOUT_MESSAGE.toString());

                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.LOGOUT_MESSAGE.toString());
                break;
            case "insert":
                returnPage = "WEB-INF/jsp/user/insert.jsp";
                break;
            case "insert.confirm":
                try {
                    /* VARIÁVEIS DO FORM */
                    user.setUsername(request.getParameter("username"));
                    user.setPassword(request.getParameter("password"));

                    if ((userSteeltratDAO.readByName(user.getUsername())) != null) {
                        /* "SETA" ATRIBUTOS */
                        request.getSession().setAttribute("returnMsgError", forLog + ReturnMsgEnum.GENERIC_ERROR_MESSAGE);

                        /* REDIRECIONA PARA PÁGINA DESEJADA */
                        returnPage = "WEB-INF/jsp/user/insert.jsp";
                        break;
                    }
                    /* PERSITE O OBJETO NO BANCO */
                    userSteeltratDAO.create(user);

                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.CREATE_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("users", userSteeltratDAO.read());
                    request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.CREATE_MESSAGE.toString());

                } catch (Exception ex) {
                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.CREATE_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* REDIRECIONA PARA PÁGINA DESEJADA */
                    returnPage = "WEB-INF/jsp/home.jsp";
                    break;
                }

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/user/read.jsp";
                break;
            case "read":
                try {
                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("users", userSteeltratDAO.read());
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
                returnPage = "WEB-INF/jsp/user/read.jsp";
                break;
            case "update":
                try {
                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("users", userSteeltratDAO.read());
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
                returnPage = "WEB-INF/jsp/user/update.jsp";
                break;
            case "updateById":
                try {
                    /* CRIA OBJETO */
                    user = userSteeltratDAO.readById(Long.parseLong(request.getParameter("users")));

                    if ((userSteeltratDAO.readByName(request.getParameter("username"))) != null) {
                        /* "SETA" ATRIBUTOS */
                        request.getSession().setAttribute("returnMsgError", forLog + ReturnMsgEnum.GENERIC_ERROR_MESSAGE);

                        /* REDIRECIONA PARA PÁGINA DESEJADA */
                        returnPage = "WEB-INF/jsp/user/insert.jsp";
                        break;
                    }
                    /* PERSITE O OBJETO NO BANCO */
                    user.setUsername(request.getParameter("username"));
                    user.setPassword(request.getParameter("password"));

                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.UPDATE_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("users", userSteeltratDAO.read());
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
                returnPage = "WEB-INF/jsp/user/read.jsp";
                break;
            case "delete":
                try {
                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("users", userSteeltratDAO.read());
                } catch (Exception ex) {
                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.GENERIC_DELETE_MESSAGE);

                    /* REDIRECIONA PARA PÁGINA DESEJADA */
                    returnPage = "WEB-INF/jsp/user/delete.jsp";
                    return;
                }

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/user/delete.jsp";
                break;
            case "delete.confirm":
                try {
                    /* CRIA OBJETO */
                    user = userSteeltratDAO.readById(Long.parseLong(request.getParameter("users")));

                    /* PERSITE O OBJETO NO BANCO */
                    userSteeltratDAO.delete(user);
                    
                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.DELETE_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("users", userSteeltratDAO.read());
                    request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.DELETE_MESSAGE.toString());
                } catch (Exception ex) {
                    /* LOG DO SISTEMA */
                    producerBean.sendMessage(((Employee) request.getSession().getAttribute("employee")).getNameEmployee() + LogEnum.CONNECT_ERROR_MESSAGE.toString());

                    /* "SETA" ATRIBUTOS */
                    request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.GENERIC_DELETE_MESSAGE.toString());

                    /* REDIRECIONA PARA PÁGINA DESEJADA */
                    returnPage = "WEB-INF/jsp/user/delete.jsp";
                    return;
                }

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/user/read.jsp";
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

    public boolean authorize(String username, String password) {
        if ((userLogin = userSteeltratDAO.readForLogin(username, password)) != null) {
            employee = employeeDAO.readByIdUser(userLogin.getIdUser());
            return true;
        }
        return false;
    }

    private UserSteeltratDAO lookupUserSteeltratDAOBean() {
        try {
            Context c = new InitialContext();
            return (UserSteeltratDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/UserSteeltratDAO!ind.br.vedax.model.dao.UserSteeltratDAO");
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
