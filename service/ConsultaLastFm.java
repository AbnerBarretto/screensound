package br.com.alura.screensound.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConsultaLastFm {

    private static final String API_KEY = "99f2d5095a9ca9991ae391cba7641d2a";
    private static final String API_URL = "http://ws.audioscrobbler.com/2.0/";

    public static String buscarBioArtista(String nomeArtista) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = HttpUrl.parse(API_URL).newBuilder()
                .addQueryParameter("method", "artist.getinfo")
                .addQueryParameter("artist", nomeArtista)
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("format", "json")
                .build();

        Request request = new Request.Builder().url(url).get().build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String json = response.body().string();
                JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
                JsonObject artist = obj.getAsJsonObject("artist");
                String nome = artist.get("name").getAsString();
                String biografia = artist.getAsJsonObject("bio").get("summary").getAsString();

                return "**" + nome + "**: " + biografia.replaceAll("<a.*", "").trim();
            } else {
                return "Erro na API Last.fm: " + response.code();
            }
        } catch (Exception e) {
            return "Erro: " + e.getMessage();
        }
    }
}