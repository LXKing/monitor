package mmp.gps.monitor.area;


import mmp.gps.domain.locate.Latest;

public abstract class AreaCatcher {
    public abstract boolean hasAlarm(Latest latest);
}
