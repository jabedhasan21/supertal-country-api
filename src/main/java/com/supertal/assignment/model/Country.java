package com.supertal.assignment.model;

import com.supertal.assignment.model.fixer.FixerCurrencyResponse;
import com.supertal.assignment.model.restcountries.RestCurrencyResponse;
import lombok.AllArgsConstructor;
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
public class Country {
    private String name;
    private Integer population;
    private Map<String, RestCurrencyResponse> currency;
    private FixerCurrencyResponse currencyRate;
}
