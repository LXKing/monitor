package mmp.gps.gateway.codec;

import mmp.gps.common.util.PackageUtil;
import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.gateway.contract.IRawDataParser;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket79;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class kangkaisiRawDataParser implements IRawDataParser {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private HashMap commandHandlers;


    public int getProtocolKind() {
        return 2;
    }

    public void parse(IoSession session, Object raw, byte[] data) {
        ICommandHandler handler;
        if (data[0] == 120) {
            KangkaisiPacket packet = (KangkaisiPacket) raw;
            handler = (ICommandHandler) this.getcommandHandlers().get(Short.valueOf(packet.getProtocolNumber()));
            if (handler == null) {
                cnsle.debug("没有找到该协议的处理：" + packet.getProtocolNumber());
                return;
            }

            handler.process(session, raw, data);
        } else if (data[0] == 121) {
            KangkaisiPacket79 packet1 = (KangkaisiPacket79) raw;
            handler = (ICommandHandler) this.getcommandHandlers().get(Short.valueOf(packet1.getProtocolNumber()));
            if (handler == null) {
                cnsle.debug("没有找到该协议的处理：" + packet1.getProtocolNumber());
                return;
            }

            handler.process(session, raw, data);
        }

    }

    public HashMap getcommandHandlers() {
        if (this.commandHandlers == null) {
            this.init();
            cnsle.debug("kangkaisiRawDataParser共：" + this.commandHandlers.size() + "个指令处理器");
        }

        return this.commandHandlers;
    }

    private synchronized void init() {
        if (this.commandHandlers == null) {
            this.commandHandlers = new HashMap();
            List classes = PackageUtil.getSubClasses("mmp.gps.gateway.codec.kangkaisi.Handler", ICommandHandler.class);
            Iterator var3 = classes.iterator();

            while (var3.hasNext()) {
                Class c = (Class) var3.next();

                try {
                    ICommandHandler e = (ICommandHandler) c.newInstance();
                    this.commandHandlers.put(e.getName(), e);
                } catch (Exception var5) {
                    cnsle.debug("初始化康凯斯RawDataParser：" + var5.getMessage());
                }
            }

        }
    }
}
