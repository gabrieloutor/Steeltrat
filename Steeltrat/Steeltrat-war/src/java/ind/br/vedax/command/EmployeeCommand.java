package ind.br.vedax.command;

import ind.br.vedax.enums.ReturnMsgEnum;
import ind.br.vedax.model.entities.Employee;
import ind.br.vedax.model.dao.DepartamentDAO;
import ind.br.vedax.model.dao.EmployeeDAO;
import ind.br.vedax.model.dao.PositionSteeltratDAO;
import ind.br.vedax.model.dao.UserSteeltratDAO;
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

public class EmployeeCommand implements Command {

    UserSteeltratDAO userSteeltratDAO = lookupUserSteeltratDAOBean();
    PositionSteeltratDAO positionSteeltratDAO = lookupPositionSteeltratDAOBean();
    DepartamentDAO departamentDAO = lookupDepartamentDAOBean();
    EmployeeDAO employeeDAO = lookupEmployeeDAOBean();

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
            departamentDAO.setEm(em);
            userSteeltratDAO.setEm(em);
            positionSteeltratDAO.setEm(em);
            employeeDAO.setEm(em);
            em.getTransaction().begin();
        } catch (Exception ex) {
            request.getSession().setAttribute("returnMsgError", ReturnMsgEnum.CONNECT_ERROR_MESSAGE.toString());
            returnPage = "index.jsp";
            return;
        }
        /* INICIO LÓGICA */
        String action = request.getParameter("action");
        Employee employee = new Employee();
        switch (action) {
            case "insert":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("positions", positionSteeltratDAO.read());
                request.getSession().setAttribute("users", userSteeltratDAO.read());
                request.getSession().setAttribute("departaments", departamentDAO.read());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/employee/insert.jsp";
                break;
            case "insert.confirm":
                /* VARIÁVEIS DO FORM */
                employee.setNameEmployee(request.getParameter("name_employee"));
                employee.setIdUser(userSteeltratDAO.readById(Long.parseLong(request.getParameter("users"))));
                employee.setIdPosition(positionSteeltratDAO.readById(Long.parseLong(request.getParameter("positions"))));
                employee.setIdDepartament(departamentDAO.readById(Long.parseLong(request.getParameter("departaments"))));
                employee.setCpf(request.getParameter("cpf_employee"));

                /* PERSITE O OBJETO NO BANCO */
                employeeDAO.create(employee);

                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("employees", employeeDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.CREATE_MESSAGE.toString());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/employee/read.jsp";
                break;
            case "read":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("employees", employeeDAO.read());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/employee/read.jsp";
                break;
            case "update":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("positions", positionSteeltratDAO.read());
                request.getSession().setAttribute("users", userSteeltratDAO.read());
                request.getSession().setAttribute("departaments", departamentDAO.read());
                request.getSession().setAttribute("employees", employeeDAO.read());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/employee/update.jsp";
                break;
            case "updateById":
                /* CRIA OBJETO */
                employee = employeeDAO.readById(Long.parseLong(request.getParameter("employees")));

                /* VARIÁVEIS DO FORM */
                employee.setNameEmployee(request.getParameter("name_employee"));
                employee.setIdUser(userSteeltratDAO.readById(Long.parseLong(request.getParameter("users"))));
                employee.setIdPosition(positionSteeltratDAO.readById(Long.parseLong(request.getParameter("positions"))));
                employee.setIdDepartament(departamentDAO.readById(Long.parseLong(request.getParameter("departaments"))));
                employee.setCpf(request.getParameter("cpf_employee"));

                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("employees", employeeDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.UPDATE_MESSAGE.toString());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/employee/read.jsp";
                break;
            case "delete":
                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("employees", employeeDAO.read());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/employee/delete.jsp";
                break;
            case "delete.confirm":
                /* CRIA OBJETO */
                employee = employeeDAO.readById(Long.parseLong(request.getParameter("employees")));

                /* PERSITE O OBJETO NO BANCO */
                employeeDAO.delete(employee);

                /* "SETA" ATRIBUTOS */
                request.getSession().setAttribute("employees", employeeDAO.read());
                request.getSession().setAttribute("returnMsgSuccessfully", ReturnMsgEnum.DELETE_MESSAGE.toString());

                /* REDIRECIONA PARA PÁGINA DESEJADA */
                returnPage = "WEB-INF/jsp/employee/read.jsp";
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

    private EmployeeDAO lookupEmployeeDAOBean() {
        try {
            Context c = new InitialContext();
            return (ind.br.vedax.model.dao.EmployeeDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/EmployeeDAO!ind.br.vedax.model.dao.EmployeeDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private DepartamentDAO lookupDepartamentDAOBean() {
        try {
            Context c = new InitialContext();
            return (DepartamentDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/DepartamentDAO!ind.br.vedax.model.dao.DepartamentDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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

    private UserSteeltratDAO lookupUserSteeltratDAOBean() {
        try {
            Context c = new InitialContext();
            return (UserSteeltratDAO) c.lookup("java:global/Steeltrat/Steeltrat-ejb/UserSteeltratDAO!ind.br.vedax.model.dao.UserSteeltratDAO");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
