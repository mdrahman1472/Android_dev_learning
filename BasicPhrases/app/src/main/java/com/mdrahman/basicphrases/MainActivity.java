package com.mdrahman.basicphrases;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void buttonTapped(View view){

        int id = view.getId(); // this function return integer of id but our id is string
        String ourId = "";
        ourId = view.getResources().getResourceEntryName(id); // getting string value of id

        // integer to refer to one of sound file
        int resourceId = getResources().getIdentifier(ourId, "raw", "com.mdrahman.basicphrases");
        MediaPlayer mplayer = MediaPlayer.create(this, resourceId);
        mplayer.start();



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
