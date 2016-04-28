package ind.br.vedax.api.weather;

import ind.br.vedax.api.entities.Temperature;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author GabrielOutor
 */
public class XMLParser {
    private DocumentBuilder builder;
    private Document doc;
    
    public XMLParser(){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            builder = dbf.newDocumentBuilder();
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void openURL(String uri){
        try {
            URL url = new URL(uri);
//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.10", 3128));
//            HttpURLConnection con = (HttpURLConnection) url.openConnection(proxy);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = rd.readLine()) != null){
                sb.append(line);
            }
            rd.close();
            con.disconnect();
            doc = builder.parse(new InputSource(new StringReader(sb.toString())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SAXException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void openFile(File file){
        
    }
    
    public Temperature parseCurrentWeather(){
        Temperature t = null;
        Element raiz = doc.getDocumentElement();
        Element temp = (Element)raiz.getElementsByTagName("temperature").item(0);
        Double value = Double.parseDouble(temp.getAttribute("value"));
        t = new Temperature();
        t.setValue(value);
        return t;
    }
}
