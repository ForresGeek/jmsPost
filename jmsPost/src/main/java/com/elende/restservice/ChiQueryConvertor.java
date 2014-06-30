package com.elende.restservice;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class ChiQueryConvertor implements MessageConverter {

	public Object fromMessage(Message arg0) throws JMSException,
			MessageConversionException {
		// TODO Auto-generated method stub
	    CHIQuery chi = null;
	    boolean isCasper = false;
			if (arg0 instanceof MapMessage)
			{
				chi = new CHIQuery();
				chi.setId(((MapMessage) arg0).getString("Id"));
				chi.setIdType(((MapMessage) arg0).getString("IdType"));
				isCasper = ((MapMessage)arg0).getBooleanProperty("ISCASPER");
				
				if (!isCasper)
					throw new MessageConversionException("Incoming Message has no CasperSignature");

			}
			else
			{
				throw new MessageConversionException("Incoming Message in not MapMessage");
			}
			
		
		return chi;
	}

	public Message toMessage(Object arg0, Session arg1) throws JMSException,
			MessageConversionException {

		MapMessage message = arg1.createMapMessage();
		
		CHIQuery chi = (CHIQuery)arg0;
		
		message.setString("Id",chi.getId());
		message.setString("IdType",chi.getIdType());
		
		//This is the secret Casper signature.. if this isn't here... we won't process it..
		message.setBooleanProperty("ISCASPER",true);
		
				
		return message;
	}

}
