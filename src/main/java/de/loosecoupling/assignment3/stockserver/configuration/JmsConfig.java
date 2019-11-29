package de.loosecoupling.assignment3.stockserver.configuration;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TemporaryTopic;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfig {
	
	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;
	@Value("${spring.activemq.user}")
	private String brokerUser;
	@Value("${spring.activemq.password}")
	private String brokerPassword;

	@Bean
	public TopicConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);
		activeMQConnectionFactory.setUserName(brokerUser);
		activeMQConnectionFactory.setPassword(brokerPassword);

		return (TopicConnectionFactory) activeMQConnectionFactory;
	}
	
	@Bean
	public TopicConnection activeMQConnection(TopicConnectionFactory activeMQConnectionFactory) throws JMSException {
		TopicConnection activeMQConnection = activeMQConnectionFactory.createTopicConnection();
		activeMQConnection.start();
		return activeMQConnection;
	}
	
	@Bean
	public TopicSession activeMQSession(TopicConnection activeMQConnection) throws JMSException {
		return activeMQConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
	}
	
	@Bean
	public Topic stockMarketTopic(TopicSession activeMQSession) throws JMSException {
		return activeMQSession.createTopic("StockMarket");
	}
	
	@Bean
	public TemporaryTopic buyOrdersTopic(TopicSession activeMQSession) throws JMSException {
		return activeMQSession.createTemporaryTopic();
	}
	
	@Bean
	public TopicPublisher topicPublisher(TopicSession activeMQSession, Topic stockMarketTopic) throws JMSException {
		TopicPublisher publisher = activeMQSession.createPublisher(stockMarketTopic);
		publisher.setDeliveryMode(DeliveryMode.PERSISTENT);
		return publisher;
	}
	
}
