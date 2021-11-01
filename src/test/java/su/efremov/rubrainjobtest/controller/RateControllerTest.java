package su.efremov.rubrainjobtest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import su.efremov.rubrainjobtest.service.RequestCounterService;

@SpringBootTest
@AutoConfigureMockMvc
class RateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RequestCounterService counterService;

    @Value("${requestCount}")
    private long requestCount;

    @BeforeEach
    public void clearCounter() {
        counterService.clear();
    }

    @Test
    public void proceedShouldReturnOkStatusIfRateInLimit() throws Exception {
        for (long i = 0; i < requestCount; i++) {
            this.mockMvc.perform(get("/proceed")).andExpect(status().isOk());
        }
    }

    @Test
    public void proceedShouldReturnBadGatewayIfRateExceedLimit() throws Exception {
        for (long i = 0; i < requestCount; i++) {
            this.mockMvc.perform(get("/proceed")).andExpect(status().isOk());
        }
        this.mockMvc.perform(get("/proceed")).andExpect(status().isBadGateway());
    }
}