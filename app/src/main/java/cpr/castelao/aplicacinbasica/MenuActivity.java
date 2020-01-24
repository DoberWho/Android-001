package cpr.castelao.aplicacinbasica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import cpr.castelao.aplicacinbasica.adapter.PlatoAdapter;
import cpr.castelao.aplicacinbasica.listeners.PlatoAdapterListener;
import cpr.castelao.aplicacinbasica.model.realm.Plato;

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

            }
        });
    }

    private void initData() {

        ArrayList<Plato> platos = new ArrayList<>();

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
