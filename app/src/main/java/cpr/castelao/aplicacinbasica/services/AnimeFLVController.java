package cpr.castelao.aplicacinbasica.services;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AnimeFLVController {

    private final String HOST = "https://animeflv.net/";


    OkHttpClient client;
    Request request;

    public AnimeFLVController(){
        client = new OkHttpClient();
    }


    public void getEpisodies() throws IOException {

        request = new Request.Builder()
                .url(HOST)
                .build();

        Response res = client.newCall(request).execute();
        String html = res.body().string();

        Document doc = Jsoup.parse(html);
        Elements episodios = doc.getElementsByClass("fa-play");
        for (Element link : episodios) {
            String url = link.attr("href");
            String image = link.getElementsByTag("img").attr("src");
            Elements nombres = link.getElementsByClass("Capi");
            String nombre = "";
            if (nombres !=null && nombres.size() > 0){
                nombre = nombres.get(0).text();
            }
            String serie = link.getElementsByTag("strong").text();

            Log.i("EPISODIO","N: "+serie+" -EP:"+nombre);
        }


    }


}
