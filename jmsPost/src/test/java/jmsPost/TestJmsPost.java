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

import com.elende.restservice.jmsPost;

public class TestJmsPost {
	
	 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		

	  
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	@Ignore
	@Test
	public void testJmsPost() {
		ClassPathXmlApplicationContext context  = new ClassPathXmlApplicationContext("beans.xml");
		
		com.elende.restservice.jmsPost jP = new jmsPost();
		
		String resp = jP.newPost("CHI", "123456");
		
		assertTrue(resp.startsWith("OK"));
		
	}

}
