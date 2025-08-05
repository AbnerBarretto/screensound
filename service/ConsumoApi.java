package br.com.alura.screensound.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConsumoApi {
    public static String buscarUrl(String url) throws Exception {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).get().build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                throw new RuntimeException("Erro na API: " + response.code());
            }
        }
    }
}
