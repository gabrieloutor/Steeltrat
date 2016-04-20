package ind.br.vedax.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    public void init(HttpServletRequest request, HttpServletResponse response);
    // METODO EXECUTE PARA EXECUTAR TUDO QUE TIVER QUE EXECUTAR
    public void execute();
    // METODO RETORNA STRING PARA RETORNAR PAGE DE RETORNO
    public String getReturnPage();
}
