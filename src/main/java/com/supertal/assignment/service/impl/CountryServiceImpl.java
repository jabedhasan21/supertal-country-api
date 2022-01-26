package com.supertal.assignment.service.impl;

import com.supertal.assignment.model.Country;
import com.supertal.assignment.model.Currency;
import com.supertal.assignment.model.fixer.FCurrencyResponse;
import com.supertal.assignment.model.restcountries.RCountry;
import com.supertal.assignment.service.CountryService;
import com.supertal.assignment.service.CurrencyRateService;
import com.supertal.assignment.service.FixerServiceGenerator;
import com.supertal.assignment.service.RestCountryService;
import com.supertal.assignment.service.RetrofitServiceGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;
import java.util.Map;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 24/01/2022 - 12:27
 */

@Service
public class CountryServiceImpl implements CountryService {

    @Value("${fixer.access_key:''}")
    private String fixerAccessKey;

    @Override
    public Country getByName(String name) throws Exception {
        Country country = new Country();
        RestCountryService service = RetrofitServiceGenerator.createService(RestCountryService.class);
        Call<List<RCountry>> callAsync = service.getCountry(name);
        //callAsync
        /* callAsync.enqueue(new Callback<List<RCountry>>() {
            @Override
            public void onResponse(Call<List<RCountry>> call, Response<List<RCountry>> response) {
                List<RCountry> countries = response.body();
                System.out.println(countries);
            }

            @Override
            public void onFailure(Call<List<RCountry>> call, Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });*/

        //Sync Call
        Response<List<RCountry>> response = callAsync.execute();
        List<RCountry> countries = response.body();

        if (countries != null && countries.size() > 0) {
            country.setName(countries.get(0).getName().getOfficial());
            country.setPopulation(countries.get(0).getPopulation());
            country.setCurrency(countries.get(0).getCurrency());
        }

        String symbols = "USD";
        for (Map.Entry<String, Currency> pair : country.getCurrency().entrySet()) {
            symbols = pair.getKey();
            System.out.println(String.format("Key (name) is: %s, Value (age) is : %s", pair.getKey(), pair.getValue()));
        }

        // Fixer

        CurrencyRateService currencyRateService = FixerServiceGenerator.createService(CurrencyRateService.class);

        Call<FCurrencyResponse> currencyCallSync = currencyRateService.getLatestExchange(fixerAccessKey, symbols);

        Response<FCurrencyResponse> fCurrencyResponse = currencyCallSync.execute();
        FCurrencyResponse crncyRespose = fCurrencyResponse.body();
        if (crncyRespose != null) {
            country.setCurrencyRate(crncyRespose);
        }

        return country;
    }
}