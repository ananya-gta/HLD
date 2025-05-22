package main.java.ratelimiteralgo.fixedWindowCounter;

import java.time.Instant;

public class FixedWindowCounter {

    private final long limit;
    private Instant window;
    private long counter;

    public FixedWindowCounter(long limit) {
        this.limit = limit;
        this.window = Instant.now();
        this.counter = 0;
    }

    public synchronized boolean allowRequests(int windowSizeInSeconds) {
        Instant now = Instant.now();
        System.out.println("current window: " + window);
        if (now.minusSeconds(windowSizeInSeconds).isBefore(window)) { // are we still inside the current time window ?
            // if yes
            if (counter < limit) { // check if counter of the current window is less than the limit of the window,
                counter++; // also increase the counter
                return true; // then allow request
            } else {
                return false; // don't allow request if counter exceeds limit
            }
        } else { // if we are not inside the current 1-minute time window
            // start a new window
            window = now;
            counter = 1; // count the current request
            return true; // allow the current request
        }
    }
}
