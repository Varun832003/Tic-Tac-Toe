package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;


public class MultiPlayerName extends AppCompatActivity {
    RelativeLayout relativeLayout;
    TextInputEditText playerName1, playerName2;
    Button button;
    public static final String Extra_Name1 = "com.example.tictactoe.multiplayername1";
    public static final String Extra_Name2 = "com.example.tictactoe.multiplayername2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player_name);
        MediaPlayer music = MediaPlayer.create(MultiPlayerName.this, R.raw.music);
        music.start();
        relativeLayout = findViewById(R.id.relativeLayout);
        playerName1 = findViewById(R.id.playerName1);
        playerName2 = findViewById(R.id.playerName2);
        button = findViewById(R.id.button);
        relativeLayout.setBackgroundResource(R.color.singlePlayerBack);
        Intent intent = getIntent();
        Intent intent1 = new Intent(this,MainActivity.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music.stop();
                String name1 = Objects.requireNonNull(playerName1.getText()).toString();
                String name2 = Objects.requireNonNull(playerName2.getText()).toString();
                intent1.putExtra(Extra_Name1,name1);
                intent1.putExtra(Extra_Name2,name2);
                startActivity(intent1);
            }
        });
    }
}