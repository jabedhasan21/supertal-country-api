package com.supertal.assignment.model.restcountries;

import com.supertal.assignment.model.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 24/01/2022 - 15:10
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryList {
    private List<RCountry> countries;
}
