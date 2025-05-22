package main.java.ratelimiteralgo.leakyBucket;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

public class LeakyBucket {

    private final int capacity;              // Max number of requests the bucket can hold
    private final long leakIntervalMillis;   // How often a request is processed (leaked)
    private final Queue<Instant> bucket;        // Stores timestamps of requests
    private Instant lastLeakTimestamp;

    public LeakyBucket(int capacity, long leakRatePerSecond) {
        this.capacity = capacity;
        this.leakIntervalMillis = 1000 / leakRatePerSecond;;
        this.bucket = new LinkedList<>();
        this.lastLeakTimestamp = Instant.now();
    }

    public synchronized boolean allowRequests() {
        leak();

        if (bucket.size() < capacity) {
            bucket.add(Instant.now());
            return true; // Request accepted
        } else {
            return false; // Bucket full — request rejected
        }
    }

    public void leak() {
        Instant now = Instant.now();
        while (!bucket.isEmpty() && (now.toEpochMilli() - lastLeakTimestamp.toEpochMilli()) >= leakIntervalMillis)  {
            bucket.poll();
            lastLeakTimestamp = lastLeakTimestamp.plusMillis(leakIntervalMillis);

        }
    }

}
