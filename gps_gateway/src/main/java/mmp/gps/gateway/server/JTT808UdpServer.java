package mmp.gps.gateway.server;

import mmp.gps.common.util.ExcepPrinter;
import mmp.gps.gateway.codec.jtt808.JTT808ProtocolCodecFactory;
import mmp.gps.gateway.config.ServerSettings;
import mmp.gps.gateway.contract.IServer;
import mmp.gps.gateway.handler.JTT808Handler;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.ExpiringSessionRecycler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class JTT808UdpServer implements IServer {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private NioDatagramAcceptor acceptor;


    public JTT808UdpServer(ServerSettings settings) {
        try {
            int e = settings.getIdleTime();
            this.acceptor = new NioDatagramAcceptor();
            this.acceptor.getFilterChain().addLast("logger", new LoggingFilter());
            this.acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new JTT808ProtocolCodecFactory(settings.getReadBufferSize())));
            this.acceptor.setHandler(new JTT808Handler(2, settings.getIdleTime()));
            this.acceptor.getSessionConfig().setReadBufferSize(settings.getReadBufferSize());
            this.acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, e);
            this.acceptor.setSessionRecycler(new ExpiringSessionRecycler(e + 10));
            // UDP处理心跳保持连接
            JTT808KeepAliveMessageFactory heartBeatFactory = new JTT808KeepAliveMessageFactory();
            KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory, IdleStatus.BOTH_IDLE);
            heartBeat.setForwardEvent(true);
            heartBeat.setRequestInterval(e + 5);
            this.acceptor.getFilterChain().addLast("heartbeat", heartBeat);
            DatagramSessionConfig dcfg = this.acceptor.getSessionConfig();
            dcfg.setReuseAddress(true);
            if (settings.getIpAddress().equalsIgnoreCase("any")) {
                this.acceptor.setDefaultLocalAddress(new InetSocketAddress(settings.getPort()));
            } else {
                this.acceptor.setDefaultLocalAddress(new InetSocketAddress(InetAddress.getByName(settings.getIpAddress()), settings.getPort()));
            }

            this.acceptor.bind();
            cnsle.debug("初始化JJTT808UdpServer：成功 ： 端口号" + settings.getPort());
        } catch (Exception var6) {
            cnsle.debug("初始化JTT808UdpServer：" + var6.getMessage());
            ExcepPrinter.print(var6);
        }

    }

    public void close() {
        if (this.acceptor != null) {
            this.acceptor.dispose();
        }

    }
}
