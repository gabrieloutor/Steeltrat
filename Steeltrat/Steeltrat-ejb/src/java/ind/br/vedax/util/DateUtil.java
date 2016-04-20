package ind.br.vedax.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateUtil {
    
    private static SimpleDateFormat formatter = new SimpleDateFormat ("dd/MM/yyyy HH:mm");
    
    public static String date2String(Date date){
        return formatter.format(date);
    }
    
    public static Date string2Date(String str){
        Date date=null;
        try {
            date = formatter.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
}
