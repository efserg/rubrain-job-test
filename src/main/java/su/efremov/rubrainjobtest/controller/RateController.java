package su.efremov.rubrainjobtest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateController {

    @GetMapping("proceed")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().build();
    }


}
