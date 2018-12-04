package mmp.gps.gateway.server;

import mmp.gps.gateway.config.ServerSettings;
import mmp.gps.gateway.contract.IServer;
import mmp.gps.gateway.handler.Tsc808Handler;
import mmp.gps.common.util.ExcepPrinter;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.ExpiringSessionRecycler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

public class Phoenix808UdpServer implements IServer {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private NioDatagramAcceptor acceptor;


    public Phoenix808UdpServer(ServerSettings settings) {
        try {
            int e = settings.getIdleTime();
            this.acceptor = new NioDatagramAcceptor();
            this.acceptor.getFilterChain().addLast("logger", new LoggingFilter());
            this.acceptor.setHandler(new Tsc808Handler(2, settings.getIdleTime()));
            this.acceptor.getSessionConfig().setReadBufferSize(settings.getReadBufferSize());
            this.acceptor.setSessionRecycler(new ExpiringSessionRecycler(e + 10));
            if (settings.getIpAddress().equalsIgnoreCase("any")) {
                this.acceptor.setDefaultLocalAddress(new InetSocketAddress(settings.getPort()));
            } else {
                this.acceptor.setDefaultLocalAddress(new InetSocketAddress(InetAddress.getByName(settings.getIpAddress()), settings.getPort()));
            }

            this.acceptor.bind();
            cnsle.debug("初始化Phoenix808UdpServer：成功 ： 端口号" + settings.getPort());
        } catch (Exception var3) {
            cnsle.debug("初始化Phoenix808UdpServer：" + var3.getMessage());
            ExcepPrinter.print(var3);
        }

    }

    public void close() {
        if (this.acceptor != null) {
            this.acceptor.dispose();
        }

    }
}
