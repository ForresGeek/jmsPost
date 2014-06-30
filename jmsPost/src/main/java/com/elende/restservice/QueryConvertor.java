package com.elende.restservice;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;

public class QueryConvertor {

	
	public static CHIQuery fromMessage(Message arg0) throws JMSException,MessageConversionException {
// TODO Auto-generated method stub
		CHIQuery chi = null;
		boolean isCasper = false;
		
	if (arg0 instanceof MapMessage)
	{
		chi = new CHIQuery();
		chi.setId(((MapMessage) arg0).getString("Id"));
		chi.setIdType(((MapMessage) arg0).getString("IdType"));
		isCasper = ((MapMessage)arg0).getBooleanProperty("ISCASPER");
		
		if(!isCasper)
			throw new MessageConversionException("No CASPER Signature in message");
	}
	else
	{
		throw new MessageConversionException("Incoming Message in not MapMessage");
	}
	

	return chi;
	}

public static Message toMessage(CHIQuery arg0, Session arg1) throws JMSException
{

	MapMessage message = arg1.createMapMessage();
    message.setString("CasperSerial",UUID.randomUUID().toString());
	message.setString("Id",arg0.getId());
	message.setString("IdType",arg0.getIdType());
	//This is the secret Casper signature.. if this isn't here... we won't process it..
	message.setBooleanProperty("ISCASPER",true);

		
	return message;
}
	
	
}
