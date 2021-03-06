package com.supertal.countryapi.service;

import com.supertal.countryapi.model.Country;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 24/01/2022 - 12:20
 */
public interface CountryService {
    Country getByName(String name) throws Exception;
}
