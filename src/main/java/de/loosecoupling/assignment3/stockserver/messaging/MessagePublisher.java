package de.loosecoupling.assignment3.stockserver.messaging;

import javax.jms.JMSException;
import javax.jms.TopicPublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.loosecoupling.assignment3.stockserver.data.CompanyStock;

@Component
public class MessagePublisher {
	
	@Autowired
	MessageFactory messageFactory;
	@Autowired
	TopicPublisher topicPublisher;

	public void publishMessageStockMarket(CompanyStock stock) throws JMSException {
		topicPublisher.send(messageFactory.generateStockServerMessage(stock));
	}
}
