package com.project.regame.Features;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.project.regame.R;
import com.project.regame.Util.Session;


public class Story extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        session = new Session(getApplicationContext());
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "android.resource://" + getPackageName() + "/" + R.raw.story1;
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("cDialog1");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("link", link);
                bundle.putString("title", "Two Goats Story");
                Player player = new Player();
                player.setArguments(bundle);

                player.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat);
                player.show(ft, "link");

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "android.resource://" + getPackageName() + "/" + R.raw.story2;
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("cDialog1");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("link", link);
                bundle.putString("title", "Elephant and Friend" );
                Player player = new Player();
                player.setArguments(bundle);
                player.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat);
                player.show(ft, "link");

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "android.resource://" + getPackageName() + "/" + R.raw.story3;
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("cDialog1");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("link", link);
                bundle.putString("title", "Lion and the Mouse");
                Player player = new Player();
                player.setArguments(bundle);
                player.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat);
                player.show(ft, "link");

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "android.resource://" + getPackageName() + "/" + R.raw.story4;
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("cDialog1");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("link", link);
                bundle.putString("title", "The Thirsty Crow");
                Player player = new Player();
                player.setArguments(bundle);
                player.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat);
                player.show(ft, "link");

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "android.resource://" + getPackageName() + "/" + R.raw.story5;
                 FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("cDialog1");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("link", link);
                bundle.putString("title", "A Hole in the Fence");
                Player player = new Player();
                player.setArguments(bundle);
                player.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat);
                player.show(ft, "link");

            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = "android.resource://" + getPackageName() + "/" + R.raw.story6;
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("cDialog1");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putString("link", link);
                bundle.putString("title", "Strong or Weak");
                Player player = new Player();
                player.setArguments(bundle);
                player.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat);
                player.show(ft, "link");

            }
        });

    }


}