package mmp.gps.gateway.codec.kangkaisi.Handler;

import mmp.gps.gateway.contract.ICommandHandler;
import mmp.gps.gateway.domain.DataSender;
import mmp.gps.protocol.jtt808.JTT808Util;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket;
import mmp.gps.protocol.kangkaisi.body.GPSAddressRequestBody;
import mmp.gps.protocol.kangkaisi.body.RawBody;
import mmp.gps.protocol.kangkaisi.body.ServerReplyAddressRequestBody;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

public class GPSAddressRequestHandler implements ICommandHandler {

    private Logger cmdLog = Logger.getLogger("cmd");


    public Object getName() {
        return Short.valueOf((short) 42);
    }

    public void process(IoSession session, Object raw, byte[] data) {
        KangkaisiPacket request = (KangkaisiPacket) raw;
        RawBody rawBody = (RawBody) request.getBody();
        GPSAddressRequestBody gpsAddressRequestBody = new GPSAddressRequestBody();
        gpsAddressRequestBody.from(rawBody.getRaw());
        String addressContent = DataSender.getCurrent().sendbaidu(String.valueOf((float) gpsAddressRequestBody.getLat() / 1800000.0F), String.valueOf((float) gpsAddressRequestBody.getLng() / 1800000.0F));
        ServerReplyAddressRequestBody serverReplySchoolBody = new ServerReplyAddressRequestBody();
        serverReplySchoolBody.setServerFlagBit(1L);
        serverReplySchoolBody.setAddressContent(addressContent);
        serverReplySchoolBody.setAddressRequest("ADDRESS");
        serverReplySchoolBody.setDelimiter1("&&");
        serverReplySchoolBody.setDelimiter2("&&");
        serverReplySchoolBody.setDelimiter3("##");
        serverReplySchoolBody.setPhoneNumber("8613421632699");
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket(serverReplySchoolBody);
        kangkaisiPacket.setProtocolNumber((short) 23);
        kangkaisiPacket.setSerialNumber(JTT808Util.getSerialNumber());
        byte[] msg = kangkaisiPacket.array();
        session.write(msg);
    }
}
