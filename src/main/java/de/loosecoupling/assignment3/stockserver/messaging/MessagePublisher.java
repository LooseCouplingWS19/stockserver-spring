package de.loosecoupling.assignment3.stockserver.messaging;

import javax.jms.JMSException;
import javax.jms.TopicPublisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.loosecoupling.assignment3.stockserver.data.CompanyStock;

@Component
public class MessagePublisher {
	
	@Autowired
	MessageFactory messageFactory;
	@Autowired
	TopicPublisher topicPublisher;
	
	private static final Logger LOG = LoggerFactory.getLogger(MessagePublisher.class);

	public void publishMessageStockMarket(CompanyStock stock) throws JMSException {
		topicPublisher.send(messageFactory.generateStockServerMessage(stock));
		LOG.info("Sent message to StockMarket for Company: " + stock.getCompanyName());
	}
}
