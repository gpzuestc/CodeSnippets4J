package com.gpzuestc.framework.mq.activemq;

import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActivemqDemo {
	
	private static final String ACTIVEMQ_URI = "failover:(tcp://10.16.48.79:51616,tcp://10.16.48.79:61616)?initialReconnectDelay=10&maxReconnectDelay=100&randomize=false";

	public static void main(String[] args) throws InterruptedException {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URI);
        Connection connection;
        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("demo");
            MessageProducer producer = session.createProducer(topic);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            
            for(int i = 0; i < 100; i++){
            	Message message = session.createMessage();
            	message.setStringProperty("sb", "gpz");
            	message.setIntProperty("age", i);
            	producer.send(message);
            	System.out.println("sended=" + i);
            	TimeUnit.SECONDS.sleep(2);
            }
            System.out.println("end");
        } catch (JMSException e) {
            e.printStackTrace();
        }
		
	}
	
	
	@Test
	public void testConsumer(){
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URI);
        Connection connection;
        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("demo");
            MessageConsumer consumer = session.createConsumer(topic);
            while(true){
            	System.out.println("receiving...");
            	Message message = consumer.receive();
            	System.out.println("received!");
            	System.out.println(message.getIntProperty("age"));
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
	}
}
