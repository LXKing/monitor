package mmp.gps.gateway.server;

import mmp.gps.gateway.codec.kangkaisi.kangkaisiProtocolCodecFactory;
import mmp.gps.gateway.config.ServerSettings;
import mmp.gps.gateway.contract.IServer;
import mmp.gps.gateway.handler.kangkaisiHandler;
import mmp.gps.common.util.ExcepPrinter;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class kangkaisiTcpServer implements IServer {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private IoAcceptor acceptor;


    public kangkaisiTcpServer(ServerSettings settings) {
        short idleTime = 480;

        try {
            this.acceptor = new NioSocketAcceptor();
            ((NioSocketAcceptor) this.acceptor).setReuseAddress(true);
            ((NioSocketAcceptor) this.acceptor).getSessionConfig().setReuseAddress(true);
            this.acceptor.getFilterChain().addLast("logger", new LoggingFilter());
            this.acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new kangkaisiProtocolCodecFactory(settings.getReadBufferSize())));
            this.acceptor.setHandler(new kangkaisiHandler(1, idleTime));
            this.acceptor.getSessionConfig().setReadBufferSize(settings.getReadBufferSize());
            this.acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, idleTime);
            if (settings.getIpAddress().equalsIgnoreCase("any")) {
                this.acceptor.setDefaultLocalAddress(new InetSocketAddress(settings.getPort()));
            } else {
                this.acceptor.setDefaultLocalAddress(new InetSocketAddress(InetAddress.getByName(settings.getIpAddress()), settings.getPort()));
            }

            this.acceptor.bind();
            cnsle.debug("初始化kangkaisiTcpServer：成功 ： 端口号" + settings.getPort() + "  空闲时间：" + idleTime);
        } catch (Exception var4) {
            cnsle.debug("初始化kangkaisiTcpServer：" + var4.getMessage());
            ExcepPrinter.print(var4);
        }

    }

    public void close() {
        if (this.acceptor != null) {
            this.acceptor.dispose();
        }

    }
}
