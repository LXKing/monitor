package mmp.gps.gateway.domain;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class ReplyValidator {

    private ConcurrentHashMap serialNumbers = new ConcurrentHashMap();


    public void put(Object key, InstructInfo value) {
        this.serialNumbers.put(key, value);
    }

    public InstructInfo get(Object key) {
        return (InstructInfo) this.serialNumbers.get(key);
    }

    public void Enqueue(Object key, InstructInfo value) throws InterruptedException {
        Object queue = this.serialNumbers.get(key);
        if (queue == null) {
            queue = new LinkedBlockingQueue();
            this.serialNumbers.put(key, queue);
        }

        ((LinkedBlockingQueue) queue).put(value);
    }

    public InstructInfo Dequeue(Object key) {
        Object queue = this.serialNumbers.get(key);
        if (queue == null) {
            LinkedBlockingQueue queue1 = new LinkedBlockingQueue();
            this.serialNumbers.put(key, queue1);
            return null;
        } else {
            return (InstructInfo) ((LinkedBlockingQueue) queue).poll();
        }
    }

    public void remove(Object key) {
        this.serialNumbers.remove(key);
    }
}
