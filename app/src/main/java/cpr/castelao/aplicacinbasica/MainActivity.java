package cpr.castelao.aplicacinbasica;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.SignStyle;

import static org.threeten.bp.temporal.ChronoField.DAY_OF_MONTH;
import static org.threeten.bp.temporal.ChronoField.MONTH_OF_YEAR;
import static org.threeten.bp.temporal.ChronoField.YEAR;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import cpr.castelao.aplicacinbasica.adapter.ListAdapter;
import cpr.castelao.aplicacinbasica.common.NotifController;
import cpr.castelao.aplicacinbasica.listeners.ListAdapterListener;
import cpr.castelao.aplicacinbasica.model.ListaPokemon;
import cpr.castelao.aplicacinbasica.model.Persona;
import cpr.castelao.aplicacinbasica.services.DownloadService;
import cpr.castelao.aplicacinbasica.services.PokeApiController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BasicApp {

    Context ctx;
    @BindView(R.id.act_main_time_lbl) TextView lblTime;
    @BindView(R.id.act_main_time2_lbl) TextView lblTime2;
    @BindView(R.id.act_main_lista_rec) RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toast("onCreate");

        initButtons();
        initData();
        initViews();
    }

    void initViews(){

        LocalDateTime dt = LocalDateTime.of(2008, 3, 30, 1, 30);

        lblTime.setText(""+ dt);

        DateTimeFormatter f = new DateTimeFormatterBuilder()
                .appendValue(YEAR, 4, 10, SignStyle.ALWAYS)
                .appendLiteral(' ')
                .appendText(MONTH_OF_YEAR)
                .appendLiteral('(')
                .appendValue(MONTH_OF_YEAR)
                .appendLiteral(')')
                .appendLiteral(' ')
                .appendValue(DAY_OF_MONTH, 2)
                .toFormatter(Locale.ENGLISH);

        String formatted = f.format(dt);
        lblTime2.setText(formatted);
    }

    @OnClick(R.id.act_main_setting_btn)
    void submit() {
        Intent intent = new Intent(ctx, SettingsActivity.class);
        startActivity(intent);
    }

    void initButtons() {

        Button btnSettings = findViewById(R.id.act_main_setting_btn);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, SettingsActivity.class);
                startActivity(intent);
            }
        });

        Button btnNotif = findViewById(R.id.act_main_notif_btn);
        btnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = "Elemento X";

                NotifController ctrl = NotifController.init(ctx);
                ctrl.showNotif(DetailsActivity.class, item);
            }
        });

        Button btnDown = findViewById(R.id.act_main_download_btn);
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://ep01.epimg.net/elpais/imagenes/2019/08/23/icon/1566563189_400624_1566563342_noticia_normal.jpg";

                Intent i= new Intent(ctx, DownloadService.class);
                i.putExtra(DownloadService.URL, url);
                startService(i);
            }
        });
    }

    private void initDb(){
        for (int idx = 0; idx < 10; idx++) {

            Persona p = new Persona();
            p.name = "Persona "+idx;
            p.trabajo = "Trabajo "+idx;
            p.imagen = "http://lorempixel.com/100/100/";

            p.save();
        }


        List<Persona> listado = Persona.listAll(Persona.class);

        ListAdapterListener listener = new ListAdapterListener() {
            @Override
            public void click(Persona item) {

                Intent intent = new Intent(ctx, DetailsActivity.class);
                //intent.putExtra(DetailsActivity.ITEM_CLICKADO, item);
                startActivity(intent);
            }
        };

        ListAdapter adapter = new ListAdapter(this, listado, listener);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        // LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        lista.setLayoutManager(mLayoutManager);
        lista.setAdapter(adapter);
    }

    void initData() {

        //initDb();

        PokeApiController ctrl = new PokeApiController();
        try {
            ctrl.todosLosPokemones(new Callback<ListaPokemon>() {
                @Override
                public void onResponse(Call<ListaPokemon> call, Response<ListaPokemon> response) {
                    ListaPokemon pokemons = response.body();
                    toast("Hay: "+pokemons.getCount()+" Pokemons");
                    // TODO: Deberiamos hacer un Adapter para la lista de los pokemons
                }

                @Override
                public void onFailure(Call<ListaPokemon> call, Throwable t) {
                    toast("Hubo un Error, Lo Siento");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void onRestart() {
        super.onRestart();
        toast("onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        toast("onStart");
        ctx = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        toast("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        toast("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        toast("onStop");
        ctx = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toast("onDestroy");

    }

    public void toast(String msg) {

        Context ctx = this;
        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);

        toast.show();
    }
}
