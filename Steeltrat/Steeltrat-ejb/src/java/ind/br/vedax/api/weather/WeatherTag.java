package ind.br.vedax.api.weather;

import ind.br.vedax.api.entities.Temperature;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author GabrielOutor
 */
public class WeatherTag extends SimpleTagSupport{

    private String city;
    public void setCity(String city) {
        this.city = city;
    }
    
    private String units;
    public void setUnits(String units) {
        this.units = units;
    }
    
    private String appid;
    public void setAppid(String appid) {
        this.appid = appid;
    }
    
    @Override
    public void doTag() throws JspException, IOException {
        String uri = "http://api.openweathermap.org/data/2.5/weather?q="+city+",br&appid="+appid+"&mode=xml&units="+units;
        XMLParser xmlp = new XMLParser();
        xmlp.openURL(uri);
        Temperature t = xmlp.parseCurrentWeather();
        JspWriter out = getJspContext().getOut();
        
        
        out.print(
                "<div>"
                        + "<h1>"+ city + "</h1>"
                        + "<h3>"+ t.getValue() + "</h3>"
              + "</div>"
        );
    }
    
}
