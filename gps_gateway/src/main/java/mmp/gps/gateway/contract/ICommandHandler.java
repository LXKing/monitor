package mmp.gps.gateway.contract;

import org.apache.mina.core.session.IoSession;

public interface ICommandHandler {

    Object getName();

    void process(IoSession var1, Object var2, byte[] var3);
}
