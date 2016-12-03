package com.ronypro.android.popularmovies.model.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rahony on 07/10/16.
 */

public class MovieDatabaseApiUtil {

    private static MovieDatabaseApi movieDatabaseApi;

    public static MovieDatabaseApi getApiInstance() {
        if (movieDatabaseApi == null) {
            movieDatabaseApi = createApi();
        }
        return movieDatabaseApi;
    }

    private static MovieDatabaseApi createApi() {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        GsonConverterFactory converterFactory = GsonConverterFactory.create(gson);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(converterFactory)
                .build();

        return retrofit.create(MovieDatabaseApi.class);
    }

    public static <T> T resolveCall(Call<T> call) throws HttpCallException, NetworkCallException {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                throw new HttpCallException(response.code(), response.errorBody());
            }
        } catch (IOException e) {
            throw new NetworkCallException(e);
        }
    }
}
