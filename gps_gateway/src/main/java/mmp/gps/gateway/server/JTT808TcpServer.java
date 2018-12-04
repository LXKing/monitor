package mmp.gps.gateway.server;

import mmp.gps.gateway.codec.jtt808.JTT808ProtocolCodecFactory;
import mmp.gps.gateway.config.ServerSettings;
import mmp.gps.gateway.contract.IServer;
import mmp.gps.gateway.handler.JTT808Handler;
import mmp.gps.common.util.ExcepPrinter;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class JTT808TcpServer implements IServer {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private IoAcceptor acceptor;


    public JTT808TcpServer(ServerSettings settings) {
        int idleTime = settings.getIdleTime();

        try {
            this.acceptor = new NioSocketAcceptor();
            ((NioSocketAcceptor) this.acceptor).setReuseAddress(true);
            ((NioSocketAcceptor) this.acceptor).getSessionConfig().setReuseAddress(true);
            this.acceptor.getFilterChain().addLast("logger", new LoggingFilter());
            this.acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new JTT808ProtocolCodecFactory(settings.getReadBufferSize())));
            this.acceptor.setHandler(new JTT808Handler(1, idleTime));
            this.acceptor.getSessionConfig().setReadBufferSize(settings.getReadBufferSize());
            this.acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, idleTime);
            JTT808KeepAliveMessageFactory e = new JTT808KeepAliveMessageFactory();
            KeepAliveFilter heartBeat = new KeepAliveFilter(e, IdleStatus.BOTH_IDLE);
            heartBeat.setForwardEvent(true);
            heartBeat.setRequestInterval(idleTime + 5);
            this.acceptor.getFilterChain().addLast("heartbeat", heartBeat);
            if (settings.getIpAddress().equalsIgnoreCase("any")) {
                this.acceptor.setDefaultLocalAddress(new InetSocketAddress(settings.getPort()));
            } else {
                this.acceptor.setDefaultLocalAddress(new InetSocketAddress(InetAddress.getByName(settings.getIpAddress()), settings.getPort()));
            }

            this.acceptor.bind();
            cnsle.debug("初始化JTT808TcpServer：成功 ： 端口号" + settings.getPort());
        } catch (Exception var5) {
            cnsle.debug("初始化JTT808TcpServer：" + var5.getMessage());
            ExcepPrinter.print(var5);
        }

    }

    public void close() {
        if (this.acceptor != null) {
            this.acceptor.dispose();
        }

    }
}
