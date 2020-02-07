package cpr.castelao.aplicacinbasica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cpr.castelao.aplicacinbasica.ui.fragment01.Fragment01;
import cpr.castelao.aplicacinbasica.ui.fragment02.Fragment02;
import cpr.castelao.aplicacinbasica.ui.fragment03.VideoFragment;
import cpr.castelao.aplicacinbasica.ui.fragment04.CalendarFragment;

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
        Button btnFrg03 = findViewById(R.id.act_fragments_frg03_btn);
        Button btnFrg04 = findViewById(R.id.act_fragments_frg04_btn);

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

        btnFrg03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCambiarFrg03();
            }
        });

        btnFrg04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCambiarFrg04();
            }
        });


        Button btnFrg05 = findViewById(R.id.act_launch_web_btn);
        btnFrg05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goWebsite();
            }
        });

        Button btnFrg06 = findViewById(R.id.act_launch_msg_btn);
        btnFrg06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendWhastapp();
            }
        });


    }

    private void sendWhastapp(){


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String text = " Hola, Me llamo Ralph";

        //Whatsapp intent.setPackage("com.whatsapp");
        //Linkedin   intent.setPackage("com.linkedin.android");
        //Twitter intent.setPackage("com.twitter.android");
        //Facebook intent.setPackage("com.facebook.katana");

        intent.setPackage("com.whatsapp");
        if (intent != null) {
            intent.putExtra(Intent.EXTRA_TEXT, text);//
            startActivity(Intent.createChooser(intent, text));
        } else {

            Toast.makeText(this, "App not found", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void goWebsite(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ctbarbanza.com"));
        startActivity(browserIntent);
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

    private void doCambiarFrg03() {

        String url = "https://www.sample-videos.com/video123/mp4/240/big_buck_bunny_240p_30mb.mp4";
        VideoFragment frg = VideoFragment.newInstance(url);

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction trans = manager.beginTransaction();
        trans.replace(R.id.contenedor, frg,"fragment_03");
        trans.commitNow();
    }

    private void doCambiarFrg04() {
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction trans = manager.beginTransaction();
        trans.replace(R.id.contenedor, new CalendarFragment(),"fragment_04");
        trans.commitNow();
    }
}
