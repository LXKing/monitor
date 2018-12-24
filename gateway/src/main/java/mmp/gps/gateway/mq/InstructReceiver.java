package mmp.gps.gateway.mq;

import mmp.gps.common.util.ExcepPrinter;
import mmp.gps.domain.gateway.InstructStatusRequest;
import mmp.gps.gateway.codec.InstructBuilders;
import mmp.gps.gateway.config.AppSettings;
import mmp.gps.gateway.config.InstructSettings;
import mmp.gps.gateway.contract.IInstructBuilder;
import mmp.gps.gateway.domain.DataSender;
import mmp.gps.gateway.domain.ReplyValidator;
import mmp.gps.gateway.domain.SessionManager;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import javax.jms.*;

public class InstructReceiver {
    private static Logger cnsle = Logger.getLogger("cnsle");
    private ConnectionFactory connectionFactory;
    private Connection connection = null;
    private Session session;
    private Destination destination;
    private MessageConsumer consumer;
    private InstructSettings settings;
    private static Thread receiveThread;
    private static InstructReceiver receiver;

    public InstructReceiver() {
        cnsle.debug("ActiveMQ 启动");
        this.settings = AppSettings.current().getInstruct();
        String user = this.settings.getUser();
        String password = this.settings.getPassword();
        if ((user == null) || (user.isEmpty())) {
            user = ActiveMQConnection.DEFAULT_USER;
        }
        if ((password == null) || (password.isEmpty())) {
            password = ActiveMQConnection.DEFAULT_PASSWORD;
        }
        this.connectionFactory = new ActiveMQConnectionFactory(user, password, this.settings.getMqUrl());
        cnsle.debug("mq:" + this.settings.getQueueName() + this.settings.getMqUrl());
        try {
            this.connection = this.connectionFactory.createConnection();

            this.connection.start();

            this.session = this.connection.createSession(Boolean.FALSE.booleanValue(), 1);

            this.destination = this.session.createQueue(this.settings.getQueueName());
            this.consumer = this.session.createConsumer(this.destination);

            // 监听 GODP MQ消息，发送指令
            this.consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    if (message == null) {
                        return;
                    }
                    try {
                        TextMessage m = (TextMessage) message;

                        InstructReceiver.cnsle.debug("mq: m.getText()" + m.getText());
                        String[] content = m.getText().split("\t", 4);

                        String deviceNumber = content[0];

                        String platformSerialNumber = content[1];

                        String command = content[2];

                        String para = content[3];

                        InstructReceiver.this.send(deviceNumber, platformSerialNumber, command, para);
                    } catch (JMSException e) {
                        InstructReceiver.cnsle.debug("mq: 下发指令：" + e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            cnsle.debug("初始化InstructReceiver：" + e.getMessage());
            ExcepPrinter.print(e);
        }
    }

    // 发送指令并POST返回消息给GODP
    private void send(String deviceNumber, String platformSerialNumber, String command, String para) {
        try {
            IoSession session = SessionManager.getCurrent().get(deviceNumber);
            if (session == null) {
                DataSender.getCurrent().send(new InstructStatusRequest(platformSerialNumber, deviceNumber, "设备不在线"));
                return;
            }
            ReplyValidator map = (ReplyValidator) session.getAttribute("instructs");
            if (map == null) {
                DataSender.getCurrent().send(new InstructStatusRequest(platformSerialNumber, deviceNumber, "设备不在线"));
                return;
            }
            int protocolKind = ((Integer) session.getAttribute("protocolkind")).intValue();

            IInstructBuilder builder = InstructBuilders.get(protocolKind);
            if (builder == null) {
                DataSender.getCurrent().send(new InstructStatusRequest(platformSerialNumber, deviceNumber, "不支持的协议类型"));
                return;
            }
            byte[] message = builder.build(platformSerialNumber, deviceNumber, command, para, map);
            if (message == null) {
                DataSender.getCurrent().send(new InstructStatusRequest(platformSerialNumber, deviceNumber, "指令参数错误"));
                return;
            }
            int netKind = ((Integer) session.getAttribute("netkind")).intValue();


            // 发送指令
            if (netKind == 1) {
                session.write(message);
            } else {
                session.write(message, session.getRemoteAddress());
            }

            DataSender.getCurrent().send(new InstructStatusRequest(platformSerialNumber, deviceNumber, "网关下发成功"));
            cnsle.debug("下发:" + deviceNumber + "=>" + IoBuffer.wrap(message).getHexDump());
        } catch (Exception e) {
            DataSender.getCurrent().send(new InstructStatusRequest(platformSerialNumber, deviceNumber, "指令参数错误"));
            ExcepPrinter.print(e);
        }
    }

    public void dispose() {
        try {
            try {
                if (this.connection != null) {
                    this.connection.close();
                }
            } catch (Throwable ignore) {
                ignore.printStackTrace();
            }
            return;
        } catch (Exception e) {
            ExcepPrinter.print(e);
        }
    }

    private static Runnable runThread = () -> InstructReceiver.receiver = new InstructReceiver();

    public static void start() {
        receiveThread = new Thread(runThread);
        receiveThread.start();
    }

    public static void stop() {
        if (receiveThread != null) {
            receiveThread.interrupt();
        }
        receiver.dispose();
    }
}
