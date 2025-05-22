package main.java.ratelimiteralgo.slidingWindowLogs;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowLogsTest {

    public static void main(String[] args) throws InterruptedException {
        SlidingWindowLogs limiter = new SlidingWindowLogs(10, 5); // 5 requests per 10 seconds

        for (int i = 1; i <= 10; i++) {
            boolean allowed = limiter.allowRequests();
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Rejected"));
            Thread.sleep(1000); // simulate 1 second between requests
        }
    }
}
