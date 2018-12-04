package mmp.gps.gateway.contract;

import mmp.gps.gateway.domain.ReplyValidator;

public interface IInstructBuilder {

    int getProtocolKind();

    String getName();

    byte[] build(String var1, String var2, String var3, String var4, ReplyValidator var5) throws Exception;
}
