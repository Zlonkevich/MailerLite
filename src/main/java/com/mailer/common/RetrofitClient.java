package com.mailer.common;

import com.mailer.common.utils.ConfigReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Component
@RequiredArgsConstructor
public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl(ConfigReader.getProperty("backendBaseUrl"))
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        }
        return retrofit;
    }
}