package com.luckysweetheart.jms.queen;

import org.apache.activemq.*;

import javax.jms.*;

/**
 * Created by yangxin on 2017/7/12.
 */
public class AppProvider {

    public static final String url = "tcp://localhost:61616";
    public static final String name = "queen-test";

    public static void main(String[] args) throws JMSException {

        // 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        // 创建连接
        Connection connection = connectionFactory.createConnection();

        // 启动连接
        connection.start();

        // 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 设置目的地
        Destination destination = session.createQueue(name);

        // 创建生产者，用于生产消息
        MessageProducer producer = session.createProducer(destination);

        // 发送消息
        for (int i = 0; i < 100; i++) {
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("text-" + i);
            producer.send(destination,textMessage);

            System.out.println("发送消息：" + textMessage.getText());

        }


    }

}
