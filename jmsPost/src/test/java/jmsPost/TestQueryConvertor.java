package jmsPost;

import static org.junit.Assert.*;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.support.converter.MessageConversionException;

import com.elende.restservice.CHIQuery;
import com.elende.restservice.QueryConvertor;
import com.elende.restservice.jmsPost;

public class TestQueryConvertor {

	
	
	MessageProducer prod;
	Session sess; 
	
	MapMessage badMessage;
	MapMessage goodMessage;
	
	CHIQuery ch1;
	CHIQuery ch2;
	

	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


	
	@Before public void init() throws JMSException
	{
		
		
		ClassPathXmlApplicationContext context  = new ClassPathXmlApplicationContext("beans.xml");
		
		 prod = (MessageProducer) context.getBean("jmsProducer");
		 sess = (Session)context.getBean("jmsSession");
	
		
		
		ch1 = new CHIQuery();
		ch1.setDOB("1234567");
		ch1.setUniqueIdentifier("33");
		
		
		badMessage = sess.createMapMessage();
		badMessage.setString("surname","123446");
		badMessage.setString("uniqueIdentifier", "CHI");
	
		goodMessage = sess.createMapMessage();
		goodMessage.setString("surname", "123446");
		goodMessage.setBooleanProperty("ISCASPER", true);
		goodMessage.setString("uniqueIdentifier", "CHI");
		
		
	}
	
	
	
	@Test
	public void testToMessage() {

		
		try {

			Object msg = QueryConvertor.toMessage(ch1,sess);

			assertTrue(msg instanceof MapMessage);
			MapMessage mmsg = (MapMessage)msg;
			
			assertTrue(mmsg.getBooleanProperty("ISCASPER"));
			assertTrue(mmsg.getString("CasperSerial").length() >10);
			
			
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	@Test(expected=MessageConversionException.class)
	public void testbadFromMessage() throws MessageConversionException, JMSException {
		CHIQuery chi = QueryConvertor.fromMessage(badMessage);	
	}
	

	@Test
	public void testgoodFromMessage() throws MessageConversionException, JMSException {
		CHIQuery chi = QueryConvertor.fromMessage(goodMessage);
		assertTrue(chi.getSurname()=="123446");
		assertTrue(chi.getUniqueIdentifier()=="CHI");
		
	}
	
	
	
	
	
	
	

}
