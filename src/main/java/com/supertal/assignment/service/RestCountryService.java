package com.supertal.assignment.service;

import com.supertal.assignment.model.restcountries.RestCountryResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 24/01/2022 - 14:20
 */
public interface RestCountryService {

    @GET("/v3.1/name/{name}")
    Call<List<RestCountryResponse>> getCountry(@Path("name") String name);
}
