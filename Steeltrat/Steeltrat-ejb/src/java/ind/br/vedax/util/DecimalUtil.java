package ind.br.vedax.util;

import java.text.DecimalFormat;

/**
 *
 * @author GabrielOutor
 */
public class DecimalUtil {
    private static DecimalFormat dfInteger = new DecimalFormat ("#,##0");
    private static DecimalFormat dfDouble = new DecimalFormat ("#,##0.00");
    
    public static String integerToDecimal(Integer number){
        return dfInteger.format(number);
    }
    
    public static String doubleToDecimal(Double number){
        return dfDouble.format(number);
    }
}
