package main.java.ratelimiteralgo;

public class TokenBucketTest {
    public static void main(String[] args) throws InterruptedException {
        TokenBucket bucket = new TokenBucket(5, 1.0); // 5 tokens max, refill 1 token/sec

        for (int i = 0; i < 10; i++) {
            boolean allowed = bucket.allowRequest(1);
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Rate Limited"));
            Thread.sleep(500); // simulate request interval
        }
    }
}
