package main.java.ratelimiteralgo.slidingWindowCounter;

import java.time.Instant;

public class SlidingWindowCounter {

    private final int maxRequests;         // Max requests allowed per window
    private final long windowSizeMillis;  // Window duration in milliseconds
    private long windowStart;             // Start time of current window
    private int requestCount;             // Count of requests in current window

    public SlidingWindowCounter(int maxRequests, long windowSizeMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
        this.windowStart = Instant.now().toEpochMilli();
        this.requestCount = 0;
    }

    public synchronized boolean allowRequest() {
        long now = Instant.now().toEpochMilli();

        // Check if we're still in the same window
        if (now - windowStart < windowSizeMillis) {
            if (requestCount < maxRequests) {
                requestCount++;
                return true;
            } else {
                return false;
            }
        } else {
            // Reset the window
            windowStart = now;
            requestCount = 1;
            return true;
        }
    }
}
