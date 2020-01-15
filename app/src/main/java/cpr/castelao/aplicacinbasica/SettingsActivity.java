package cpr.castelao.aplicacinbasica;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class SettingsActivity extends BasicApp {

    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String ip = getString(R.string.IPSERVIDOR);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setBackgroundResource(R.color.naranjito);
        int colorint = getColor(R.color.naranjito);
        fab.setBackgroundColor(colorint);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ctx, DialogActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ctx = this;
    }
}
