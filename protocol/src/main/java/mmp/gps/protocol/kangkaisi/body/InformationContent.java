package mmp.gps.protocol.kangkaisi.body;

import mmp.gps.protocol.kangkaisi.definition.ALM1Definition;
import mmp.gps.protocol.kangkaisi.definition.ALM2Definition;
import mmp.gps.protocol.kangkaisi.definition.ALM3Definition;
import mmp.gps.protocol.kangkaisi.definition.ALM4Definition;
import mmp.gps.protocol.kangkaisi.definition.OilBreakingElectricityDefinition;
import mmp.gps.protocol.kangkaisi.definition.STA1Definition;

public class InformationContent {

    private String InformationContent;
    private String ALM1;
    private String ALM2;
    private String ALM3;
    private String ALM4;
    private String STA1;
    private String SOS;
    private String CENTER;
    private String FENCE;
    private String DYD;
    private String MODE;
    private ALM1Definition aLM1Definition;
    private ALM2Definition aLM2Definition;
    private ALM3Definition aLM3Definition;
    private ALM4Definition aLM4Definition;
    private STA1Definition sTA1Definition;
    private OilBreakingElectricityDefinition oilBreakingElectricityDefinition;


    public InformationContent() {
    }

    public InformationContent(String InformationContent) {
        this.InformationContent = InformationContent;
    }

    public String getInformationContent() {
        return this.InformationContent;
    }

    public void setInformationContent(String informationContent) {
        this.InformationContent = informationContent;
    }

    public String getALM1() {
        return this.ALM1;
    }

    public void setALM1(String aLM1) {
        this.ALM1 = aLM1;
    }

    public String getALM2() {
        return this.ALM2;
    }

    public void setALM2(String aLM2) {
        this.ALM2 = aLM2;
    }

    public String getALM3() {
        return this.ALM3;
    }

    public void setALM3(String aLM3) {
        this.ALM3 = aLM3;
    }

    public String getALM4() {
        return this.ALM4;
    }

    public void setALM4(String aLM4) {
        this.ALM4 = aLM4;
    }

    public String getSTA1() {
        return this.STA1;
    }

    public void setSTA1(String sTA1) {
        this.STA1 = sTA1;
    }

    public String getSOS() {
        return this.SOS;
    }

    public void setSOS(String sOS) {
        this.SOS = sOS;
    }

    public String getCENTER() {
        return this.CENTER;
    }

    public void setCENTER(String cENTER) {
        this.CENTER = cENTER;
    }

    public String getFENCE() {
        return this.FENCE;
    }

    public void setFENCE(String fENCE) {
        this.FENCE = fENCE;
    }

    public String getDYD() {
        return this.DYD;
    }

    public void setDYD(String dYD) {
        this.DYD = dYD;
    }

    public String getMODE() {
        return this.MODE;
    }

    public void setMODE(String mODE) {
        this.MODE = mODE;
    }

    public ALM1Definition getaLM1Definition() {
        return this.aLM1Definition;
    }

    public void setaLM1Definition(ALM1Definition aLM1Definition) {
        this.aLM1Definition = aLM1Definition;
    }

    public ALM2Definition getaLM2Definition() {
        return this.aLM2Definition;
    }

    public void setaLM2Definition(ALM2Definition aLM2Definition) {
        this.aLM2Definition = aLM2Definition;
    }

    public ALM3Definition getaLM3Definition() {
        return this.aLM3Definition;
    }

    public void setaLM3Definition(ALM3Definition aLM3Definition) {
        this.aLM3Definition = aLM3Definition;
    }

    public ALM4Definition getaLM4Definition() {
        return this.aLM4Definition;
    }

    public void setaLM4Definition(ALM4Definition aLM4Definition) {
        this.aLM4Definition = aLM4Definition;
    }

    public STA1Definition getsTA1Definition() {
        return this.sTA1Definition;
    }

    public void setsTA1Definition(STA1Definition sTA1Definition) {
        this.sTA1Definition = sTA1Definition;
    }

    public OilBreakingElectricityDefinition getOilBreakingElectricityDefinition() {
        return this.oilBreakingElectricityDefinition;
    }

    public void setOilBreakingElectricityDefinition(OilBreakingElectricityDefinition oilBreakingElectricityDefinition) {
        this.oilBreakingElectricityDefinition = oilBreakingElectricityDefinition;
    }

    public String toString() {
        return "InformationContent [InformationContent=" + this.InformationContent + ", ALM1=" + this.ALM1 + ", ALM2=" + this.ALM2 + ", ALM3=" + this.ALM3 + ", ALM4=" + this.ALM4 + ", STA1=" + this.STA1 + ", SOS=" + this.SOS + ", CENTER=" + this.CENTER + ", FENCE=" + this.FENCE + ", DYD=" + this.DYD + ", MODE=" + this.MODE + "]";
    }

    public void from() {
        String[] s = this.InformationContent.split(";");
        String[] var5 = s;
        int var4 = s.length;

        for (int var3 = 0; var3 < var4; ++var3) {
            String str = var5[var3];
            String[] s2 = str.split("==");
            String var7;
            switch ((var7 = s2[0]).hashCode()) {
                case 68175:
                    if (var7.equals("DYD")) {
                        this.DYD = s2[1];
                        this.oilBreakingElectricityDefinition = new OilBreakingElectricityDefinition();
                        this.oilBreakingElectricityDefinition.from(Integer.parseInt(this.DYD));
                    }
                    break;
                case 82295:
                    if (var7.equals("SOS")) {
                        this.SOS = s2[1];
                    }
                    break;
                case 2011887:
                    if (var7.equals("ALM1")) {
                        this.ALM1 = s2[1];
                        this.aLM1Definition = new ALM1Definition();
                        this.aLM1Definition.from(Integer.parseInt(this.ALM1));
                    }
                    break;
                case 2372003:
                    if (var7.equals("MODE")) {
                        this.MODE = s2[1];
                    }
                    break;
                case 31564560:
                    if (var7.equals(" ALM2")) {
                        this.ALM2 = s2[1];
                        this.aLM2Definition = new ALM2Definition();
                        this.aLM2Definition.from(Integer.parseInt(this.ALM2));
                    }
                    break;
                case 31564561:
                    if (var7.equals(" ALM3")) {
                        this.ALM3 = s2[1];
                        this.aLM3Definition = new ALM3Definition();
                        this.aLM3Definition.from(Integer.parseInt(this.ALM3));
                    }
                    break;
                case 31564562:
                    if (var7.equals(" ALM4")) {
                        this.ALM4 = s2[1];
                        this.aLM4Definition = new ALM4Definition();
                        this.aLM4Definition.from(Integer.parseInt(this.ALM4));
                    }
                    break;
                case 32108113:
                    if (var7.equals(" STA1")) {
                        this.STA1 = s2[1];
                        this.sTA1Definition = new STA1Definition();
                        this.sTA1Definition.from(Integer.parseInt(this.STA1));
                    }
                    break;
                case 66779153:
                    if (var7.equals("FENCE")) {
                        this.FENCE = s2[1];
                    }
                    break;
                case 1984282709:
                    if (var7.equals("CENTER")) {
                        this.CENTER = s2[1];
                    }
            }
        }

    }

    public String to() {
        String s = "";
        if (this.ALM1 != null) {
            s = s + "ALM1=" + this.ALM1 + ";";
        }

        if (this.ALM2 != null) {
            s = s + "ALM2=" + this.ALM2 + ";";
        }

        if (this.ALM3 != null) {
            s = s + "ALM3=" + this.ALM3 + ";";
        }

        if (this.ALM4 != null) {
            s = s + "ALM4=" + this.ALM4 + ";";
        }

        if (this.STA1 != null) {
            s = s + "STA1=" + this.STA1 + ";";
        }

        if (this.SOS != null) {
            s = s + "SOS=" + this.SOS + ";";
        }

        if (this.CENTER != null) {
            s = s + "CENTER=" + this.CENTER + ";";
        }

        if (this.FENCE != null) {
            s = s + "FENCE=" + this.FENCE + ";";
        }

        if (this.DYD != null) {
            s = s + "DYD=" + this.DYD + ";";
        }

        if (this.MODE != null) {
            s = s + "MODE" + this.MODE + ";";
        }

        return s;
    }
}
