package mmp.gps.gateway.codec.jtt808.Handler;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

class MediaFuture implements Runnable {

    private String key;


    public String getKey() {
        return this.key;
    }

    public MediaFuture(String key) {
        this.key = key;
    }

    public void run() {
        ConcurrentHashMap map = MultimediaUtil.getMultimediaMap();
        Multimedia media = (Multimedia) map.get(this.key);
        Date now = new Date();
        long time = (now.getTime() - media.getLastUploadTime().getTime()) / 1000L;
        if (!media.isCompleted() && (double) time <= (double) media.getPages() * 1.5D) {
            MultimediaUtil.reply(media);
        } else {
            media.getFuture().cancel(true);
            map.remove(this.key);
        }
    }
}
