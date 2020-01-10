package cpr.castelao.aplicacinbasica.services;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SpotifyApiController {

    private final String HOST = "https://api.spotify.com/v1/";


    OkHttpClient client;
    Request request;

    public SpotifyApiController(){
        client = new OkHttpClient();
    }


    public void get() throws IOException {
        String url = "";
        request = new Request.Builder()
                .url(url)
                .build();

        Response res = client.newCall(request).execute();
        String response = res.body().string();


    }


}
