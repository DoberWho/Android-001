package cpr.castelao.aplicacinbasica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cpr.castelao.aplicacinbasica.adapter.PlatoAdapter;
import cpr.castelao.aplicacinbasica.listeners.PlatoAdapterListener;
import cpr.castelao.aplicacinbasica.model.realm.Menu;
import cpr.castelao.aplicacinbasica.model.realm.Plato;
import cpr.castelao.aplicacinbasica.model.realm.Preferencias;
import cpr.castelao.aplicacinbasica.model.realm.RealmController;
import cpr.castelao.aplicacinbasica.model.realm.TipoAlimento;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MenuActivity extends AppCompatActivity {

    private Button btn;
    private Spinner selector;
    private RecyclerView contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_menu);

        initView();
        initButtons();
        initData();
    }

    private void initView() {
        btn = findViewById(R.id.act_menu_btn);
        selector = findViewById(R.id.act_menu_spinner);
        contenedor = findViewById(R.id.act_menu_container_rec);
    }

    private void initButtons() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarDb();
            }


        });
    }

    private void generarDb() {

        Realm realm = RealmController.init().get();
        realm.beginTransaction();

        TipoAlimento tipo1 = new TipoAlimento();
        tipo1.setNombre("Carne");

        TipoAlimento tipo2 = new TipoAlimento();
        tipo2.setNombre("Pescado");

        TipoAlimento tipo3 = new TipoAlimento();
        tipo3.setNombre("Verduras");

        TipoAlimento tipo4 = new TipoAlimento();
        tipo4.setNombre("Lo que cocina la vecina que huele tan mal");

        Preferencias prefs = new Preferencias();
        prefs.setTipoPreferido(tipo3);

        Plato plato1 = new Plato();
        RealmList<TipoAlimento> alimentos1 = new RealmList<TipoAlimento>();
        alimentos1.add(tipo1);
        alimentos1.add(tipo3);
        plato1.setAlimentos(alimentos1);

        Plato plato2 = new Plato();
        RealmList<TipoAlimento> alimentos2 = new RealmList<TipoAlimento>();
        alimentos2.add(tipo2);
        alimentos2.add(tipo3);
        plato2.setAlimentos(alimentos2);

        Plato plato3 = new Plato();
        RealmList<TipoAlimento> alimentos3 = new RealmList<TipoAlimento>();
        alimentos3.add(tipo3);
        plato3.setAlimentos(alimentos3);

        Plato plato4 = new Plato();
        RealmList<TipoAlimento> alimentos4 = new RealmList<TipoAlimento>();
        alimentos4.add(tipo4);
        alimentos4.add(tipo1);
        plato4.setAlimentos(alimentos4);

        Menu menu = new Menu();
        RealmList<Plato> platos1 = new RealmList<Plato>();
        platos1.add(plato1);
        platos1.add(plato2);
        menu.setPlatos(platos1);

        Menu menu2 = new Menu();
        RealmList<Plato> platos2 = new RealmList<Plato>();
        platos2.add(plato4);
        platos2.add(plato3);
        menu2.setPlatos(platos2);
        realm.commitTransaction();
    }

    private void initData() {

        ArrayList<Plato> platos = new ArrayList<>();

        // RECOGER TODOS LOS PLATOS
        Realm realm = RealmController.init().get();
        RealmQuery<Plato> query = realm.where(Plato.class);
        RealmResults<Plato> res = query.findAll();
        for (Plato plato : res){
            platos.add(plato);
        }

        PlatoAdapterListener listener = new PlatoAdapterListener() {
            @Override
            public void click(Plato item) {

            }
        };

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        //GridLayoutManager gridManager = new GridLayoutManager(this, 4);
        // LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        contenedor.setLayoutManager(mLayoutManager);

        PlatoAdapter adaptador = new PlatoAdapter(this, platos, listener);

        contenedor.setAdapter(adaptador);
    }

}
