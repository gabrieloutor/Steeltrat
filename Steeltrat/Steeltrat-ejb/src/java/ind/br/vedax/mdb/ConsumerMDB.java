package ind.br.vedax.mdb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author 1147106
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/lp3"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ConsumerMDB implements MessageListener {

    public ConsumerMDB() {
    }

    @Override
    public void onMessage(Message message) {
        TextMessage txtmsg = (TextMessage) message;
        try {
            File file = new File("/Users/GabrielOutor/Documents/log.txt");
            if(!file.exists()) file.createNewFile();
            
            SimpleDateFormat formatter = 
                    new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            
            String txt = new String(carregar(file));
            txt += "\n"+formatter.format(new Date())+" - "+txtmsg.getText();
            
            salvar(file, txt);
            
        } catch (JMSException ex) {
            Logger.getLogger(ConsumerMDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConsumerMDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ConsumerMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public byte[] carregar(File arquivo) throws Exception {
        FileInputStream dispositivoDeEntrada = new FileInputStream(arquivo);
        byte[] conteudo = new byte[dispositivoDeEntrada.available()];
        dispositivoDeEntrada.read(conteudo);
        return conteudo;
    }

    public void salvar(File arquivo, String conteudo) throws Exception {
        FileOutputStream streamDeSaida = new FileOutputStream(arquivo);
        streamDeSaida.write(conteudo.getBytes());
        streamDeSaida.close();
    }

}
