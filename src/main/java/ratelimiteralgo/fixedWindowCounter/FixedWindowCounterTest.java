package main.java.ratelimiteralgo.fixedWindowCounter;

import java.time.Instant;

public class FixedWindowCounterTest {
    public static void main(String[] args) throws InterruptedException {
        FixedWindowCounter obj = new FixedWindowCounter(5); // Allow 5 requests per 60-second window

        for (int i = 1; i <= 20; i++) {
            boolean allowed = obj.allowRequests(10);
            System.out.println(Instant.now() + "Request " + i + ": " + (allowed ? "Allowed" : "Rejected"));
            Thread.sleep(1000); // Wait 1 second between requests to simulate real-time traffic
        }
    }
}
