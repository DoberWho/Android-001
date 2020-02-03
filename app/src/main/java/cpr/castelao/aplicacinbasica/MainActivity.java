package cpr.castelao.aplicacinbasica;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import cpr.castelao.aplicacinbasica.adapter.ListAdapter;
import cpr.castelao.aplicacinbasica.common.NotifController;
import cpr.castelao.aplicacinbasica.listeners.ListAdapterListener;
import cpr.castelao.aplicacinbasica.model.Episodio;
import cpr.castelao.aplicacinbasica.model.ListaPokemon;
import cpr.castelao.aplicacinbasica.model.Persona;
import cpr.castelao.aplicacinbasica.services.AnimeFLVController;
import cpr.castelao.aplicacinbasica.services.PokeApiController;
import cpr.castelao.aplicacinbasica.services.controladoresPaginas.PaginaListener;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BasicApp {

    Context ctx;
    //@BindView(R.id.act_main_time_lbl) TextView lblTime;
    //@BindView(R.id.act_main_time2_lbl) TextView lblTime2;
    //@BindView(R.id.act_main_lista_rec) RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        toast("onCreate");

        System.out.println("HOLA ANDROID");
        Log.v("MAIN","Hola Verbose");
        Log.i("MAIN","Hola Info");
        Log.d("MAIN","Hola Debug");
        Log.w("MAIN","Hola Warning");
        Log.e("MAIN","Hola Error");


        initButtons();
        initData();
        initViews();
    }

    void initViews(){

        LocalDateTime dt = LocalDateTime.of(2008, 3, 30, 1, 30);

        //lblTime.setText(""+ dt);

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
        //lblTime2.setText(formatted);
    }

    @OnClick(R.id.act_main_settings_btn)
    void submit() {
        Intent intent = new Intent(ctx, SettingsActivity.class);
        startActivity(intent);
    }

    void initButtons() {

        // Recojemos el String traducido segun el idioma del terminal
        if (ctx != null){
            String str = ctx.getString(R.string.notification_label);
        }


        Button btnOkhttp = findViewById(R.id.act_main_okhttp_btn);

        btnOkhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button btnSettings = findViewById(R.id.act_main_settings_btn);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, SettingsActivity.class);
                startActivity(intent);
            }
        });

        Button btnFrg = findViewById(R.id.act_main_fragment_btn);
        btnFrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, ActConFragments.class);
                startActivity(intent);
            }
        });

        Button btnMenu = findViewById(R.id.act_main_menu_btn);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, MenuActivity.class);
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

                // Uso de un Service
                /*
                String url = "https://ep01.epimg.net/elpais/imagenes/2019/08/23/icon/1566563189_400624_1566563342_noticia_normal.jpg";

                Intent i= new Intent(ctx, DownloadService.class);
                i.putExtra(DownloadService.URL, url);
                startService(i);
                //*/

               /*
                try {
                    doRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //*/

                AnimeFLVController ctrl = new AnimeFLVController(ctx);
                try {
                    ctrl.getEpisodies(new PaginaListener() {
                        @Override
                        public void devolver(List<Episodio> items) {

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void doRequest() throws IOException {
        PokeApiController ctrl = new PokeApiController();

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
    }

    private void initDb(){
        List<Persona> listado = new ArrayList<>();
        for (int idx = 0; idx < 10; idx++) {

            Persona p = new Persona();
            p.name = "Persona "+idx;
            p.trabajo = "Trabajo "+idx;
            p.imagen = "http://lorempixel.com/100/100/";
            listado.add(p);
            //p.save();
        }

        //Persona.listAll(Persona.class);

        ListAdapterListener listener = new ListAdapterListener() {
            @Override
            public void click(Persona item) {

                Intent intent = new Intent(ctx, DetailsActivity.class);
                intent.putExtra(DetailsActivity.ITEM_CLICKADO, item);
                intent.putExtra("persona","");
                startActivity(intent);
            }
        };

        ListAdapter adapter = new ListAdapter(this, listado, listener);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        // LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView lista = findViewById(R.id.act_main_lista_rec);
        lista.setLayoutManager(mLayoutManager);
        lista.setAdapter(adapter);
    }

    void initData() {

        initDb();

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
