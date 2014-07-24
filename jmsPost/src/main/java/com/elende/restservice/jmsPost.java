package com.elende.restservice;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


@Path("/SubmitCHI")
public class jmsPost   {

	
	@Context
	private ServletContext servletContext;
	
	
	public final static Logger LOGGER =LogManager.getLogger("com.elende.restservice.jmsPost");
	//private JmsTemplate jmsTemplate = null;
  
	
	

	  // This method is called if TEXT_PLAIN is request
	  @GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public String sayPlainTextHello() {
	    return "SubmitCHI Service Running";
	  }

	  // This method is called if XML is request
	  @GET
	  @Produces(MediaType.TEXT_XML)
	  public String sayXMLHello() {
	    return "<?xml version=\"1.0\"?>" + "<hello>SubmitCHI Service Running" + "</hello>";
	  }

	  // This method is called if HTML is request
	  @GET
	  @Produces(MediaType.TEXT_HTML)
	  public String sayHtmlHello() {
	    return "<html> " + "<title>" + "SubmitCHI Service Running" + "</title>"
	        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
	  }
	  
	  
	  
	  
	  
	/*  
	  @POST
		  @Produces(MediaType.TEXT_PLAIN)
	public String newPost(
				@PathParam("IdType") String IdType ,
				@PathParam("Id") String  Id 
			)  		
	  {
		  
		  MessageProducer producer = (MessageProducer) servletContext.getAttribute(ContextListener.ACTIVE_MQ_PRODUCER);
		  Session session = (Session) servletContext.getAttribute(ContextListener.ACTIVE_MQ_SESSION);

		  

		  CHIQuery chi = new CHIQuery();
		  chi.setId(Id);
		  chi.setIdType(IdType);
		  LOGGER.trace("Received Query:"+chi.toString() + " "+chi.hashCode());
				  
		  
		  Message msg;
		try {
			msg = QueryConvertor.toMessage(chi,session);
			 producer.send(msg);
			 
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Error sending message",e);
		}
		  
		
		  return "OK:"+chi.hashCode();
	  }
	  
	  */
	  
	  
	  
	  
	  
	  
	  

	  @POST
	  @Produces(MediaType.TEXT_PLAIN)
	  @Consumes(MediaType.APPLICATION_JSON)
	  public String jsonPost(CHIQuery chi)  		
	  {
		  
		  MessageProducer producer = (MessageProducer) servletContext.getAttribute(ContextListener.ACTIVE_MQ_PRODUCER);
		  Session session = (Session) servletContext.getAttribute(ContextListener.ACTIVE_MQ_SESSION);

		  LOGGER.trace("Received Query:"+chi.toString() );
				  
		  
		  Message msg;
		try {
			msg = QueryConvertor.toMessage(chi,session);
			 producer.send(msg);
			 
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Error sending message",e);
		}
		  
		
		  return "OK";
	  }

	  
	  
	  
	   
	   
	  
	  
	  	  
	  
	  
	  
	  
	  
	  

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(@Context ServletContext servletContext) {
		LOGGER.trace("Something injtected my servletContext"+servletContext.getServerInfo());
		this.servletContext = servletContext;
	}



} 
	