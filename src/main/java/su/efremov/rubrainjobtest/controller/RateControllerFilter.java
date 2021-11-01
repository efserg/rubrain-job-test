package su.efremov.rubrainjobtest.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import su.efremov.rubrainjobtest.service.RequestCounterService;

@Component
@Order(1)
public class RateControllerFilter implements Filter {

    @Value("${requestCount:5}")
    private Long requestCount;

    private final RequestCounterService requestCounter;

    @Autowired
    public RateControllerFilter(RequestCounterService requestCounter) {
        this.requestCounter = requestCounter;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final long count = requestCounter.incrementAndGet();
        if (count > requestCount) {
            ((HttpServletResponse) response).sendError(HttpStatus.BAD_GATEWAY.value(),
                    "Too many request");
            return;
        }
        chain.doFilter(request, response);
    }
}
