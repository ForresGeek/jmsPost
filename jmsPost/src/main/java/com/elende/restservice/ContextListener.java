package com.elende.restservice;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@WebListener
public class ContextListener implements ServletContextListener {

	
	public final static Logger LOGGER = LogManager.getLogger("com.elende.restservice.ContextListener");
	
	
	public final static String ACTIVE_MQ_SESSION = "ActiveMQSession";
    public final static String ACTIVE_MQ_PRODUCER = "ActiveMQProducer";
    private static final int ackMode = Session.AUTO_ACKNOWLEDGE;
    private static final boolean transacted = false;

    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private ConnectionFactory connectionFactory;
    private Destination destination;

    
    
    
    
    public void contextDestroyed(ServletContextEvent sce) {
    	LOGGER.debug("Destroying CONTEXT");
    	try {
            this.producer.close();
            this.session.close();
            this.connection.stop();
            this.connection.close();
        } catch (JMSException e) {
            LOGGER.warn("tearDown()", e);
        }

    }

    public void contextInitialized(ServletContextEvent sce) {
    	
    	LOGGER.debug("Initializing CONTEXT");
    	
    	ClassPathXmlApplicationContext context  = new ClassPathXmlApplicationContext("beans.xml");
    	connectionFactory =  (ConnectionFactory)context.getBean("cachedConnectionFactory");
		
    	destination =  (Destination)context.getBean("sciDest");
    	
      
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(transacted, ackMode);
            producer = session.createProducer(destination);

            ServletContext sc = sce.getServletContext();
            sc.setAttribute(ACTIVE_MQ_SESSION, session);
            sc.setAttribute(ACTIVE_MQ_PRODUCER, producer);
            
        	LOGGER.debug("Initialized CONTEXT");
        	
            
        } catch (JMSException e) {
            LOGGER.warn("setup() failed to setup connection to JMS ",e);
        }
 }

}