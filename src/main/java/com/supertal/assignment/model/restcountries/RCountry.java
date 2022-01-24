package com.supertal.assignment.model.restcountries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.supertal.assignment.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 24/01/2022 - 15:13
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RCountry {

    private Name name;

    private Integer population;

    @SerializedName("currencies")
    @Expose
    private Map<String, Currency> currency;
}