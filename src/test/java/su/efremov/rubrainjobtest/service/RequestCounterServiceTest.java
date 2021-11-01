package su.efremov.rubrainjobtest.service;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RequestCounterServiceTest {

    @Autowired
    private RequestCounterService service;

    @Test
    public void counterShouldIncrement() {
        final long counter = service.incrementAndGet();
        assertThat(counter, Matchers.equalTo(1L));
    }

    @Test
    public void counterShouldClear() {
        service.incrementAndGet();
        service.incrementAndGet();
        service.incrementAndGet();
        service.clear();
        final long counter = service.incrementAndGet();
        assertThat(counter, Matchers.equalTo(1L));
    }


}