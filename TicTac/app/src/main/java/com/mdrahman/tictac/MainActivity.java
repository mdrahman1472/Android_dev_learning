package com.mdrahman.tictac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; // 0 = red
    int[] stage = {2,2,2,2,2,2,2,2,2};
    // int [][] winner = {};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        // getting tag
        int tappedStage = Integer.parseInt(counter.getTag().toString());

        if (stage[tappedStage] == 2) {
            stage[tappedStage] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }
            counter.animate().alpha(1f).rotationX(720f).setDuration(1000);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
