package mmp.gps.logic.net;


import mmp.gps.domain.device.Device;
import mmp.gps.logic.config.ActiveMQConfiguration;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class InstructSender {
    private ConnectionFactory connectionFactory;
    private Connection connection = null;
    private Session session;
    private static Thread sendThread;
    private static InstructSender sender;

    public void sendMessage(Device device, String message)
            throws Exception {
        SenderProxy proxy = new SenderProxy();

        System.out.println(device.getHost() + " message" + message);
        Destination destination = this.session.createQueue(device.getHost());

        MessageProducer producer = this.session.createProducer(destination);

        producer.setDeliveryMode(1);

        proxy.destination = destination;
        proxy.producer = producer;

        TextMessage text = this.session.createTextMessage(message);
        proxy.producer.send(text);

        this.session.commit();
    }

    public InstructSender() {
        String user = ActiveMQConfiguration.getUser();
        String password = ActiveMQConfiguration.getPassword();
        String url = ActiveMQConfiguration.getUrl();
        if ((user == null) || (user.isEmpty())) {
            user = ActiveMQConnection.DEFAULT_USER;
        }
        if ((password == null) || (password.isEmpty())) {
            password = ActiveMQConnection.DEFAULT_PASSWORD;
        }
        this.connectionFactory = new ActiveMQConnectionFactory(user, password, url);
        try {
            this.connection = this.connectionFactory.createConnection();

            this.connection.start();

            this.session = this.connection.createSession(Boolean.TRUE.booleanValue(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dispose() {
        try {
            try {
                if (this.session != null) {
                    this.session.close();
                }
                if (null != this.connection) {
                    this.connection.close();
                }
            } catch (Throwable ignore) {
                ignore.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Runnable runThread = new Runnable() {
        public void run() {
            InstructSender.sender = new InstructSender();
        }
    };

    public static void start() {
        sendThread = new Thread(runThread);
        sendThread.start();
    }

    public static void stop() {
        if (sendThread != null) {
            sendThread.interrupt();
        }
        if (sender != null) {
            sender.dispose();
        }
    }

    public static void send(Device device, String message)
            throws Exception {
        if (sender == null) {
            return;
        }
        sender.sendMessage(device, message);
    }
}
