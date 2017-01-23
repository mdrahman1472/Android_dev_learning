package com.mdrahman.guessgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int randomNum;

    public void makeToast(String string){
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
    }

    public void guess(View view){

        EditText guessNum = (EditText) findViewById(R.id.textId);

        int guessInt = Integer.parseInt(guessNum.getText().toString());

        if (guessInt > randomNum){

            makeToast("Lower!");
        }
        else if(guessInt < randomNum){
            makeToast("Higher!");
        }

        else{

            makeToast("Correct !!!  Try again");

            Random rand = new Random();
            randomNum = rand.nextInt(20) + 1;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();
        randomNum = rand.nextInt(20) + 1;
    }
}
