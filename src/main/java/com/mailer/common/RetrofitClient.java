package com.mailer.common;

import com.mailer.common.utils.ApplicationPropertiesReader;
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
                .baseUrl(ApplicationPropertiesReader.getProperty("backendBaseUrl", "http://localhost:8001"))
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        }
        return retrofit;
    }
}