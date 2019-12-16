package cpr.castelao.aplicacinbasica;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    public static final String ITEM_CLICKADO = "item_clickado";
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        item = intent.getStringExtra(ITEM_CLICKADO);

        initData();
    }

    void initData(){
        TextView txt = findViewById(R.id.act_details_item_lbl);
        txt.setText("DETALLES: "+item);
    }

}
