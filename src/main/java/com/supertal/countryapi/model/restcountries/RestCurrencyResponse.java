package com.supertal.countryapi.model.restcountries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 24/01/2022 - 12:24
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestCurrencyResponse {
    private String name;
    private String symbol;
}
