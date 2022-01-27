package com.supertal.countryapi.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 25/01/2022 - 02:20
 */
public class FixerServiceGenerator {
    private static final String BASE_URL = "http://data.fixer.io/";

    private static Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
