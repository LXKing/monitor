package mmp.gps.gateway.codec;

import mmp.gps.common.util.PackageUtil;
import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.gateway.contract.IRawDataParser;
import mmp.gps.protocol.jtt808.JTT808Packet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class JTT808RawDataParser implements IRawDataParser {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private HashMap commandHandlers;


    public int getProtocolKind() {
        return 1;
    }

    public void parse(IoSession session, Object raw, byte[] data) {
        JTT808Packet packet = (JTT808Packet) raw;
        ICommandHandler handler = (ICommandHandler) this.getcommandHandlers().get(Integer.valueOf(packet.getCommand()));
        if (handler != null) {
            handler.process(session, raw, data);
        }
    }

    public HashMap getcommandHandlers() {
        if (this.commandHandlers == null) {
            this.init();
            cnsle.debug("JTT808RawDataParser共：" + this.commandHandlers.size() + "个指令处理器");
        }

        return this.commandHandlers;
    }

    private synchronized void init() {
        if (this.commandHandlers == null) {
            this.commandHandlers = new HashMap();
            List classes = PackageUtil.getSubClasses("mmp.gps.gateway.codec.jtt808.Handler", ICommandHandler.class);
            Iterator var3 = classes.iterator();

            while (var3.hasNext()) {
                Class c = (Class) var3.next();

                try {
                    ICommandHandler e = (ICommandHandler) c.newInstance();
                    this.commandHandlers.put(e.getName(), e);
                } catch (Exception var5) {
                    cnsle.debug("初始化JTT808RawDataParser：" + var5.getMessage());
                }
            }

        }
    }
}
