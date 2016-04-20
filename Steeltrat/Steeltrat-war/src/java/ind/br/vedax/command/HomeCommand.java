package ind.br.vedax.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeCommand implements Command{
    public HttpServletRequest request;
    public HttpServletResponse response;
    public String returnPage = "WEB-INF/jsp/home.jsp";
    
    @Override
    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void execute() {
    }

    @Override
    public String getReturnPage() {
        return returnPage;
    }

}
