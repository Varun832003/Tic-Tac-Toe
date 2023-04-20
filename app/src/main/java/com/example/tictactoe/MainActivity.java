package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AlertDialogLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    boolean playerTurn = true;
    ImageButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    Button reset;
    Button player1;
    Button player2;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout2;
    GridLayout gridLayout;
    int player1Score = 0;
    int player2Score = 0;
    // Player representation
    // 0 - X
    // 1 - O
    Random random = new Random();
    int activePlayer = random.nextInt(2);
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    // State meanings
    // 0-x
    // 1-O
    // 2-null
    int winPositions[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void onClick(View view){
        MediaPlayer button = MediaPlayer.create(MainActivity.this, R.raw.buttonsound);
        button.start();
        ImageButton btn = (ImageButton) view;
        int tappedbtn = Integer.parseInt(btn.getTag().toString());

        if(gameState[tappedbtn]==2){
            gameState[tappedbtn] = activePlayer;
            if(activePlayer==0){
                player1.getBackground().setAlpha(80);
                player2.getBackground().setAlpha(250);
                btn.setImageResource(R.drawable.cross);
                activePlayer=1;
            }else{
                player2.getBackground().setAlpha(80);
                player1.getBackground().setAlpha(250);
                btn.setImageResource(R.drawable.circle);
                activePlayer=0;
            }
        }
        // Check if any player has won
        for(int winpos=0; winpos<winPositions.length; winpos++ ){
            if(gameState[winPositions[winpos][0]]==gameState[winPositions[winpos][1]] && gameState[winPositions[winpos][1]]==gameState[winPositions[winpos][2]]
            && gameState[winPositions[winpos][0]]!=2 && gameState[winPositions[winpos][1]]!=2 && gameState[winPositions[winpos][0]]!=2){
                // Somebody has won, find out who?
                if(gameState[winPositions[winpos][0]]==0){
                    // Player 1 Won
                    MediaPlayer winSound  = MediaPlayer.create(MainActivity.this,R.raw.winsound);
                    winSound.start();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle(player1.getText().toString()+"WON !");
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            winSound.stop();
                        }
                    });
                    builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            gameReset();
                            dialogInterface.cancel();
                            winSound.stop();
                        }
                    });
                    builder.show();
                }else{
                    // Player 2 Won
                    MediaPlayer winSound  = MediaPlayer.create(MainActivity.this,R.raw.winsound);
                    winSound.start();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle(player2.getText().toString()+"WON !");
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            winSound.stop();
                        }
                    });
                    builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            gameReset();
                            dialogInterface.cancel();
                            winSound.stop();
                        }
                    });
                    builder.show();
                }
            }
        }
    }
    public void gameReset(){
        activePlayer = random.nextInt(2);
        if(activePlayer==0){
            player1.getBackground().setAlpha(250);
            player2.getBackground().setAlpha(80);
        }else{
            player2.getBackground().setAlpha(250);
            player1.getBackground().setAlpha(80);
        }
        for(int i=0; i<gameState.length; i++){
            gameState[i]=2;
        }
        ((ImageButton)findViewById(R.id.btn1)).setImageResource(0);
        ((ImageButton)findViewById(R.id.btn2)).setImageResource(0);
        ((ImageButton)findViewById(R.id.btn3)).setImageResource(0);
        ((ImageButton)findViewById(R.id.btn4)).setImageResource(0);
        ((ImageButton)findViewById(R.id.btn5)).setImageResource(0);
        ((ImageButton)findViewById(R.id.btn6)).setImageResource(0);
        ((ImageButton)findViewById(R.id.btn7)).setImageResource(0);
        ((ImageButton)findViewById(R.id.btn8)).setImageResource(0);
        ((ImageButton)findViewById(R.id.btn9)).setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        reset = findViewById(R.id.reset);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        relativeLayout = findViewById(R.id.relativeLayout);
        linearLayout2 = findViewById(R.id.linearLayout2);
        gridLayout = findViewById(R.id.gridLayout);
        gridLayout.setBackgroundResource(R.color.grey);
        relativeLayout.setBackgroundResource(R.color.black);
        linearLayout2.setBackgroundColor(getResources().getColor(R.color.black));
        Intent intent = getIntent();
        String name = intent.getStringExtra(singlePlayerName.Extra_Name);
        if(name!=null){
            player1.setText(name);
        }
        else{
            String name1 = intent.getStringExtra(MultiPlayerName.Extra_Name1);
            String name2 = intent.getStringExtra(MultiPlayerName.Extra_Name2);
            player1.setText(name1);
            player2.setText(name2);
        }
        if(activePlayer==0){
            player2.getBackground().setAlpha(80);
        }else{
            player1.getBackground().setAlpha(80);
        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer resetSound = MediaPlayer.create(MainActivity.this,R.raw.buttonsound);
                resetSound.start();
                activePlayer = random.nextInt(2);
                if(activePlayer==0){
                    player1.getBackground().setAlpha(250);
                    player2.getBackground().setAlpha(80);
                }else{
                    player2.getBackground().setAlpha(250);
                    player1.getBackground().setAlpha(80);
                }
                for(int i=0; i<gameState.length; i++){
                    gameState[i]=2;
                }
                ((ImageButton)findViewById(R.id.btn1)).setImageResource(0);
                ((ImageButton)findViewById(R.id.btn2)).setImageResource(0);
                ((ImageButton)findViewById(R.id.btn3)).setImageResource(0);
                ((ImageButton)findViewById(R.id.btn4)).setImageResource(0);
                ((ImageButton)findViewById(R.id.btn5)).setImageResource(0);
                ((ImageButton)findViewById(R.id.btn6)).setImageResource(0);
                ((ImageButton)findViewById(R.id.btn7)).setImageResource(0);
                ((ImageButton)findViewById(R.id.btn8)).setImageResource(0);
                ((ImageButton)findViewById(R.id.btn9)).setImageResource(0);
            }
        });
    }

}
