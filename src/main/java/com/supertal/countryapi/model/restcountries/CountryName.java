package com.supertal.countryapi.model.restcountries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 24/01/2022 - 15:12
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryName {
    private String common;
    private String official;
}
