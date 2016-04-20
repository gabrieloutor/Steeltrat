package ind.br.vedax.util;

import java.util.Calendar;
import java.util.Date;

public class TesteDate {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("Data para String :\n"+DateUtil.date2String(date));
        System.out.println("-----------");
        String str = "20/07/2010 13:14:15";
        System.out.println("String para Date :\n"+DateUtil.string2Date(str));
        System.out.println("-----------");
        
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 19);
        cal.set(Calendar.MONTH, 3); //ABRIL - VETOR COMECA NO 0
        cal.set(Calendar.YEAR, 2016);
        System.out.println(DateUtil.date2String(cal.getTime()));
        
        Date date3 = cal.getTime();
        
        //USA NO BANCO PARA BD
        java.sql.Date dateSQL = new java.sql.Date(date3.getTime());
    }
    
}
