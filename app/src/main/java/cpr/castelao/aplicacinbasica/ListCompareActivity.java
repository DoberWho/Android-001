package cpr.castelao.aplicacinbasica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ListCompareActivity extends AppCompatActivity {

    private ArrayList<String> nombresSeries = new ArrayList<>();

    private RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_compare);

        lista = findViewById(R.id.act_list_compare_rec);

        initData();
        doCompare();

        getItems();
    }

    private void getItems() {

        final List<String> listado = new ArrayList<>();

        final ListAdapter adapter = new ListAdapter(this, listado, listener);
        lista.setAdapter(adapter);


        AnimeController ctrl = new AnimeController(ctx);
        try {
            ctrl.getEpisodies(new PaginaListener() {
                @Override
                public void devolver(List<Episodio> items) {
                    listado.addAll(items);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(mLayoutManager);

    }

    private void initData(){

        nombresSeries.add("Tower of Druaga");
        nombresSeries.add("Hellsing");
        nombresSeries.add("Chobits");
        nombresSeries.add("Attack of Titans");
        nombresSeries.add("Fairy Tail");
        nombresSeries.add("Astro Boy");
        nombresSeries.add("Excell Saga");
        nombresSeries.add("FLCL");
        nombresSeries.add("Please, Save My Earth");
        nombresSeries.add("Kimagure Orange Road");
        nombresSeries.add("Mazinger Z");
        nombresSeries.add("Kaiser Mazinger");
        nombresSeries.add("Saint Seiya");
        nombresSeries.add("Dragon Ball");
        nombresSeries.add("Wedding Peach");

    }


    private void doCompare(){
        String nombreEpisodio = "Tower of Druaga S01 E02";

        for(String nombre : nombresSeries){
            if (nombreEpisodio.contains(nombre)){
                String nEpisodio = nombreEpisodio.replace(nombre, "").trim();
                Log.d("SERIE","La serie está dentro de la lista");
            }

        }

    }
}
