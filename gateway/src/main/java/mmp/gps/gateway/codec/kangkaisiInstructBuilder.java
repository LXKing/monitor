package mmp.gps.gateway.codec;

import mmp.gps.gateway.Common;
import mmp.gps.gateway.contract.IInstructBuilder;
import mmp.gps.gateway.domain.InstructInfo;
import mmp.gps.gateway.domain.ReplyValidator;
import mmp.gps.common.util.JsonMapper;
import mmp.gps.protocol.jtt808.JTT808Util;
import mmp.gps.protocol.kangkaisi.KangkaisiPacket;
import mmp.gps.protocol.kangkaisi.body.ServerSendOnlineInstructionBody;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class kangkaisiInstructBuilder implements IInstructBuilder {

    private static Logger cnsle = Logger.getLogger("cnsle");


    public int getProtocolKind() {
        return 2;
    }

    public String getName() {
        return "康凯斯指令生成器";
    }

    public byte[] build(String platformSerialNumber, String deviceNumber, String command, String para, ReplyValidator validator) {
        int deviceSerialNumber = Common.getSerialNumber();
        byte[] instruct = null;
        boolean putvalidator = true;
        switch (command.hashCode()) {
            case -2032068064:
                if (command.equals("DEFENSE")) {
                    instruct = this.instructionQuery("DEFENSE");
                }
                break;
            case -2014929842:
                if (command.equals("MOVING")) {
                    instruct = this.instructionQuery("MOVING");
                }
                break;
            case -1942098602:
                if (command.equals("PARAM#")) {
                    instruct = this.instructionQuery("PARAM#");
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
                break;
            case -1881112364:
                if (command.equals("RESET#")) {
                    instruct = this.instructionQuery("RESET#");
                }
                break;
            case -1854167660:
                if (command.equals("SCXSZ#")) {
                    instruct = this.instructionQuery("SCXSZ#");
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
                break;
            case -1852636218:
                if (command.equals("SENALM")) {
                    instruct = this.instructionQuery("SENALM");
                }
                break;
            case -1852618822:
                if (command.equals("SENSOR")) {
                    instruct = this.instructionQuery("SENSOR");
                }
                break;
            case -1735601092:
                if (command.equals("WHERE#")) {
                    instruct = this.instructionQuery("PARAM#");
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
                break;
            case -1202426261:
                if (command.equals("VERSION#")) {
                    instruct = this.instructionQuery("VERSION#");
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
                break;
            case -1179141519:
                if (command.equals("STATUS#")) {
                    instruct = this.instructionQuery("PARAM#");
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
                break;
            case -401883125:
                if (command.equals("GPRSSET#")) {
                    instruct = this.instructionQuery("GPRSSET#");
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
                break;
            case 2643:
                if (command.equals("SF")) {
                    instruct = this.instructionQuery("SF");
                }
                break;
            case 68080:
                if (command.equals("DW#")) {
                    instruct = this.instructionQuery("DW#");
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
                break;
            case 82295:
                if (command.equals("SOS")) {
                    instruct = this.instructionQuery("SOS");
                }
                break;
            case 1509425:
                if (command.equals("123#")) {
                    instruct = this.instructionQuery("123#");
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
                break;
            case 2060894:
                if (command.equals("CALL")) {
                    instruct = this.instructionQuery("CALL");
                }
                break;
            case 2139882:
                if (command.equals("EURL")) {
                    instruct = this.SetEURL(para);
                }
                break;
            case 2329070:
                if (command.equals("LANG")) {
                    instruct = this.SetLANG("LANG");
                }
                break;
            case 2336762:
                if (command.equals("LINK")) {
                    instruct = this.instructionQuery("LINK");
                }
                break;
            case 2613428:
                if (command.equals("URL#")) {
                    instruct = this.instructionQuery("URL#");
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
                break;
            case 21919205:
                if (command.equals("ASETAPN#")) {
                    instruct = this.instructionQuery("ASETAPN#");
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
                break;
            case 64208429:
                if (command.equals("CLEAR")) {
                    instruct = this.instructionQuery("CLEAR");
                }
                break;
            case 66779153:
                if (command.equals("FENCE")) {
                    instruct = this.instructionQuery("FENCE");
                }
                break;
            case 72328036:
                if (command.equals("LEVEL")) {
                    instruct = this.instructionQuery("LEVEL");
                }
                break;
            case 77859441:
                if (command.equals("RELAY")) {
                    instruct = this.instructionQuery("RELAY");
                }
                break;
            case 78784971:
                if (command.equals("SENDS")) {
                    instruct = this.instructionQuery("SENDS");
                }
                break;
            case 79104039:
                if (command.equals("SPEED")) {
                    instruct = this.SetSpeed(para);
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
                break;
            case 79826725:
                if (command.equals("TIMER")) {
                    instruct = this.SetGpsTIMER("TIMER");
                }
                break;
            case 198745114:
                if (command.equals("POSITION#")) {
                    instruct = this.instructionQuery("POSITION#");
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
                break;
            case 1071086581:
                if (command.equals("DISTANCE")) {
                    instruct = this.instructionQuery("DISTANCE");
                }
                break;
            case 1294000249:
                if (command.equals("FACTORY#")) {
                    instruct = this.instructionQuery("FACTORY#");
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
                break;
            case 1641186941:
                if (command.equals("POWERALM")) {
                    instruct = this.instructionQuery("POWERALM");
                }
                break;
            case 1924847740:
                if (command.equals("ACCREP")) {
                    instruct = this.instructionQuery("ACCREP");
                }
                break;
            case 1952120173:
                if (command.equals("BATALM")) {
                    instruct = this.instructionQuery("BATALM");
                }
                break;
            case 1961684373:
                if (command.equals("MOVING#")) {
                    instruct = this.instructionQuery("MOVING#");
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
                break;
            case 1984282709:
                if (command.equals("CENTER")) {
                    instruct = this.instructionQuery("CENTER");
                }
                break;
            case 2070153778:
                if (command.equals("FENCE#")) {
                    instruct = this.instructionQuery("FENCE#");
                    validator.put(deviceNumber + 263, new InstructInfo(platformSerialNumber, command));
                }
        }

        if (putvalidator) {
            cnsle.debug("deviceNumber + deviceSerialNumber ： " + deviceNumber + deviceSerialNumber);
            validator.put(deviceNumber + deviceSerialNumber, new InstructInfo(platformSerialNumber, command));
        }

        return instruct;
    }

    private byte[] instructionQuery(String command) {
        cnsle.debug("发送的指令 ： " + command);
        ServerSendOnlineInstructionBody serverReplyOnlineInstructionBody = new ServerSendOnlineInstructionBody();
        serverReplyOnlineInstructionBody.setServerFlag(1L);
        serverReplyOnlineInstructionBody.setInstructionContent(command);
        serverReplyOnlineInstructionBody.setLanguage(1);
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket(serverReplyOnlineInstructionBody);
        kangkaisiPacket.setProtocolNumber((short) 128);
        kangkaisiPacket.setSerialNumber(JTT808Util.getSerialNumber());
        return kangkaisiPacket.array();
    }

    private byte[] SetEURL(String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        String EURL = map.get("EURL").toString();
        String command = "EURL," + EURL + "#";
        return this.instructionQuery(command);
    }

    private byte[] SetLANG(String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        String X = map.get("X").toString();
        String command = "LANG," + X + "#";
        return this.instructionQuery(command);
    }

    private byte[] SetGpsTIMER(String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        String T = map.get("#").toString();
        String command;
        if (T.equals("1")) {
            command = "TIMER#";
        } else {
            String T1 = map.get("T1").toString();
            String T2 = map.get("T2").toString();
            command = "TIMER,<" + T1 + ">[," + T2 + "]#";
        }

        return this.instructionQuery(command);
    }

    private byte[] SetSpeed(String para) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("SPEED");
        String command = null;
        Iterator var6 = items.iterator();

        while (var6.hasNext()) {
            Map item = (Map) var6.next();
            Iterator var8 = item.keySet().iterator();

            while (var8.hasNext()) {
                String mapKey = (String) var8.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            item.get(mapKey);
                            command = "SPEED#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object A = settings.get("A");
                            Object B = settings.get("B");
                            Object C = settings.get("C");
                            Object M = settings.get("M");
                            if (A.toString().equals("OFF")) {
                                command = "SPEED,OFF#";
                            } else {
                                command = "SPEED," + A + "," + B + "," + C + "," + M + "#";
                            }
                        }
                }
            }
        }

        return this.instructionQuery(command);
    }
}
