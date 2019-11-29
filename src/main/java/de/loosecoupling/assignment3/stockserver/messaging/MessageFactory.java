package de.loosecoupling.assignment3.stockserver.messaging;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Topic;
import javax.jms.TopicSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.loosecoupling.assignment3.stockserver.data.CompanyStock;

@Component
public class MessageFactory {
	
	@Autowired
	Topic stockMarketTopic;
	@Autowired
	TopicSession activeMQSession;

	public MapMessage generateStockServerMessage(CompanyStock stock) throws JMSException {
		MapMessage message = activeMQSession.createMapMessage();
		message.setJMSDestination(stockMarketTopic);
		message.setInt(stock.getCompanyName(), stock.getCompanyValue());
		return message;	
	}
}
