package cpr.castelao.aplicacinbasica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cpr.castelao.aplicacinbasica.ui.fragment01.Fragment01;

public class ActConFragments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_con_fragments);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Fragment01.newInstance())
                    .commitNow();
        }
    }
}
