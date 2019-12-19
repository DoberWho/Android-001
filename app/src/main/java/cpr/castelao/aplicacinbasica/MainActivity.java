package cpr.castelao.aplicacinbasica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import cpr.castelao.aplicacinbasica.adapter.ListAdapter;
import cpr.castelao.aplicacinbasica.common.NotifController;
import cpr.castelao.aplicacinbasica.listeners.ListAdapterListener;
import cpr.castelao.aplicacinbasica.services.DownloadService;

public class MainActivity extends AppCompatActivity {

    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toast("onCreate");

        initButtons();
        initData();
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

    void initData() {

        ArrayList<String> items = new ArrayList();
        for (int idx = 0; idx < 10; idx++) {
            items.add("Elemento " + idx);
        }

        ListAdapterListener listener = new ListAdapterListener() {
            @Override
            public void click(String item) {

                Intent intent = new Intent(ctx, DetailsActivity.class);
                intent.putExtra(DetailsActivity.ITEM_CLICKADO, item);
                startActivity(intent);
            }
        };

        ListAdapter adapter = new ListAdapter(items, listener);

        RecyclerView lista = findViewById(R.id.act_main_lista_rec);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        // LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        lista.setLayoutManager(mLayoutManager);

        lista.setAdapter(adapter);
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
