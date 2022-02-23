package com.project.regame.Features;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.project.regame.R;
import com.project.regame.Util.Session;


public class Video extends AppCompatActivity {
    Button btn1, btn2, btn3;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        session = new Session(getApplicationContext());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "android.resource://" + getPackageName() + "/" + R.raw.video1;
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("cDialog1");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("link", link);
                bundle.putString("title", "ABC Letter Sounds");
                Player player = new Player();
                player.setArguments(bundle);

                player.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat);
                player.show(ft, "link");

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "android.resource://" + getPackageName() + "/" + R.raw.video2;
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("cDialog1");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("link", link);
                bundle.putString("title", "THE ABC SONG");
                Player player = new Player();
                player.setArguments(bundle);
                player.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat);
                player.show(ft, "link");

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "android.resource://" + getPackageName() + "/" + R.raw.video3;
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("cDialog1");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("link", link);
                bundle.putString("title", "Phonic Songs");
                Player player = new Player();
                player.setArguments(bundle);
                player.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat);
                player.show(ft, "link");

            }
        });

    }
}