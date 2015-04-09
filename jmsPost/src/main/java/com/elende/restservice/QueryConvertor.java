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
		
		chi.setDOB(((MapMessage) arg0).getString("DOB"));
		chi.setForename(((MapMessage) arg0).getString("forename"));
		chi.setSurname(((MapMessage) arg0).getString("surname"));
		chi.setUniqueIdentifier(((MapMessage) arg0).getString("uniqueIdentifier"));
		chi.setNhsNumber(((MapMessage) arg0).getString("nhsNumber"));
		
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
	
	
	message.setString("DOB", arg0.getDOB());
	message.setString("forename", arg0.getForename());
	message.setString("surname", arg0.getSurname());
	message.setString("uniqueIdentifier", arg0.getUniqueIdentifier());
	message.setString("nhsNumber", arg0.getNhsNumber());
		
	
	//This is the secret Casper signature.. if this isn't here... we won't process it..
	message.setBooleanProperty("ISCASPER",true);

		
	return message;
}
	
	
}
