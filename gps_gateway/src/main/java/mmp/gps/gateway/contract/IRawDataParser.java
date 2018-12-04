package mmp.gps.gateway.contract;

import org.apache.mina.core.session.IoSession;

public interface IRawDataParser {

    int getProtocolKind();

    void parse(IoSession var1, Object var2, byte[] var3);
}
