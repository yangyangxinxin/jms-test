package com.luckysweetheart.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by yangxin on 2017/7/12.
 */
public class AppProvider {

    public static final String url = "tcp://localhost:61616";
    public static final String name = "topic-test";

    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createTopic(name);

        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("text-" + i);
            producer.send(destination,textMessage);

            System.out.println("发送消息：" + textMessage.getText());

        }


    }

}
