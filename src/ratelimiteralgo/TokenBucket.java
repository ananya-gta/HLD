package ratelimiteralgo;

import java.time.Instant;

public class TokenBucket {

    private final long capacity;         // Max number of tokens the bucket can hold
    private final double fillRate;       // Tokens added per second
    private double tokens;               // Current token count (can be fractional)
    private Instant lastRefillTimestamp; // Last time the bucket was refilled

    public TokenBucket(long capacity, double fillRate) {
        this.capacity = capacity;
        this.fillRate = fillRate;
        this.tokens = capacity;
        this.lastRefillTimestamp = Instant.now();
    }

    /*
    * In java, synchronized is used to prevent multiple threads from accessing a critical section of code at the same time.
    * 🧠 Key Reasons for synchronized:
    *        1. Thread Safety
    *       - Imagine two threads call allowRequest() at the same time:
    *       - Both check this.tokens < 1
    *       - Both see tokens = 1 (enough!)
    *       - Both subtract 1 → tokens -= 1
    *       ➡️ Result: tokens = -1 ❌
    *       This violates the logic of the token bucket — it just allowed two requests when only one token was available.
    *        **synchronized** ensures that only one thread can enter the method at a time.
    *        2. Consistency During refill() and Token Check
    *        - You’re modifying shared state: this.tokens & this.lastRefillTimestamp
    *        Without synchronization, a thread could:
    *        - Read stale data.
    *        - Interleave operations midway with another thread.
    *        - Cause race conditions, inconsistent refills, or incorrect token deductions.
    * */
    public synchronized boolean allowRequest(int tokensToConsume) { // usually tokensToConsume = 1 per request
        refill();
        if (this.tokens < tokensToConsume) return false;
        this.tokens -= tokensToConsume;
        return true;
    }


    private void refill() {
        Instant now = Instant.now();
        // calculate "Elapsed time" = How long it’s been since the last token refill
        double tokenToAdd = (now.toEpochMilli() - lastRefillTimestamp.toEpochMilli()) * fillRate / 1000.0;
        this.tokens = Math.min(capacity, this.tokens + tokenToAdd);
        this.lastRefillTimestamp = now;
    }

}
