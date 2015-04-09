package jmsPost;

import static org.junit.Assert.*;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.elende.restservice.CHIQuery;
import com.elende.restservice.jmsPost;

public class TestJmsPost {
	
	 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		

	  
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	
	@Test
	@Ignore
	public void testJmsPost() {
		ClassPathXmlApplicationContext context  = new ClassPathXmlApplicationContext("beans.xml");
		
		com.elende.restservice.jmsPost jP = new jmsPost();
		
		CHIQuery chi = new CHIQuery();
		
		chi.setDOB("1969-04-02");
		chi.setForename("lee");
		chi.setSurname("dyson");
		chi.setNhsNumber("1234567890");
		chi.setUniqueIdentifier("33");
		
		String resp = jP.jsonPost(chi);
		
		assertTrue(resp.startsWith("OK"));
		
	}

}
