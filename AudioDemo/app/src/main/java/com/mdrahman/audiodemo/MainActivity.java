package com.mdrahman.audiodemo;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.media.AudioManager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mplayer;
    AudioManager audioMang; // variable for audio manager

    public void playFun(View view){
        mplayer.start();
    }

    public void pauseFun(View view){
            mplayer.pause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mplayer = MediaPlayer.create(this, R.raw.audio);

        audioMang = (AudioManager) getSystemService(Context.AUDIO_SERVICE); // allow audio service
        int maxVolume = audioMang.getStreamMaxVolume(AudioManager.STREAM_MUSIC); // give max vol of music type (STREAM_MUSIC)
        int currentVol = audioMang.getStreamVolume(AudioManager.STREAM_MUSIC); // give current volume of music type


        // for volume seekBar
        SeekBar volController = (SeekBar) findViewById(R.id.seekBarId); // fisrt seekBar
        volController.setMax(maxVolume); // bcz we don't want to set max volume bigger than system vol, define seekbBar max volume
        volController.setProgress(currentVol); // current volume

        volController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.i("Seekbar value", Integer.toString(progress));
                audioMang.setStreamVolume(AudioManager.STREAM_MUSIC, progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        // ---------------------For second seek Bar -----------------------

        //maxVolOfSecondBar = mplayer.getDuration(); // can put inside on max vol func
        final SeekBar scrubber = (SeekBar) findViewById(R.id.seekBarId2);
        scrubber.setMax(mplayer.getDuration()); // give the max as length of file

        // create timer and schedule at fix rate and in this fixed rate create timer task
        // and run from 0 to 1000 means every second. So every second TimerTask is working
        // showing current position
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubber.setProgress(mplayer.getCurrentPosition());
            }
        }, 0, 1000);

        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mplayer.seekTo(progress); // for fast forward
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mplayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mplayer.start();
            }
        });

        //------------------------------------------------------------------

    }
}
