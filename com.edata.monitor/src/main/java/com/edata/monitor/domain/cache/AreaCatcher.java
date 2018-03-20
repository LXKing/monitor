package com.edata.monitor.domain.cache;

import com.edata.monitor.domain.locate.Latest;

public abstract class AreaCatcher {
	public abstract boolean hasAlarm(Latest latest);
}
