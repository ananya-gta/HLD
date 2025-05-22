package main.java.ratelimiteralgo.slidingWindowLogs;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowLogs {
    private final long windowSizeInMilliseconds;
    private  final int maxRequests;
    private final Queue<Long> queue;

    public SlidingWindowLogs(long windowSizeInMilliseconds, int maxRequests) {
        this.windowSizeInMilliseconds = windowSizeInMilliseconds;
        this.maxRequests = maxRequests;
        this.queue = new LinkedList<>();
    }

    public synchronized boolean allowRequests() {
        long now = Instant.now().toEpochMilli();

        // remove timestamps that are older than current window
        while (!queue.isEmpty() && (now - queue.peek()) > windowSizeInMilliseconds) queue.poll();

        if (queue.size() < maxRequests ) {
            queue.offer(now);
            return true;
        }
        return false;
    }
}
