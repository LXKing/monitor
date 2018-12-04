package mmp.gps.protocol.kangkaisi.body;


public class StateCourse {

    private int gpsRealTime;
    private int isGps;
    private int through;
    private int weft;
    private int course;


    public int getGpsRealTime() {
        return this.gpsRealTime;
    }

    public void setGpsRealTime(int gpsRealTime) {
        this.gpsRealTime = gpsRealTime;
    }

    public int getIsGps() {
        return this.isGps;
    }

    public void setIsGps(int isGps) {
        this.isGps = isGps;
    }

    public int getThrough() {
        return this.through;
    }

    public void setThrough(int through) {
        this.through = through;
    }

    public int getWeft() {
        return this.weft;
    }

    public void setWeft(int weft) {
        this.weft = weft;
    }

    public int getCourse() {
        return this.course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String toString() {
        return "StateCourse [gpsRealTime=" + this.gpsRealTime + ", isGps=" + this.isGps + ", through=" + this.through + ", weft=" + this.weft + ", course=" + this.course + "]";
    }

    public void from(int TerminalInformationContent) {
        String binaryStr = Integer.toBinaryString(TerminalInformationContent);
        switch (binaryStr.length()) {
            case 10:
                binaryStr = "000000" + binaryStr;
                break;
            case 11:
                binaryStr = "00000" + binaryStr;
                break;
            case 12:
                binaryStr = "0000" + binaryStr;
                break;
            case 13:
                binaryStr = "000" + binaryStr;
                break;
            case 14:
                binaryStr = "00" + binaryStr;
                break;
            case 15:
                binaryStr = "0" + binaryStr;
        }

        this.gpsRealTime = Integer.parseInt(String.valueOf(binaryStr.charAt(2)));
        this.isGps = Integer.parseInt(String.valueOf(binaryStr.charAt(3)));
        this.through = Integer.parseInt(String.valueOf(binaryStr.charAt(4)));
        this.weft = Integer.parseInt(String.valueOf(binaryStr.charAt(5)));
        this.course = Integer.parseInt(binaryStr.substring(7, 16), 2);
    }

    public int to() {
        String coursestr = Integer.toBinaryString(this.course);
        String a = "000" + this.gpsRealTime + this.isGps + this.through + this.weft + coursestr;
        int d = Integer.parseInt(a, 2);
        return d;
    }
}
