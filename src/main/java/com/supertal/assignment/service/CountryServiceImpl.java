package com.supertal.assignment.service;

import com.supertal.assignment.model.Country;
import com.supertal.assignment.model.restcountries.CountryList;
import com.supertal.assignment.model.restcountries.RCountry;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 24/01/2022 - 12:27
 */

@Service
public class CountryServiceImpl implements CountryService {
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
        return country;
    }
}
