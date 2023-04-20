package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class singlePlayerName extends AppCompatActivity {
    RelativeLayout relativeLayout;
    TextInputLayout inputLayout;
    TextInputEditText playerName;
    Button button;
    public static final String Extra_Name = "com.example.tictactoe.extra.name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player_name);
        Intent intent = getIntent();
        MediaPlayer music = MediaPlayer.create(singlePlayerName.this, R.raw.music);
        music.start();
        relativeLayout = findViewById(R.id.relativeLayout);
        relativeLayout.setBackgroundResource(R.color.singlePlayerBack);
        playerName = findViewById(R.id.playerName1);
        button = findViewById(R.id.button);
        Intent intent1 = new Intent(this,MainActivity.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music.stop();
                String nameText = playerName.getText().toString();
                intent1.putExtra(Extra_Name,nameText);
                startActivity(intent1);
            }
        });



    }



}