package cpr.castelao.aplicacinbasica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cpr.castelao.aplicacinbasica.ui.fragment01.Fragment01;
import cpr.castelao.aplicacinbasica.ui.fragment02.Fragment02;

public class ActConFragments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_con_fragments);
        if (savedInstanceState == null) {

            FragmentManager manager = getSupportFragmentManager();

            FragmentTransaction trans = manager.beginTransaction();
            trans.replace(R.id.contenedor, Fragment01.newInstance());
            trans.commitNow();
        }

        initView();
    }

    private void initView() {
        Button btnFrg01 = findViewById(R.id.act_fragments_frg01_btn);
        Button btnFrg02 = findViewById(R.id.act_fragments_frg02_btn);


        btnFrg01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCambiarFrg01();
            }


        });

        btnFrg02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCambiarFrg02();
            }
        });
    }

    private void doCambiarFrg01() {
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction trans = manager.beginTransaction();
        trans.replace(R.id.contenedor, new Fragment01(),"fragment_01");
        trans.commitNow();
    }

    private void doCambiarFrg02() {

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction trans = manager.beginTransaction();
        trans.replace(R.id.contenedor, new Fragment02(),"fragment_02");
        trans.commitNow();
    }
}
