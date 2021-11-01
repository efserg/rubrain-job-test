package su.efremov.rubrainjobtest.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class RequestCounterService {

    private final AtomicLong counter;

    public RequestCounterService() {
        this.counter = new AtomicLong();
    }

    public long incrementAndGet() {
        return counter.incrementAndGet();
    }

    public void clear() {
        counter.set(0);
    }
}
