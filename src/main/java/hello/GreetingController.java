package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	 @Autowired
	 JmsTemplate jmsTemplate;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    //Send the Greeting message to the Greeting Channel
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name,
    		@RequestParam(value="age",defaultValue="35") String age) {
    	
    	Greeting greeting = new Greeting(counter.incrementAndGet(),
                String.format(template, name),Long.parseLong(age));
    	 jmsTemplate.convertAndSend("Greeting", greeting);
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name),Long.parseLong(age));
    }
    
}
