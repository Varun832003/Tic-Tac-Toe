package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class landingPage extends AppCompatActivity {
    boolean singleUser = true;
    RadioButton singlePlayerbtn, multiPlayerbtn;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setBackgroundColor(getResources().getColor(R.color.landingBackgnd));
        singlePlayerbtn = findViewById(R.id.singlePlayerbtn);
        multiPlayerbtn = findViewById(R.id.multiPlayerbtn);
        MediaPlayer music = MediaPlayer.create(landingPage.this, R.raw.music);
        music.start();
        Intent intent = new Intent(this, singlePlayerName.class);
        singlePlayerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music.stop();
                singleUser = true;
                startActivity(intent);
            }
        });
        Intent intent1 = new Intent(this,MultiPlayerName.class);
        multiPlayerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music.stop();
               singleUser = false;
               startActivity(intent1);
            }
        });
    }
}