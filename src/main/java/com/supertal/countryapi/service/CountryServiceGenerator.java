package com.supertal.countryapi.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 24/01/2022 - 14:45
 */
public class CountryServiceGenerator {

    private static final String BASE_URL = "https://restcountries.com/";

    private static Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
