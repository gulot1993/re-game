package com.project.regame.Features;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.project.regame.Features.Leaderboard.GameListActivity;
import com.project.regame.Features.signin.SignInActivity;
import com.project.regame.R;
import com.project.regame.Util.Session;


public class MenuActivity extends AppCompatActivity {
    AppCompatButton button, button1, button3,button4, button5, logout;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        session = new Session(getApplicationContext());
        button = findViewById(R.id.play);
        button1 = findViewById(R.id.resume);
         button1.setVisibility(View.VISIBLE);
        button4 = findViewById(R.id.video);
        button5= findViewById(R.id.leaderboard);
        button3 = findViewById(R.id.story);
        logout = findViewById(R.id.logout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainGame.class);
                session.clearSession();
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!session.getSession("name", "0").equals("0")){
                    Intent intent = new Intent(MenuActivity.this, MainGame.class);
                    startActivity(intent);
                } else {
                    button.callOnClick();
                }

            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Story.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Video.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, GameListActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, SignInActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });

    }
}