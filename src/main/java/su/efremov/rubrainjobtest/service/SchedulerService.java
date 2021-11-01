package su.efremov.rubrainjobtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    private final RequestCounterService requestCounter;

    @Autowired
    public SchedulerService(final RequestCounterService requestCounter) {
        this.requestCounter = requestCounter;
    }

    @Scheduled(fixedDelayString = "${timeDurationMillis}")
    public void clear() {
        requestCounter.clear();
    }

}

