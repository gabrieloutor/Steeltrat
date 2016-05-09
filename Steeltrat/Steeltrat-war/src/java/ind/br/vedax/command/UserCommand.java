package ind.br.vedax.command;

import ind.br.vedax.enums.ReturnMsgEnum;
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

    EmployeeDAO employeeDAO = lookupEmployeeDAOBean();
    UserSteeltratDAO userSteeltratDAO = lookupUserSteeltratDAOBean();
    
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
            request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());
            returnPage = "index.jsp";
            return;
        }
        
        /* INICIO LÓGICA */
        String action = request.getParameter("action");
        UserSteeltrat user = new UserSteeltrat();
        switch (action) {
            case "login":
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                if (authorize(username, password)) {
                    request.getSession().setAttribute("resultado", true);
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
                break;
            case "logout":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.LOGOUT_MESSAGE.toString());
                break;
            case "insert":
                returnPage = "WEB-INF/jsp/user/insert.jsp";
                break;
            case "insert.confirm":
                /* VARIÁVEIS DO FORM */
                user.setUsername(request.getParameter("username"));
                user.setPassword(request.getParameter("password"));

                /* PERSITE O OBJETO NO BANCO */
                userSteeltratDAO.create(user);

                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("users", userSteeltratDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.CREATE_MESSAGE.toString());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/user/read.jsp";
                break;
            case "read":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("users", userSteeltratDAO.read());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/user/read.jsp";
                break;
            case "update":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("users", userSteeltratDAO.read());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/user/update.jsp";
                break;
            case "updateById":
                /* CRIA OBJETO */
                user = userSteeltratDAO.readById(Long.parseLong(request.getParameter("users")));

                /* PERSITE O OBJETO NO BANCO */
                user.setUsername(request.getParameter("username"));
                user.setPassword(request.getParameter("password"));

                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("users", userSteeltratDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.UPDATE_MESSAGE.toString());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/user/read.jsp";
                break;
            case "delete":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("users", userSteeltratDAO.read());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/user/delete.jsp";
                break;
            case "delete.confirm":
                /* CRIA OBJETO */
                user = userSteeltratDAO.readById(Long.parseLong(request.getParameter("users")));

                /* PERSITE O OBJETO NO BANCO */
                userSteeltratDAO.delete(user);

                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("users", userSteeltratDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.DELETE_MESSAGE.toString());

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
            request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());
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
            return (ind.br.vedax.model.dao.EmployeeDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/EmployeeDAO!ind.br.vedax.model.dao.EmployeeDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
