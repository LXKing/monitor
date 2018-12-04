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

public class kangkaisiInstructBuilder2 implements IInstructBuilder {

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
                    instruct = this.SetDEFENSE(para, deviceSerialNumber);
                }
                break;
            case -2014929842:
                if (command.equals("MOVING")) {
                    instruct = this.SetMOVING(para, deviceSerialNumber);
                }
                break;
            case -1942098602:
                if (command.equals("PARAM#")) {
                    instruct = this.instructionQuery("PARAM#", deviceSerialNumber);
                }
                break;
            case -1881112364:
                if (command.equals("RESET#")) {
                    instruct = this.instructionQuery(para, deviceSerialNumber);
                }
                break;
            case -1854167660:
                if (command.equals("SCXSZ#")) {
                    instruct = this.instructionQuery("SCXSZ#", deviceSerialNumber);
                }
                break;
            case -1852636218:
                if (command.equals("SENALM")) {
                    instruct = this.SetSENALM(para, deviceSerialNumber);
                }
                break;
            case -1852618822:
                if (command.equals("SENSOR")) {
                    instruct = this.SetDEFENSE(para, deviceSerialNumber);
                }
                break;
            case -1735601092:
                if (command.equals("WHERE#")) {
                    instruct = this.instructionQuery("PARAM#", deviceSerialNumber);
                }
                break;
            case -1202426261:
                if (command.equals("VERSION#")) {
                    instruct = this.instructionQuery("VERSION#", deviceSerialNumber);
                }
                break;
            case -1179141519:
                if (command.equals("STATUS#")) {
                    instruct = this.instructionQuery("PARAM#", deviceSerialNumber);
                }
                break;
            case -401883125:
                if (command.equals("GPRSSET#")) {
                    instruct = this.instructionQuery("GPRSSET#", deviceSerialNumber);
                }
                break;
            case 2643:
                if (command.equals("SF")) {
                    instruct = this.SetSF(para, deviceSerialNumber);
                }
                break;
            case 68080:
                if (command.equals("DW#")) {
                    instruct = this.instructionQuery("DW#", deviceSerialNumber);
                }
                break;
            case 82295:
                if (command.equals("SOS")) {
                    instruct = this.SetSOS(para, deviceSerialNumber);
                }
                break;
            case 1509425:
                if (command.equals("123#")) {
                    instruct = this.instructionQuery("123#", deviceSerialNumber);
                }
                break;
            case 2060894:
                if (command.equals("CALL")) {
                    instruct = this.SetCALL(para, deviceSerialNumber);
                }
                break;
            case 2139882:
                if (command.equals("EURL")) {
                    instruct = this.SetEURL(para, deviceSerialNumber);
                }
                break;
            case 2329070:
                if (command.equals("LANG")) {
                    instruct = this.SetLANG(para, deviceSerialNumber);
                }
                break;
            case 2336762:
                if (command.equals("LINK")) {
                    instruct = this.SetLINK(para, deviceSerialNumber);
                }
                break;
            case 2613428:
                if (command.equals("URL#")) {
                    instruct = this.instructionQuery("URL#", deviceSerialNumber);
                }
                break;
            case 21919205:
                if (command.equals("ASETAPN#")) {
                    instruct = this.instructionQuery("ASETAPN#", deviceSerialNumber);
                }
                break;
            case 64208429:
                if (command.equals("CLEAR")) {
                    instruct = this.instructionQuery(para, deviceSerialNumber);
                }
                break;
            case 66779153:
                if (command.equals("FENCE")) {
                    instruct = this.SetFENCE(para, deviceSerialNumber);
                }
                break;
            case 72328036:
                if (command.equals("LEVEL")) {
                    instruct = this.SetLEVEL(para, deviceSerialNumber);
                }
                break;
            case 77859441:
                if (command.equals("RELAY")) {
                    instruct = this.SetRELAY(para, deviceSerialNumber);
                }
                break;
            case 78784971:
                if (command.equals("SENDS")) {
                    instruct = this.SetSENDS(para, deviceSerialNumber);
                }
                break;
            case 79104039:
                if (command.equals("SPEED")) {
                    instruct = this.SetSpeed(para, deviceSerialNumber);
                }
                break;
            case 79826725:
                if (command.equals("TIMER")) {
                    instruct = this.SetTIMER(para, deviceSerialNumber);
                }
                break;
            case 198745114:
                if (command.equals("POSITION#")) {
                    instruct = this.instructionQuery("POSITION#", deviceSerialNumber);
                }
                break;
            case 1071086581:
                if (command.equals("DISTANCE")) {
                    instruct = this.SetDISTANCE(para, deviceSerialNumber);
                }
                break;
            case 1294000249:
                if (command.equals("FACTORY#")) {
                    instruct = this.instructionQuery("FACTORY#", deviceSerialNumber);
                }
                break;
            case 1641186941:
                if (command.equals("POWERALM")) {
                    instruct = this.SetPOWERALM(para, deviceSerialNumber);
                }
                break;
            case 1924847740:
                if (command.equals("ACCREP")) {
                    instruct = this.SetACCREP(para, deviceSerialNumber);
                }
                break;
            case 1952120173:
                if (command.equals("BATALM")) {
                    instruct = this.SetBATALM(para, deviceSerialNumber);
                }
                break;
            case 1961684373:
                if (command.equals("MOVING#")) {
                    instruct = this.instructionQuery("MOVING#", deviceSerialNumber);
                }
                break;
            case 1984282709:
                if (command.equals("CENTER")) {
                    instruct = this.SetCENTER(para, deviceSerialNumber);
                }
                break;
            case 2070153778:
                if (command.equals("FENCE#")) {
                    instruct = this.instructionQuery("FENCE#", deviceSerialNumber);
                }
        }

        if (putvalidator) {
            validator.put(deviceNumber + deviceSerialNumber, new InstructInfo(platformSerialNumber, command));
        }

        return instruct;
    }

    private byte[] instructionQuery(String command, int deviceSerialNumber) {
        cnsle.debug("发送的指令 ： " + command);
        ServerSendOnlineInstructionBody serverReplyOnlineInstructionBody = new ServerSendOnlineInstructionBody();
        serverReplyOnlineInstructionBody.setServerFlag((long) deviceSerialNumber);
        serverReplyOnlineInstructionBody.setInstructionContent(command);
        serverReplyOnlineInstructionBody.setLanguage(1);
        KangkaisiPacket kangkaisiPacket = new KangkaisiPacket(serverReplyOnlineInstructionBody);
        kangkaisiPacket.setProtocolNumber((short) 128);
        kangkaisiPacket.setSerialNumber(JTT808Util.getSerialNumber());
        return kangkaisiPacket.array();
    }

    private byte[] SetSpeed(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("SPEED");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
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

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetEURL(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("EURL");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            item.get(mapKey);
                            command = "EURL#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object connect = settings.get("connect");
                            command = "EURL," + connect + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetLANG(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("LANG");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            item.get(mapKey);
                            command = "LANG#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object connect = settings.get("X");
                            command = "LANG," + connect + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetLINK(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("LINK");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            item.get(mapKey);
                            command = "LINK#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object N = settings.get("N");
                            command = "LINK," + N + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetSOS(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("SOS");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            item.get(mapKey);
                            command = "SOS#";
                        }
                        break;
                    case 65:
                        if (mapKey.equals("A")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object p1 = settings.get("p1");
                            Object p2 = settings.get("p2");
                            Object p3 = settings.get("p3");
                            command = "SOS,A," + p1 + "," + p2 + "," + p3 + "#";
                        }
                        break;
                    case 2157:
                        if (mapKey.equals("D1")) {
                            Object val3 = item.get(mapKey);
                            Map settings2 = (Map) val3;
                            Object s1 = settings2.get("s1");
                            Object s2 = settings2.get("s2");
                            Object s3 = settings2.get("s3");
                            command = "SOS,D," + s1 + "," + s2 + "," + s3 + "#";
                        }
                        break;
                    case 2158:
                        if (mapKey.equals("D2")) {
                            Object val4 = item.get(mapKey);
                            Map settings3 = (Map) val4;
                            Object p = settings3.get("p");
                            command = "SOS,D," + p + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetCENTER(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("CENTER");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "CENTER#";
                        }
                        break;
                    case 65:
                        if (mapKey.equals("A")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object p = settings.get("p");
                            command = "CENTER,A," + p + "#";
                        }
                        break;
                    case 68:
                        if (mapKey.equals("D")) {
                            command = "CENTER#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetTIMER(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("TIMER");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "TIMER#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object T1 = settings.get("T1");
                            Object T2 = settings.get("T2");
                            command = "TIMER," + T1 + "," + T2 + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetDISTANCE(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("DISTANCE");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "DISTANCE#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object D = settings.get("D");
                            command = "DISTANCE," + D + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetACCREP(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("ACCREP");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "ACCREP#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object A = settings.get("A");
                            command = "ACCREP," + A + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetDEFENSE(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("DEFENSE");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "DEFENSE#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object A = settings.get("A");
                            command = "DEFENSE," + A + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetSENSOR(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("SENSOR");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "SENSOR#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object A = settings.get("A");
                            Object B = settings.get("B");
                            Object C = settings.get("C");
                            command = "SENSOR," + A + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetSENDS(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("SENDS");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "SENDS#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object A = settings.get("A");
                            command = "SENDS," + A + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetSF(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("SF");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "SF#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object A = settings.get("A");
                            Object B = settings.get("B");
                            command = "SF," + A + "," + B + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetRELAY(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("RELAY");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "RELAY#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object A = settings.get("A");
                            command = "RELAY," + A + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetFENCE(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("FENCE");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -1498085729:
                        if (mapKey.equals("circular")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object B = settings.get("B");
                            Object D = settings.get("D");
                            Object E = settings.get("E");
                            Object F = settings.get("F");
                            Object X = settings.get("X");
                            Object M = settings.get("M");
                            command = "RELAY," + B + ",0," + D + "," + E + "," + F + "," + X + "," + M + "#";
                        }
                        break;
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "RELAY#";
                        }
                        break;
                    case -894674659:
                        if (mapKey.equals("square")) {
                            Object val3 = item.get(mapKey);
                            Map settings2 = (Map) val3;
                            Object B1 = settings2.get("B");
                            Object D1 = settings2.get("D");
                            Object E1 = settings2.get("E");
                            Object F1 = settings2.get("F");
                            Object G1 = settings2.get("G");
                            Object X1 = settings2.get("X");
                            Object M1 = settings2.get("M");
                            command = "RELAY," + B1 + ",1," + D1 + "," + E1 + "," + F1 + "," + G1 + "," + X1 + "," + M1 + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetSENALM(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("SENALM");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "SENALM#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object A = settings.get("A");
                            Object M = settings.get("M");
                            if (A.toString().equals("OFF")) {
                                command = "SENALM,OFF#";
                            } else {
                                command = "SENALM," + A + "," + M + "#";
                            }
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetPOWERALM(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("POWERALM");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "POWERALM#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object A = settings.get("A");
                            Object M = settings.get("M");
                            Object T1 = settings.get("T1");
                            Object T2 = settings.get("T2");
                            Object T3 = settings.get("T3");
                            if (A.toString().equals("OFF")) {
                                command = "POWERALM,OFF#";
                            } else {
                                command = "POWERALM," + A + "," + M + "," + T1 + "," + T2 + "," + T3 + "#";
                            }
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetBATALM(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("BATALM");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "BATALM#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object A = settings.get("A");
                            Object M = settings.get("M");
                            if (A.toString().equals("OFF")) {
                                command = "POWERALM,OFF#";
                            } else {
                                command = "BATALM," + A + "," + M + "#";
                            }
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetMOVING(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("MOVING");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "MOVING#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object A = settings.get("A");
                            Object R = settings.get("R");
                            Object M = settings.get("M");
                            if (A.toString().equals("OFF")) {
                                command = "MOVING,OFF#";
                            } else {
                                command = "MOVING," + A + "," + R + "," + M + "#";
                            }
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetCALL(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("CALL");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "CALL#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object N = settings.get("N");
                            command = "CALL," + N + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }

    private byte[] SetLEVEL(String para, int deviceSerialNumber) {
        Map map = (Map) JsonMapper.toObject(para, Map.class);
        List items = (List) map.get("LEVEL");
        String command = null;
        Iterator var7 = items.iterator();

        while (var7.hasNext()) {
            Map item = (Map) var7.next();
            Iterator var9 = item.keySet().iterator();

            while (var9.hasNext()) {
                String mapKey = (String) var9.next();
                switch (mapKey.hashCode()) {
                    case -906021636:
                        if (mapKey.equals("select")) {
                            command = "LEVEL#";
                        }
                        break;
                    case 113762:
                        if (mapKey.equals("set")) {
                            Object val2 = item.get(mapKey);
                            Map settings = (Map) val2;
                            Object A = settings.get("A");
                            command = "LEVEL," + A + "#";
                        }
                }
            }
        }

        return this.instructionQuery(command, deviceSerialNumber);
    }
}
