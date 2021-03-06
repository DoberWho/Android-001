package cpr.castelao.aplicacinbasica.services;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cpr.castelao.aplicacinbasica.R;
import cpr.castelao.aplicacinbasica.model.Episodio;
import cpr.castelao.aplicacinbasica.services.controladoresPaginas.Fenix;
import cpr.castelao.aplicacinbasica.services.controladoresPaginas.Flv;
import cpr.castelao.aplicacinbasica.services.controladoresPaginas.PaginaEpisodios;
import cpr.castelao.aplicacinbasica.services.controladoresPaginas.PaginaListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AnimeFLVController {

    private Context ctx;
    private String[] paginas;


    OkHttpClient client;
    Request request;

    public AnimeFLVController(Context ctx){
        this.ctx = ctx;
        client = new OkHttpClient();

        Resources res = ctx.getResources();
        paginas = res.getStringArray(R.array.paginas);
    }


    public void getEpisodies(PaginaListener listener ) throws IOException {

        ArrayList<PaginaEpisodios> items = new ArrayList<>();
        items.add(new Fenix());
        items.add(new Flv());

        for (PaginaEpisodios pagina: items) {
            getPaginaEpisodios(pagina, listener);
        }
    }

    private void getPaginaEpisodios(final PaginaEpisodios pagina, final PaginaListener listener) {
        request = new Request.Builder()
                .url(pagina.url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String html = response.body().string();

                Document doc = Jsoup.parse(html);
                List<Episodio> items = pagina.getFromDocument(doc);

                if (listener !=null){
                    listener.devolver(items);
                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast toast = Toast.makeText(ctx, "UPS... Fue mal", Toast.LENGTH_SHORT);
                toast.show();
                e.printStackTrace();
            }
        });
    }


}
