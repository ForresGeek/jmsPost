package com.elende.restservice;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.protobuf.MessageBuffer;
import org.apache.activemq.transport.stomp.Stomp.Headers.Subscribe.AckModeValues;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
public class JmsContext {
	

	public final static Logger LOGGER =LogManager.getLogger("com.elende.restservice.JmsContext");


		private Connection connection;
		private ConnectionFactory connectionFactory;
		private Session session;
		private Destination destination;
		//private MessageConsumer consumer;
		
		private MessageProducer producer;
		private MessageConverter messageConvertor;
	

		public JmsContext()
		{
			
			
		
		}
		
		
		
		
		public MessageProducer getProducer() {
			return producer;
		}

		public void setProducer(MessageProducer producer) {
			this.producer = producer;
		}

		public Session getSession() {
			return session;
		}

		public void setSession(Session session) {
			this.session = session;
		}

		public Connection getConnection() {
			return connection;
		}



		public void setConnection(Connection connection) {
			this.connection = connection;
		}


		public Destination getDestination() {
			return destination;
		}

		public void setDestination(Destination destination) {
			this.destination = destination;
		}
		
		
		
		
		public void SendCHI(Object inMsg) {
	
	
			try {
				
				connection = connectionFactory.createConnection();
				session = connection.createSession(false,0);
				producer = session.createProducer(destination);
				
				Message msg = messageConvertor.toMessage(inMsg, getSession());
				
				producer.send(msg);
						
				producer.close();
				session.close();
				connection.close();
				
				//Will this destroy the connection ??
				connection = null;
				
				
				
				
				} catch (JMSException e) {
			LOGGER.error("Error Sending JMS");
			}
			
			
		}
		



		public MessageConverter getMessageConvertor() {
			return messageConvertor;
		}




		public void setMessageConvertor(MessageConverter messageConvertor) {
			this.messageConvertor = messageConvertor;
		}




		public ConnectionFactory getConnectionFactory() {
			return connectionFactory;
		}




		public void setConnectionFactory(ConnectionFactory connectionFactory) {
			this.connectionFactory = connectionFactory;
		}
		
		
		
		
		
}
