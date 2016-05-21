package ind.br.vedax.jms;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author 1147106
 */
@Stateless
@LocalBean
public class ProducerBean implements ProducerBeanLocal {
    @Resource(mappedName = "jms/lp3")
    private Queue lp3;
    
    @Inject
    @JMSConnectionFactory("jms/lp3Factory")
    private JMSContext context;

    private void sendJMSMessageToLp3(String messageData) {
        context.createProducer().send(lp3, messageData);
    }

    @Override
    public void sendMessage(String msg) {
        sendJMSMessageToLp3(msg);
    }
    
    

    
}
