package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class Receiver {
	@Autowired
	JmsTemplate jmsTemplate;

    @JmsListener(destination = "somechintanbox")
    public void receiveEmailMessage(Email email) {
        System.out.println("Received <" + email + ">");
    }
    
    
    //SendGreetingMessage to the Rest Service
    @JmsListener(destination = "somechintanbox")
    public void receiveGreetingMessage(Greeting greeting) {
        System.out.println("Received <" + greeting.getContent() + ">");
    }
   
}
