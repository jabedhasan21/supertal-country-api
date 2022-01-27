package com.supertal.countryapi.service.impl;

import com.supertal.countryapi.exception.EntityNotFoundException;
import com.supertal.countryapi.model.Country;
import com.supertal.countryapi.model.restcountries.RestCurrencyResponse;
import com.supertal.countryapi.model.fixer.FixerCurrencyResponse;
import com.supertal.countryapi.model.restcountries.RestCountryResponse;
import com.supertal.countryapi.service.CountryService;
import com.supertal.countryapi.service.CurrencyRateService;
import com.supertal.countryapi.service.FixerServiceGenerator;
import com.supertal.countryapi.service.RestCountryService;
import com.supertal.countryapi.service.CountryServiceGenerator;
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
        RestCountryService service = CountryServiceGenerator.createService(RestCountryService.class);
        Call<List<RestCountryResponse>> countryCallSync = service.getCountry(name);
        //Sync Call
        Response<List<RestCountryResponse>> response = countryCallSync.execute();
        List<RestCountryResponse> countries = response.body();

        if (countries == null || countries.size() == 0) {
            throw new EntityNotFoundException(Country.class, "name", name);
        }
        country.setName(countries.get(0).getName().getOfficial());
        country.setPopulation(countries.get(0).getPopulation());
        country.setCurrency(countries.get(0).getCurrency());

        String symbols = "USD";
        for (Map.Entry<String, RestCurrencyResponse> pair : country.getCurrency().entrySet()) {
            symbols = pair.getKey();
            System.out.println(String.format("Key (name) is: %s, Value (age) is : %s", pair.getKey(), pair.getValue()));
        }

        // Fixer
        CurrencyRateService currencyRateService = FixerServiceGenerator.createService(CurrencyRateService.class);

        Call<FixerCurrencyResponse> currencyCallSync = currencyRateService.getLatestExchange(fixerAccessKey, symbols);

        Response<FixerCurrencyResponse> fCurrencyResponse = currencyCallSync.execute();
        FixerCurrencyResponse fixerCurrencyResponse = fCurrencyResponse.body();
        if (fixerCurrencyResponse != null) {
            country.setCurrencyRate(fixerCurrencyResponse);
        }
        return country;
    }
}
