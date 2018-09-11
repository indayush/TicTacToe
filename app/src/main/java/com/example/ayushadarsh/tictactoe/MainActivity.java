package com.example.ayushadarsh.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    // 0 = Yellow , 1 = Red
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;


    public void dropTile(View view){

        ImageView counter = (ImageView)view;
        Log.i("Tag",counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winPos : winPos) {

                if (gameState[winPos[0]] == gameState[winPos[1]] && gameState[winPos[1]] == gameState[winPos[2]] && gameState[winPos[0]] != 2) {

                    String winner = "";

                    if (activePlayer == 1) {

                        winner = " Yellow ";
                    } else {

                        winner = " Red";
                    }
                    //Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();
                    gameActive = false;

                    Button playAgainButton = (Button)findViewById(R.id.playAgain);
                    TextView winnerView = (TextView)findViewById(R.id.winnerTextview);

                    winnerView.setText("Player " + winner + " has Won" );

                    playAgainButton.setVisibility(view.VISIBLE);
                    winnerView.setVisibility(view.VISIBLE);


                }
            }
        }
    }

    public void playAgain(View view){

        Button playAgainButton = (Button)findViewById(R.id.playAgain);
        TextView winnerView = (TextView)findViewById(R.id.winnerTextview);

        playAgainButton.setVisibility(view.INVISIBLE);
        winnerView.setVisibility(view.INVISIBLE);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);

            //TextView child = (TextView)mGridView.getChildAt(i);
            // do stuff with child view
        }

        activePlayer = 0;
        gameActive = true;

        for (int i=0;i<gameState.length;i++)
        gameState[i] = 2;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
