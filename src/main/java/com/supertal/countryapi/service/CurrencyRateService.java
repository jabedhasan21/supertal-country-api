package com.supertal.countryapi.service;

import com.supertal.countryapi.model.fixer.FixerCurrencyResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 25/01/2022 - 02:09
 */
public interface CurrencyRateService {
    @GET("/api/latest")
    Call<FixerCurrencyResponse> getLatestExchange(@Query("access_key") String access_key, @Query("symbols") String symbols);
}
