package main.java.ratelimiteralgo.slidingWindowCounter;

public class SlidingWindowCounterTest {
    public static void main(String[] args) throws InterruptedException {
        SlidingWindowCounter limiter = new SlidingWindowCounter(3, 1000); // 3 requests per second

        for (int i = 1; i <= 6; i++) {
            boolean allowed = limiter.allowRequest();
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Restricted"));
            Thread.sleep(200); // 200 ms between each request
        }
    }
}
