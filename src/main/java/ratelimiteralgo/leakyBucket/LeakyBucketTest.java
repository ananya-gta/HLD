package main.java.ratelimiteralgo.leakyBucket;

public class LeakyBucketTest {
        public static void main(String[] args) throws InterruptedException {
            LeakyBucket limiter = new LeakyBucket(5, 1); // 1 request per second

            for (int i = 0; i < 10; i++) {
                if (limiter.allowRequests()) {
                    System.out.println("Request " + i + " allowed");
                } else {
                    System.out.println("Request " + i + " rejected");
                }
                Thread.sleep(200); // simulate time between requests
            }
        }


}
