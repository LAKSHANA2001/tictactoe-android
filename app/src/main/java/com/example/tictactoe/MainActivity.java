package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Bitmap> arrbas;
    ImageView [] imgs=new ImageView[9];

    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    boolean gameActive=true;
    int counter=0;
    public void playerTap(View view) throws InterruptedException {
        ImageView img=(ImageView) view;
        int tappedImage=Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset();
            return;
        }

        if(gameState[tappedImage]==2 && gameActive) {
            counter++;
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.root), "Computer's Turn", Snackbar.LENGTH_LONG);
            snackbar.show();
                if(counter!=9) {
                int min = 0;
                int max = 8;
                int random = new Random().nextInt((max - min) + 1) + min;
                while (gameState[random] != 2) {

                    random = new Random().nextInt((max - min) + 1) + min;
                }
                gameState[random] = activePlayer;
                imgs[random].setImageResource(R.drawable.o);
                counter++;
                status = findViewById(R.id.status);
                activePlayer = 0;
                snackbar = Snackbar
                            .make(findViewById(R.id.root), "Your Turn", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    imgs[random].setTranslationY(-1000f);
                    imgs[random].animate().translationYBy(1000f).setDuration(1000);
            }


            img.animate().translationYBy(1000f).setDuration(300);

        }

        for(int [] winPosition:winPositions){
            if((gameState[winPosition[0]]==gameState[winPosition[1]])&&(gameState[winPosition[1]]==gameState[winPosition[2]])&&gameState[winPosition[0]]!=2){
                String winner;
                if(gameState[winPosition[0]]==0){
                    winner="You won 😁😁😁😁😁";

                    TextView status=findViewById(R.id.status);
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.root), winner, Snackbar.LENGTH_LONG);
                    snackbar.show();

                }
                else{
                    winner="Computer won \uD83D\uDE01\uD83D\uDE01\uD83D\uDE01\uD83D\uDE01\uD83D\uDE01 ";
                    TextView status=findViewById(R.id.status);
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.root), winner, Snackbar.LENGTH_LONG);
                    snackbar.show();

                }
                gameActive=false;
            }
        }
        if(counter==9)
        {
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.root), "Draw !! Tap to Reset", Snackbar.LENGTH_LONG);
            snackbar.show();
            gameActive=false;


        }
    }

    public void gameReset(){
        gameActive=true;
        activePlayer=0;
        counter=0;
        for (int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status=findViewById(R.id.status);
        status.setText("Tap to play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgs[0]=findViewById(R.id.imageView0);
        imgs[1]=findViewById(R.id.imageView1);
        imgs[2]=findViewById(R.id.imageView2);
        imgs[3]=findViewById(R.id.imageView3);
        imgs[4]=findViewById(R.id.imageView4);
        imgs[5]=findViewById(R.id.imageView5);
        imgs[6]=findViewById(R.id.imageView6);
        imgs[7]=findViewById(R.id.imageView7);
        imgs[8]=findViewById(R.id.imageView8);
        };
    }

