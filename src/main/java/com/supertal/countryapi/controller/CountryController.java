package com.supertal.countryapi.controller;

import com.supertal.countryapi.model.Country;
import com.supertal.countryapi.service.impl.CountryServiceImpl;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 24/01/2022 - 12:19
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/country")
public class CountryController {

    public Long rateLimitBandwidthsCapacity = 30l;

    public Long rateLimitTime = 1l;

    private final Bucket bucket;

    @Autowired
    private CountryServiceImpl countryService;

    public CountryController() {
        Bandwidth limit = Bandwidth.classic(rateLimitBandwidthsCapacity, Refill.greedy(rateLimitTime, Duration.ofMinutes(rateLimitTime)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<Country> getByName(@PathVariable String name) throws Exception {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok(countryService.getByName(name));
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
