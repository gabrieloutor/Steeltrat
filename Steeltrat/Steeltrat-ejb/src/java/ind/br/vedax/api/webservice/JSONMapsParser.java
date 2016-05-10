package ind.br.vedax.api.webservice;

import ind.br.vedax.api.entities.Place;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author GabrielOutor
 */
public class JSONMapsParser {
    public static Place parseFeed(String content){
        Place p = null;
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonObject raiz = reader.readObject();
        reader.close();
        
        JsonArray mainObj = raiz.getJsonArray("results");
        String long_name = mainObj.getJsonObject(0).getJsonArray("address_components").getJsonObject(1).getString("long_name");
        
        p = new Place(long_name);
        return p;
    }
}
