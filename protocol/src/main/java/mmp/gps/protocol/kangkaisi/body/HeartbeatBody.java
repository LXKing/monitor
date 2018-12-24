package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.ByteIO;
import mmp.gps.protocol.kangkaisi.IPacket;


public class HeartbeatBody implements IPacket {
    private short terminalInformationContent;
    private short voltageLevel;
    private short gsm;
    private int language;

    public short getTerminalInformationContent() {
        return this.terminalInformationContent;
    }

    public void setTerminalInformationContent(short terminalInformationContent) {
        terminalInformationContent = terminalInformationContent;
    }

    public short getVoltageLevel() {
        return this.voltageLevel;
    }

    public void setVoltageLevel(short voltageLevel) {
        this.voltageLevel = voltageLevel;
    }

    public short getGSM() {
        return this.gsm;
    }

    public void setGSM(short gsm) {
        this.gsm = gsm;
    }

    public int getLanguage() {
        return this.language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public static class TerminalInformationc {
        private int electric;
        private int gps;
        private int charge;
        private int acc;
        private int isFortification;

        public int getElectric() {
            return this.electric;
        }

        public void setElectric(int electric) {
            this.electric = electric;
        }

        public int getGps() {
            return this.gps;
        }

        public void setGps(int gps) {
            this.gps = gps;
        }

        public int getCharge() {
            return this.charge;
        }

        public void setCharge(int charge) {
            this.charge = charge;
        }

        public int getAcc() {
            return this.acc;
        }

        public void setAcc(int acc) {
            this.acc = acc;
        }

        public int getIsFortification() {
            return this.isFortification;
        }

        public void setIsFortification(int isFortification) {
            this.isFortification = isFortification;
        }

        public String toString() {
            return
                    "TerminalInformationc [electric=" + this.electric + ", gps=" + this.gps + ", charge=" + this.charge + ", acc=" + this.acc + ", isFortification=" + this.isFortification + "]";
        }

        public void from(short TerminalInformationContent) {
            String binaryStr = Integer.toBinaryString(TerminalInformationContent);
            System.out.println("binaryStr" + binaryStr);

            this.electric = Integer.valueOf(binaryStr.charAt(0));
            this.gps = Integer.valueOf(binaryStr.charAt(1));
            this.charge = Integer.valueOf(binaryStr.charAt(5));
            this.acc = Integer.valueOf(binaryStr.charAt(6));
            this.isFortification = Integer.valueOf(binaryStr.charAt(7));
        }

        public int to() {
            String a = this.electric + this.gps + "000" + this.charge + this.acc + this.isFortification;
            int d = Integer.parseInt(a, 2);
            return d;
        }
    }

    public String toString() {
        return
                "HeartbeatBody [TerminalInformationContent=" + this.terminalInformationContent + ", VoltageLevel=" + this.voltageLevel + ", GSM=" + this.gsm + ", language=" + this.language + "]";
    }

    public int size() {
        return 5;
    }

    public void from(byte[] src) {
        ByteIO io = new ByteIO(src);
        this.terminalInformationContent = io.getUbyte();
        this.voltageLevel = io.getUbyte();
        this.gsm = io.getUbyte();
        this.language = io.getUshort();
    }

    public byte[] array() {
        ByteIO io = new ByteIO(size());
        io.putUbyte(this.terminalInformationContent);
        io.putUbyte(this.voltageLevel);
        io.putUbyte(this.gsm);
        io.putUshort(this.language);
        return io.array();
    }
}
