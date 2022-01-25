package com.supertal.assignment.model.fixer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.supertal.assignment.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 25/01/2022 - 02:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FCurrencyResponse {
    private Long timestamp;
    private String base;
    @SerializedName("rates")
    @Expose
    private Map<String, Double> rates;
}
