package com.supertal.assignment.controller;

import com.supertal.assignment.model.Country;
import com.supertal.assignment.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 24/01/2022 - 12:19
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/{name}")
    public Country getByName(@PathVariable String name) throws Exception {
        return countryService.getByName(name);
    }
}
