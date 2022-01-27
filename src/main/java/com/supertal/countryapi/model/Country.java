package com.supertal.countryapi.model;

import com.supertal.countryapi.model.fixer.FixerCurrencyResponse;
import com.supertal.countryapi.model.restcountries.RestCurrencyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 24/01/2022 - 12:23
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Country {
    private String name;
    private Integer population;
    private Map<String, RestCurrencyResponse> currency;
    private FixerCurrencyResponse currencyRate;
}
