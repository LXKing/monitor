package mmp.gps.logic.push;

import mmp.gps.domain.fault.FaultDto;
import mmp.gps.domain.flow.FlowDto;
import mmp.gps.domain.gateway.BulkDataDto;
import mmp.gps.domain.gateway.InstructResultDto;
import mmp.gps.domain.track.TrackDto;
import mmp.gps.logic.service.DeviceService;
import mmp.gps.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.Map.Entry;

@Component
public class Pushers {

    private static DeviceService deviceService;
    private static UserService userService;
    private static Pushers current = null;
    private HashMap urls = new HashMap();
    private HashMap pushers = new HashMap();


    public static Pushers getCurrent() {
        return current;
    }

    @Autowired(
            required = true
    )
    public Pushers(@Qualifier("deviceService") DeviceService deviceService, @Qualifier("userService") UserService userService) {
        deviceService = deviceService;
        userService = userService;
        current = this;
        this.reload();
    }

    public synchronized void push(BulkDataDto bulk) {
        HashMap senders = new HashMap();
        Iterator var3;
        List pl;
        Iterator var6;
        Pusher p;
        if (bulk.tracks != null) {
            var3 = bulk.tracks.iterator();

            while (var3.hasNext()) {
                TrackDto pusher = (TrackDto) var3.next();
                pl = (List) this.pushers.get(pusher.dn);
                if (pl != null) {
                    var6 = pl.iterator();

                    while (var6.hasNext()) {
                        p = (Pusher) var6.next();
                        p.getBlock().add(pusher);
                        senders.put(pusher.dn, p);
                    }
                }
            }
        }

        if (bulk.flows != null) {
            var3 = bulk.flows.iterator();

            while (var3.hasNext()) {
                FlowDto pusher1 = (FlowDto) var3.next();
                pl = (List) this.pushers.get(pusher1.number);
                if (pl != null) {
                    var6 = pl.iterator();

                    while (var6.hasNext()) {
                        p = (Pusher) var6.next();
                        p.getBlock().add(pusher1);
                        senders.put(pusher1.number, p);
                    }
                }
            }
        }

        if (bulk.faults != null) {
            var3 = bulk.faults.iterator();

            while (var3.hasNext()) {
                FaultDto pusher2 = (FaultDto) var3.next();
                pl = (List) this.pushers.get(pusher2.number);
                if (pl != null) {
                    var6 = pl.iterator();

                    while (var6.hasNext()) {
                        p = (Pusher) var6.next();
                        p.getBlock().add(pusher2);
                        senders.put(pusher2.number, p);
                    }
                }
            }
        }

        if (bulk.instructResults != null) {
            var3 = bulk.instructResults.iterator();

            while (var3.hasNext()) {
                InstructResultDto pusher3 = (InstructResultDto) var3.next();
                pl = (List) this.pushers.get(pusher3.number);
                if (pl != null) {
                    var6 = pl.iterator();

                    while (var6.hasNext()) {
                        p = (Pusher) var6.next();
                        p.getBlock().add(pusher3);
                        senders.put(pusher3.number, p);
                    }
                }
            }
        }

        var3 = senders.values().iterator();

        while (var3.hasNext()) {
            Pusher pusher4 = (Pusher) var3.next();
            pusher4.pushRealtimeDataBlock();
        }

    }

    public synchronized void push(String number, String kind, Object msg) {
        List pl = (List) this.pushers.get(number);
        if (pl != null) {
            Iterator var5 = pl.iterator();

            while (var5.hasNext()) {
                Pusher p = (Pusher) var5.next();
                p.push(kind, msg);
            }

        }
    }

    public void batch(String number, Object msg, Map batch) {
        if (batch != null) {
            List pl = (List) this.pushers.get(number);
            if (pl != null) {
                Iterator var5 = pl.iterator();

                while (var5.hasNext()) {
                    Pusher p = (Pusher) var5.next();
                    List list = (List) batch.getOrDefault(p, new ArrayList());
                    list.add(msg);
                    batch.put(p, list);
                }

            }
        }
    }

    public void push(String kind, Map batch) {
        if (batch != null) {
            Iterator var3 = batch.entrySet().iterator();

            while (var3.hasNext()) {
                Entry entry = (Entry) var3.next();
                ((Pusher) entry.getKey()).push(kind, entry.getValue());
            }

        }
    }

    public synchronized void reload() {
        try {
            this.urls.clear();
            HashMap e = userService.getPushUrls();
            Iterator deviceInUsers = e.entrySet().iterator();

            while (deviceInUsers.hasNext()) {
                Entry entry = (Entry) deviceInUsers.next();
                this.urls.put(entry.getKey(), new Pusher((String) entry.getValue(), (String) entry.getKey()));
            }

            Map deviceInUsers1 = deviceService.getDeviceInUsers();
            Iterator entry2 = deviceInUsers1.entrySet().iterator();

            while (entry2.hasNext()) {
                Entry entry1 = (Entry) entry2.next();
                List users = (List) entry1.getValue();
                Iterator var6 = users.iterator();

                while (var6.hasNext()) {
                    String user = (String) var6.next();
                    Pusher pusher = (Pusher) this.urls.get(user);
                    if (pusher != null) {
                        Object list = (List) this.pushers.get(entry1.getKey());
                        if (list == null) {
                            list = new ArrayList(3);
                            this.pushers.put(entry1.getKey(), list);
                        }

                        ((List) list).add(pusher);
                    }
                }
            }
        } catch (Exception var10) {
            var10.printStackTrace();
        }

    }

    public synchronized void bindDeviceInUser(String userId, String number) {
        Pusher p = (Pusher) this.urls.get(userId);
        if (p != null) {
            Object pl = (List) this.pushers.get(number);
            if (pl == null) {
                pl = new ArrayList(2);
                this.pushers.put(number, pl);
            }

            if (!((List) pl).contains(p)) {
                ((List) pl).add(p);
            }

        }
    }

    public synchronized void unbindDeviceInUser(String userId, String number) {
        this.pushers.remove(number);
    }

    public synchronized void addPusher(String userId, String pushUrl) {
        if (pushUrl != null && pushUrl.length() > 0) {
            Pusher pusher = (Pusher) this.urls.get(userId);
            if (pusher == null) {
                pusher = new Pusher(pushUrl, userId);
                this.urls.put(userId, pusher);
            }

            pusher.setUrl(pushUrl);
        }
    }

    public synchronized void updatePusher(String userId, String pushUrl) {
        Pusher pusher = (Pusher) this.urls.get(userId);
        if (pusher != null) {
            if (pushUrl != null && pushUrl.length() > 0) {
                pusher.setUrl(pushUrl);
            } else {
                ArrayList keys = new ArrayList();
                Iterator var5 = this.pushers.entrySet().iterator();

                while (var5.hasNext()) {
                    Entry key = (Entry) var5.next();
                    List pl = (List) key.getValue();
                    Iterator var8 = pl.iterator();

                    while (var8.hasNext()) {
                        Pusher p = (Pusher) var8.next();
                        if (p.getUserId() == userId) {
                            keys.add(key.getKey());
                        }
                    }
                }

                var5 = keys.iterator();

                while (var5.hasNext()) {
                    String key1 = (String) var5.next();
                    this.pushers.remove(key1);
                }

                this.urls.remove(userId);
            }
        }
    }

}
